package git.monthly.reports.domain.interfaces;

import java.util.List;

public interface GitRepoRepository {
    List<String> getOrgRepos(String orgName);
}
