package git.monthly.reports.infrastructure;

import git.monthly.reports.domain.interfaces.GitRepoRepository;
import git.monthly.reports.domain.interfaces.GitRepositoryClientConnection;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GitHubOrgRepoRepository implements GitRepoRepository {
    private static GitRepositoryClientConnection gitHubConnection;


    public GitHubOrgRepoRepository(GitRepositoryClientConnection gitHubConnection) {
        this.gitHubConnection = gitHubConnection;
    }

    @Override
    public List<String> getOrgRepos(String orgName) {
        System.out.println("Fetching Organization Repository Data");
        return executeGetOrgRepoCall(orgName);
    }

    private List<String> executeGetOrgRepoCall(String orgName){
        List<String> repos = new ArrayList<>();
        String query = "orgs/"+orgName+"/repos";
        String responseJson = gitHubConnection.execute(query);

        JSONArray reposArray = new JSONArray(responseJson);

        for (int i = 0; i < reposArray.length(); i++) {
            JSONObject repoObject = reposArray.getJSONObject(i);
            repos.add(repoObject.getString("name"));
        }
        return repos;
    }

}
