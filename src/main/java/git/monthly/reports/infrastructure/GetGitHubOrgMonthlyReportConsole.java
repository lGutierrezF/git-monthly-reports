package git.monthly.reports.infrastructure;

import git.monthly.reports.application.GetOrgReposFromRepository;
import git.monthly.reports.application.GetOrgTeamsFromRepository;
import git.monthly.reports.domain.entities.GitOrganization;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GetGitHubOrgMonthlyReportConsole implements CommandLineRunner {
    private static GitHubClientConnection gitHubConnection;
    private static GitOrganization organization;

    private static  GitHubOrgRepoRepository gitHubOrgRepoRepository = new GitHubOrgRepoRepository(gitHubConnection);
    private static GitHubOrgTeamRepository gitHubOrgTeamRepository = new GitHubOrgTeamRepository(gitHubConnection);


    @Override
    public void run(String... args) throws Exception {
        gitHubConnection = new GitHubClientConnection();
        organization = new GitOrganization("github-stats-test");
        var repos = new GetOrgReposFromRepository(gitHubOrgRepoRepository, organization).getOrgRepos();
        var teams = new GetOrgTeamsFromRepository(gitHubOrgTeamRepository,organization).getOrgTeams();
        System.out.println(repos);
        System.out.println(teams);

    }
}
