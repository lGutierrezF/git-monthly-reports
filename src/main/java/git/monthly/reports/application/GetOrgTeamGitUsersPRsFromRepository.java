package git.monthly.reports.application;

import git.monthly.reports.domain.entities.GitOrganization;
import git.monthly.reports.domain.entities.GitTeam;
import git.monthly.reports.domain.entities.GitUser;
import git.monthly.reports.domain.entities.PRComment;
import git.monthly.reports.domain.interfaces.GitPRsRepository;

import java.util.List;

public class GetOrgTeamGitUsersPRsFromRepository {
    private static GitOrganization gitOrganization;
    private static GitPRsRepository gitPRsRepository;
    private static String date;


    public GetOrgTeamGitUsersPRsFromRepository(GitPRsRepository gitPRsRepository, GitOrganization gitOrganization, String date) {
        this.gitPRsRepository = gitPRsRepository;
        this.gitOrganization = gitOrganization;
        this.date = date;
    }

    public List<GitTeam> getOrgTeamsGitUsersPR(){
        for (GitTeam team: gitOrganization.getOrgTeams()) {
            team.setTeamMembers(getOrgTeamGitUsersPR(team).getTeamMembers());
        }
        return gitOrganization.getOrgTeams();
    }

    private GitTeam getOrgTeamGitUsersPR(GitTeam gitTeam){
        for (GitUser gitUser: gitTeam.getTeamMembers()) {
            gitUser.setExecutedPR(getUserExecutedPR(gitUser.getUserName(),date));
            gitUser.setReviwedPR(getUserReviewedPR(gitUser.getUserName(),date));
            gitUser.setComments(getUserCommentsOnPR(gitUser.getUserName(),date));
        }
        return gitTeam;
    }

    private int getUserExecutedPR(String userName, String date){
        return gitPRsRepository.getUserExecutedPR(userName, date);
    }

    private int getUserReviewedPR(String userName, String date){
        return gitPRsRepository.getUserReviewedPR(userName, date);
    }

    private List<PRComment> getUserCommentsOnPR(String userName, String date){
        return gitPRsRepository.getUserCommentsOnPR(userName, date);
    }
}
