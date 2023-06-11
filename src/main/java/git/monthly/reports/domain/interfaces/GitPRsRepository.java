package git.monthly.reports.domain.interfaces;

import git.monthly.reports.domain.entities.PRComment;
import git.monthly.reports.domain.exceptions.GitClientConnectionException;

import java.util.List;

public interface GitPRsRepository {
    int getUserExecutedPR(String userName, String orgName, String date) throws GitClientConnectionException;
    int getUserReviewedPR(String userName, String orgName, String date) throws GitClientConnectionException;
    List<PRComment> getUserCommentsOnPR(String userName, String orgName, String date) throws GitClientConnectionException;
}
