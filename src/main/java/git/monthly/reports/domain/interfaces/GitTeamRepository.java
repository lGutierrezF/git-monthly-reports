package git.monthly.reports.domain.interfaces;

import git.monthly.reports.domain.entities.GitTeam;
import git.monthly.reports.domain.entities.GitUser;

import java.util.List;

public interface GitTeamRepository {
    List<GitTeam> getOrgTeams(String orgName);
    List<GitUser> getTeamMembers(String teamName);
}
