package git.monthly.reports.infrastructure;

import git.monthly.reports.domain.entities.GitTeam;
import git.monthly.reports.domain.entities.GitUser;
import git.monthly.reports.domain.exceptions.EmptyOrganizationTeamException;
import git.monthly.reports.domain.exceptions.GitClientConnectionException;
import git.monthly.reports.domain.interfaces.GitRepositoryClientConnection;
import git.monthly.reports.domain.interfaces.GitTeamRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GitHubOrgTeamRepository implements GitTeamRepository {
    private static GitRepositoryClientConnection gitHubConnection;



    public GitHubOrgTeamRepository(GitRepositoryClientConnection gitHubConnection) {
        this.gitHubConnection = gitHubConnection;
    }

    @Override
    public List<GitTeam> getOrgTeams(String orgName) throws GitClientConnectionException, EmptyOrganizationTeamException {
        System.out.println("Fetching Organization Team Data");
        return executeGetOrgTeamsCall(orgName);
    }

    private List<GitTeam> executeGetOrgTeamsCall(String orgName) throws GitClientConnectionException, EmptyOrganizationTeamException {
        List<GitTeam> teams = new ArrayList<>();
        String query = "orgs/"+orgName+"/teams";
        String responseJson = gitHubConnection.execute(query);

        JSONArray teamsArray = new JSONArray(responseJson);

        for (int i = 0; i < teamsArray.length(); i++) {
            JSONObject teamObject = teamsArray.getJSONObject(i);
            try {
                var teamName = teamObject.getString("name");
                teams.add(new GitTeam(teamName));
            } catch (Exception e){
                throw new EmptyOrganizationTeamException("Organization does not have any teams.");
            }
        }
        return teams;
    }

    @Override
    public List<GitUser> getTeamMembers(String orgName, String teamName) throws GitClientConnectionException, EmptyOrganizationTeamException {
        return executeGetTeamMembers(orgName, teamName);
    }

    private List<GitUser> executeGetTeamMembers(String orgName, String teamName) throws GitClientConnectionException, EmptyOrganizationTeamException {
        List<GitUser> teamMembers = new ArrayList<>();
        String query = "orgs/"+orgName+"/teams/"+teamName+"/members";
        String responseJson = gitHubConnection.execute(query);

        JSONArray teamsArray = new JSONArray(responseJson);

        for (int i = 0; i < teamsArray.length(); i++) {
            JSONObject teamMemberObject = teamsArray.getJSONObject(i);
            try {
                var teamMemberLogin = teamMemberObject.getString("login");
                teamMembers.add(new GitUser(teamMemberLogin));
            } catch (Exception e){
                throw new EmptyOrganizationTeamException("Organization team does not have any team members.");
            }
        }
        return teamMembers;
    }
}
