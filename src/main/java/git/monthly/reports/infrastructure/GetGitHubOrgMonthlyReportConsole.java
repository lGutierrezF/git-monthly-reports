package git.monthly.reports.infrastructure;

import git.monthly.reports.application.*;
import git.monthly.reports.domain.entities.GitOrganization;
import git.monthly.reports.domain.entities.GitUserMonthlyReport;
import git.monthly.reports.domain.exceptions.*;
import git.monthly.reports.domain.interfaces.InvalidDateException;
import git.monthly.reports.domain.services.MonthConstraintsCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.YearMonth;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class GetGitHubOrgMonthlyReportConsole implements CommandLineRunner {
    @Autowired
    private static MongoDBClient mongoClient;
    private static GitHubClientConnection gitHubConnection;
    private static GitOrganization organization;
    private static String date;
    private static String orgName;


    private static GitHubOrgRepoRepository gitHubOrgRepoRepository = new GitHubOrgRepoRepository(gitHubConnection);
    private static GitHubOrgTeamRepository gitHubOrgTeamRepository = new GitHubOrgTeamRepository(gitHubConnection);
    private static GitHubOrgPRsRepository gitHubOrgPRsRepository = new GitHubOrgPRsRepository(gitHubConnection);
    private static GitHubOrgCommitRepository gitHubOrgCommitRepository = new GitHubOrgCommitRepository(gitHubConnection);
    private static MongoDBReportRepository mongoDBReportRepository = new MongoDBReportRepository(mongoClient);
    private CommonsCSVReportGenerator commonsCSVReportGenerator = new CommonsCSVReportGenerator();


    @Override
    public void run(String... args) throws Exception {

        getInputParameters();
        List<GitUserMonthlyReport> reports;

        try {
            reports = new GetGitOrgMonthlyReportsFromRepository(organization,mongoDBReportRepository,date).getOrgMonthlyReport();
        } catch (Exception e){
            throw new ReportClientConnectionException("Error connecting to MongoDB database.");
        }

        if (reports.isEmpty()) {
            gitHubConnection = new GitHubClientConnection();
            fetchOrganizationMonthlyReportFromAPI();
            saveOrganizationMonthlyReportToMongoDB();
            saveOrganizationMonthlyReportCSV();

            System.exit(0);

        } else{
            System.out.println("Report already stored locally");
            organization.setMonthlyReports(reports);
            saveOrganizationMonthlyReportCSV();
            System.exit(0);
        }
    }

    private void getInputParameters() throws EmptyReportDateException, EmptyOrganizationNameException, IncorrectDateFormatException, InvalidDateException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the organization name: ");
        orgName = scanner.nextLine();
        organization = new GitOrganization(orgName);

        System.out.println("Enter the date (YYYY-MM): ");
        date = scanner.nextLine();
        guardEmptyReportDate(date);
        guardCorrectDateFormat(date);
        guardDateIsInRange(date);

        scanner.close();
    }

    private void guardEmptyReportDate(String date) throws EmptyReportDateException {
        if (date.isEmpty()) {
            throw new EmptyReportDateException();
        }

    }
    public void guardCorrectDateFormat(String date) throws IncorrectDateFormatException {
        String pattern = "\\d{4}-\\d{2}";

        if (!date.matches(pattern)) {
            throw new IncorrectDateFormatException();
        }
    }
    public void guardDateIsInRange(String date) throws InvalidDateException {
        try {
            YearMonth yearMonth = YearMonth.parse(date);
            int year = yearMonth.getYear();

            if (year < 1970 || year > 2970) {
                throw new InvalidDateException("Invalid date. Year must be within the range 1970 to 2970.");
            }
        } catch (Exception e) {
            throw new InvalidDateException("Invalid date. Month must be within the range 01 to 12.");
        }
    }

    private void saveOrganizationMonthlyReportCSV() {
        new PrintMonthlyReportCSV(organization, commonsCSVReportGenerator, date).execute();
    }

    private static void saveOrganizationMonthlyReportToMongoDB() throws ReportClientConnectionException {
        MonthConstraintsCalculator calculator = new MonthConstraintsCalculator();
        if (calculator.isMonthEnded(date)){
            try {
                new SaveGitOrgMonthlyReports(organization, mongoDBReportRepository).execute();
            } catch (Exception e){
                throw new ReportClientConnectionException("Error connecting to MongoDB database.");
            }
        }
       else System.out.println("Month data is still not final, report won't be saved to MongoDB database.");
    }

    private static void fetchOrganizationMonthlyReportFromAPI() throws GitClientConnectionException {
        System.out.println("Report not stored locally, fetching data from the API.");
        try {
            new GetOrgReposFromRepository(gitHubOrgRepoRepository, organization).execute();
            new GetOrgTeamsFromRepository(gitHubOrgTeamRepository, organization).execute();
            new GetOrgTeamGitUsersPRsFromRepository(gitHubOrgPRsRepository, organization, date).execute();
            new GetOrgCommitsFromRepository(gitHubOrgCommitRepository, organization, date).execute();
            new CreateOrgMonthlyReport(organization, date).execute();
        } catch (Exception e) {
            throw new GitClientConnectionException("Organization does not exist in GitHib API Repository.");
        }

    }
}
