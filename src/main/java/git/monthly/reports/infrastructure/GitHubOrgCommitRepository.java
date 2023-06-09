package git.monthly.reports.infrastructure;

import git.monthly.reports.domain.entities.Commit;
import git.monthly.reports.domain.entities.CommitStats;
import git.monthly.reports.domain.interfaces.GitCommitRepository;
import git.monthly.reports.domain.interfaces.GitRepositoryClientConnection;
import git.monthly.reports.domain.services.MonthConstraintsCalculator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GitHubOrgCommitRepository implements GitCommitRepository {
    private static GitRepositoryClientConnection gitHubConnection;

    public GitHubOrgCommitRepository(GitRepositoryClientConnection gitHubConnection) {
        this.gitHubConnection = gitHubConnection;
    }

    @Override
    public List<Commit> getOrgCommits(String orgName, String repoName, String date) {
        System.out.println("Fetching Organization Commit Data");
        return executeGetOrgCommits(orgName, repoName, date);
    }

    private List<Commit> executeGetOrgCommits(String orgName, String repoName, String date) {
        List<Commit> teams = new ArrayList<>();
        var monthDates = new MonthConstraintsCalculator().execute(date);
        String query = "repos/" + orgName + "/" + repoName + "/commits?since=" + monthDates.get(0).toString()
                + "&until=" + monthDates.get(1).toString();
        String responseJson = gitHubConnection.execute(query);

        JSONArray commitsArray = new JSONArray(responseJson);

        for (int i = 0; i < commitsArray.length(); i++) {
            JSONObject repoObject = commitsArray.getJSONObject(i);

            String authorLogin = getAuthorInformation(repoObject);

            teams.add(new Commit(repoObject.getString("sha"), authorLogin));
        }
        return teams;
    }

    /*
    If the user didn't configure a github user in the git configuration
    the author object in the commit response can be null,
    in those cases, it will alternatively try to identify the user with the configured name in the git profile.
    */
    private static String getAuthorInformation(JSONObject repoObject) {
        JSONObject authorObject = repoObject.optJSONObject("author");
        String authorLogin;

        if (authorObject != null) {
            authorLogin = authorObject.getString("login");
        } else {
            authorLogin = repoObject.getJSONObject("commit").getJSONObject("author").getString("name");
        }
        return authorLogin;
    }

    @Override
    public CommitStats getCommitStats(String orgName, String repoName, String SHA) {
        return executeGetCommitStats(orgName, repoName, SHA);
    }

    private CommitStats executeGetCommitStats(String orgName, String repoName, String SHA){
        List<Commit> teams = new ArrayList<>();
        String query = "repos/"+orgName+"/"+repoName+"/commits/"+SHA;
        String responseJson = gitHubConnection.execute(query);

        JSONObject jsonObject = new JSONObject(responseJson).getJSONObject("stats");
        return new CommitStats(jsonObject.getInt("total"), jsonObject.getInt("additions"),
                jsonObject.getInt("deletions"));
    }
}
