package git.monthly.reports.infrastructure;

import git.monthly.reports.application.GetOrgReposFromRepository;
import git.monthly.reports.application.GetOrgTeamGitUsersPRsFromRepository;
import git.monthly.reports.application.GetOrgTeamsFromRepository;
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


    @Override
    public void run(String... args) throws Exception {
        gitHubConnection = new GitHubClientConnection();
        organization = new GitOrganization("github-stats-test");
        date = "2023-05";
        var repos = new GetOrgReposFromRepository(gitHubOrgRepoRepository, organization).getOrgRepos();
        var teams = new GetOrgTeamsFromRepository(gitHubOrgTeamRepository,organization).getOrgTeams();
        teams = new GetOrgTeamGitUsersPRsFromRepository(gitHubOrgPRsRepository,organization,date).getOrgTeamsGitUsersPR();

        System.out.println(repos);
        System.out.println(teams);

    }
}
