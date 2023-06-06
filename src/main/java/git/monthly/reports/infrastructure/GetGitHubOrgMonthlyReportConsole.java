package git.monthly.reports.infrastructure;

import git.monthly.reports.application.*;
import git.monthly.reports.domain.entities.GitOrganization;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GetGitHubOrgMonthlyReportConsole implements CommandLineRunner {
    private static GitHubClientConnection gitHubConnection;
    private static GitOrganization organization;
    private static String date;

    private static  GitHubOrgRepoRepository gitHubOrgRepoRepository = new GitHubOrgRepoRepository(gitHubConnection);
    private static GitHubOrgTeamRepository gitHubOrgTeamRepository = new GitHubOrgTeamRepository(gitHubConnection);
    private static GitHubOrgPRsRepository gitHubOrgPRsRepository = new GitHubOrgPRsRepository(gitHubConnection);
    private static GitHubOrgCommitRepository gitHubOrgCommitRepository = new GitHubOrgCommitRepository(gitHubConnection);


    @Override
    public void run(String... args) throws Exception {
        gitHubConnection = new GitHubClientConnection();
        organization = new GitOrganization("github-stats-test");
        date = "2023-05";
        var repos = new GetOrgReposFromRepository(gitHubOrgRepoRepository, organization).execute();
        var teams = new GetOrgTeamsFromRepository(gitHubOrgTeamRepository,organization).execute();
        teams = new GetOrgTeamGitUsersPRsFromRepository(gitHubOrgPRsRepository,organization,date).execute();
        teams = new GetOrgCommitsFromRepository(gitHubOrgCommitRepository,organization,date).execute();
        var reports = new CreateOrgMonthlyReport(organization,date).execute();

        System.out.println(repos);
        System.out.println(teams);
        System.out.println(reports);


    }
}
