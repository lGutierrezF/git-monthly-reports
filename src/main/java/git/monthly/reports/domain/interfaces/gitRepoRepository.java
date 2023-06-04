package git.monthly.reports.domain.interfaces;

import java.util.List;

public interface gitRepoRepository {
    List<String> getOrgRepos(String orgName);
}
