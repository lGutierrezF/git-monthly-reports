package git.monthly.reports.infrastructure;

import git.monthly.reports.domain.entities.PRComment;
import git.monthly.reports.domain.exceptions.GitClientConnectionException;
import git.monthly.reports.domain.interfaces.GitPRsRepository;
import git.monthly.reports.domain.interfaces.GitRepositoryClientConnection;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GitHubOrgPRsRepository implements GitPRsRepository {
        private static GitRepositoryClientConnection gitHubConnection;

        public GitHubOrgPRsRepository(GitRepositoryClientConnection gitHubConnection) {
            this.gitHubConnection = gitHubConnection;
        }

    @Override
    public int getUserExecutedPR(String userName, String orgName, String date) throws GitClientConnectionException {
        return executeGetUserExecutedPR(userName, orgName, date);
    }

    private int executeGetUserExecutedPR(String userName, String orgName, String date) throws GitClientConnectionException {
        int total_count = 0;
        String base = "search/issues";
        String query = "?q=type:pr+author:" + userName +"+org:" + orgName + "+created:" + date;
        String responseJson = gitHubConnection.execute(base+query);

        JSONObject obj = new JSONObject(responseJson);
        total_count = obj.getInt("total_count");

        return total_count;
    }

    @Override
    public int getUserReviewedPR(String userName, String orgName, String date) throws GitClientConnectionException {
        System.out.println("Fetching Organization Pull Request Data");
        return executeGetUserReviewedPR(userName, orgName, date);
    }

    private int executeGetUserReviewedPR(String userName, String orgName, String date) throws GitClientConnectionException {
        int total_count = 0;
        String base = "search/issues";
        String query = "?q=type:pr+reviewed-by:" + userName +"+org:" + orgName +  "+created:" + date;
        String responseJson = gitHubConnection.execute(base+query);

        JSONObject obj = new JSONObject(responseJson);
        total_count = obj.getInt("total_count");

        return total_count;
    }

    @Override
    public List<PRComment> getUserCommentsOnPR(String userName, String orgName, String date) throws GitClientConnectionException {
        return executeGetUserCommentsOnPR(userName, orgName, date);
    }

    private List<PRComment> executeGetUserCommentsOnPR(String userName, String orgName, String date) throws GitClientConnectionException {
        List<PRComment> comments = new ArrayList<>();
        String base = "search/issues";
        String query = "?q=type:pr+commenter:" + userName +"+org:" + orgName +  "+created:" + date;
        String responseJson = gitHubConnection.execute(base+query);

        JSONObject jsonObject = new JSONObject(responseJson);
        JSONArray commentsArray = jsonObject.getJSONArray("items");

        for (int i = 0; i < commentsArray.length(); i++) {
            JSONObject repoObject = commentsArray.getJSONObject(i);
            comments.add(new PRComment(repoObject.optString("body", "")));
        }
        return comments;
    }
}
