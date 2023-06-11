package git.monthly.reports.domain.interfaces;

import git.monthly.reports.domain.entities.GitTeam;
import git.monthly.reports.domain.entities.GitUser;
import git.monthly.reports.domain.exceptions.EmptyOrganizationTeamException;
import git.monthly.reports.domain.exceptions.GitClientConnectionException;

import java.util.List;

public interface GitTeamRepository {
    List<GitTeam> getOrgTeams(String orgName) throws GitClientConnectionException, EmptyOrganizationTeamException;

    List<GitUser> getTeamMembers(String orgName, String teamName) throws GitClientConnectionException, EmptyOrganizationTeamException;
}
