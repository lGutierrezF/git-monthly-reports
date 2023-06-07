package git.monthly.reports.infrastructure;

import git.monthly.reports.domain.interfaces.GitRepositoryClientConnection;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GitHubClientConnection implements GitRepositoryClientConnection {
    private static final String API_BASE_URL = "https://api.github.com/";
    private static final String API_VERSION_SPEC = "application/vnd.github.v3+json";
    private static final String JSON_CONTENT_TYPE = "application/json";
    private static final String accessToken = "Bearer " + System.getenv("ACCESS_TOKEN_GITLAB");
    private static OkHttpClient client;

    public GitHubClientConnection() {
        System.out.println("Creating connection client");
         this.client = new OkHttpClient();
    }

    @Override
    public String execute(String query) {
        Request request = new Request.Builder()
                .url(API_BASE_URL+query)
                .header("Authorization", accessToken)
                .header("Accept", API_VERSION_SPEC)
                .header("Content-Type", JSON_CONTENT_TYPE)
                .build();
        try {
            Response response = client.newCall(request).execute();
            ResponseBody responseBody = response.body();

            if (response.isSuccessful() && responseBody != null) {
                return responseBody.string();
            } else {
                System.out.println("Request failed with status code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
