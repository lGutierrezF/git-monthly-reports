package git.monthly.reports.application;

import git.monthly.reports.domain.entities.GitOrganization;
import git.monthly.reports.domain.entities.GitTeam;
import git.monthly.reports.domain.entities.GitUser;
import git.monthly.reports.domain.entities.PRComment;
import git.monthly.reports.domain.exceptions.GitClientConnectionException;
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

    public List<GitTeam> execute() throws GitClientConnectionException {
        for (GitTeam team: gitOrganization.getOrgTeams()) {
            team.setTeamMembers(getOrgTeamGitUsersPR(team).getTeamMembers());
        }
        return gitOrganization.getOrgTeams();
    }

    private GitTeam getOrgTeamGitUsersPR(GitTeam gitTeam) throws GitClientConnectionException {
        for (GitUser gitUser: gitTeam.getTeamMembers()) {
            gitUser.setExecutedPR(getUserExecutedPR(gitUser.getUserName(),gitOrganization.getOrgName(),date));
            gitUser.setReviwedPR(getUserReviewedPR(gitUser.getUserName(),gitOrganization.getOrgName(),date));
            gitUser.setComments(getUserCommentsOnPR(gitUser.getUserName(),gitOrganization.getOrgName(),date));
        }
        return gitTeam;
    }

    private int getUserExecutedPR(String userName, String orgName,String date) throws GitClientConnectionException {
        return gitPRsRepository.getUserExecutedPR(userName, orgName, date);
    }

    private int getUserReviewedPR(String userName, String orgName, String date) throws GitClientConnectionException {
        return gitPRsRepository.getUserReviewedPR(userName, orgName, date);
    }

    private List<PRComment> getUserCommentsOnPR(String userName, String orgName, String date) throws GitClientConnectionException {
        return gitPRsRepository.getUserCommentsOnPR(userName, orgName, date);
    }
}
