package git.monthly.reports.domain.interfaces;

import git.monthly.reports.domain.entities.PRComment;

import java.util.List;

public interface gitPRRepository {
    int getUserExecutedPR(String userName, String date);
    int getUserReviewedPR(String userName, String date);
    List<PRComment> getUserCommentsOnPR(String userName, String date);
}
