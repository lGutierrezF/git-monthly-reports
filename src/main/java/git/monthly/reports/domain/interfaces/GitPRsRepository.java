package git.monthly.reports.domain.interfaces;

import git.monthly.reports.domain.entities.PRComment;

import java.util.List;

public interface GitPRsRepository {
    int getUserExecutedPR(String userName, String orgName, String date);
    int getUserReviewedPR(String userName, String orgName, String date);
    List<PRComment> getUserCommentsOnPR(String userName, String orgName, String date);
}
