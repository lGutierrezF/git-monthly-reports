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
        return executeGetOrgCommits(orgName, repoName, date);
    }

    private List<Commit> executeGetOrgCommits(String orgName, String repoName, String date){
        List<Commit> teams = new ArrayList<>();
        var monthDates = new MonthConstraintsCalculator().execute(date);
        String query = "repos/"+orgName+"/"+repoName+"/commits?since=" +  monthDates.get(0).toString()
                + "&until=" +  monthDates.get(1).toString();
        String responseJson = gitHubConnection.execute(query);

        JSONArray commitsArray = new JSONArray(responseJson);

        for (int i = 0; i < commitsArray.length(); i++) {
            JSONObject repoObject = commitsArray.getJSONObject(i);
            teams.add(new Commit(repoObject.getString("sha"),repoObject.getJSONObject("commit")
                    .getJSONObject("committer").getString("name")));
        }
        return teams;
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
