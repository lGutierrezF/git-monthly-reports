package git.monthly.reports.domain.interfaces;

import git.monthly.reports.domain.entities.Commit;
import git.monthly.reports.domain.entities.CommitStats;

import java.util.List;

public interface GitCommitRepository {
    List<Commit> getOrgCommits(String orgName, String repoName, String date);
    CommitStats getCommitStats(String commitSHA);
}
