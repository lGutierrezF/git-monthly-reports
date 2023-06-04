package git.monthly.reports.application;

import git.monthly.reports.domain.entities.GitOrganization;
import git.monthly.reports.domain.entities.GitTeam;
import git.monthly.reports.domain.entities.GitUser;
import git.monthly.reports.domain.interfaces.GitTeamRepository;

import java.util.List;

public class GetOrgTeamsFromRepository {
    private static GitOrganization gitOrganization;
    private static GitTeamRepository gitTeamRepository;

    public GetOrgTeamsFromRepository(GitTeamRepository gitTeamRepository, GitOrganization gitOrganization) {
        this.gitTeamRepository = gitTeamRepository;
        this.gitOrganization = gitOrganization;
    }

    public List<GitTeam> getOrgTeams(){
        for (GitTeam team: gitOrganization.getOrgTeams()) {
            team.setTeamMembers(getTeamMembers(team));
        }
        return gitOrganization.getOrgTeams();
    }

    public List<GitTeam> getOrgTeamsNames(String orgName){
        gitOrganization.setOrgTeams(gitTeamRepository.getOrgTeams(orgName));
        return gitOrganization.getOrgTeams();
    }

    private List<GitUser> getTeamMembers(GitTeam team){
        return  gitTeamRepository.getTeamMembers(team.getTeamName());
    }
}
