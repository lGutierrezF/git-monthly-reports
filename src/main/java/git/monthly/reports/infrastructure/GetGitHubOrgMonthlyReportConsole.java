package git.monthly.reports.infrastructure;

import git.monthly.reports.application.*;
import git.monthly.reports.domain.entities.GitOrganization;
import git.monthly.reports.domain.services.MonthConstraintsCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class GetGitHubOrgMonthlyReportConsole implements CommandLineRunner {
    @Autowired
    private static MongoDBClient mongoClient;
    private static GitHubClientConnection gitHubConnection;
    private static GitOrganization organization;
    private static String date;
    private static String orgName;


    private static  GitHubOrgRepoRepository gitHubOrgRepoRepository = new GitHubOrgRepoRepository(gitHubConnection);
    private static GitHubOrgTeamRepository gitHubOrgTeamRepository = new GitHubOrgTeamRepository(gitHubConnection);
    private static GitHubOrgPRsRepository gitHubOrgPRsRepository = new GitHubOrgPRsRepository(gitHubConnection);
    private static GitHubOrgCommitRepository gitHubOrgCommitRepository = new GitHubOrgCommitRepository(gitHubConnection);
    private static MongoDBReportRepository mongoDBReportRepository = new MongoDBReportRepository(mongoClient);
    private CommonsCSVReportGenerator commonsCSVReportGenerator = new CommonsCSVReportGenerator();


    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the organization name: ");
        orgName = scanner.nextLine();

        System.out.println("Enter the date (yyyy-MM): ");
        date = scanner.nextLine();

        scanner.close();

        if (orgName.isEmpty() || date.isEmpty()){
            System.out.println("Using default values");
            date = "2023-06";
            orgName = "github-stats-test";
        }


        gitHubConnection = new GitHubClientConnection();
        organization = new GitOrganization(orgName);

        var reports = new GetGitOrgMonthlyReportsFromRepository(organization,mongoDBReportRepository,date).getOrgMonthlyReport();
        if (reports.isEmpty()) {
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

    private void saveOrganizationMonthlyReportCSV() {
        new PrintMonthlyReportCSV(organization, commonsCSVReportGenerator, date).execute();
    }

    private static void saveOrganizationMonthlyReportToMongoDB() {
        MonthConstraintsCalculator calculator = new MonthConstraintsCalculator();
        if (calculator.isMonthEnded(date)){
        new SaveGitOrgMonthlyReports(organization, mongoDBReportRepository).execute();
        }
       else System.out.println("Month data is still not final, report won't be saved to MongoDB database.");
    }

    private static void fetchOrganizationMonthlyReportFromAPI() {
        System.out.println("Report not stored locally, fetching data from the API.");
        new GetOrgReposFromRepository(gitHubOrgRepoRepository, organization).execute();
        new GetOrgTeamsFromRepository(gitHubOrgTeamRepository, organization).execute();
        new GetOrgTeamGitUsersPRsFromRepository(gitHubOrgPRsRepository, organization, date).execute();
        new GetOrgCommitsFromRepository(gitHubOrgCommitRepository, organization, date).execute();
        new CreateOrgMonthlyReport(organization, date).execute();
    }
}
