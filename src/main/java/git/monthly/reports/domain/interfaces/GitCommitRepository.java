package git.monthly.reports.domain.interfaces;

import git.monthly.reports.domain.entities.Commit;
import git.monthly.reports.domain.entities.CommitStats;
import git.monthly.reports.domain.exceptions.GitClientConnectionException;

import java.util.List;

public interface GitCommitRepository {
    List<Commit> getOrgCommits(String orgName, String repoName, String date) throws GitClientConnectionException;
    CommitStats getCommitStats(String orgName, String repoName, String SHA) throws GitClientConnectionException;
}
