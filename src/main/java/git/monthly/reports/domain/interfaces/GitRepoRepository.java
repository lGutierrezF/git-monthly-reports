package git.monthly.reports.domain.interfaces;

import git.monthly.reports.domain.exceptions.GitClientConnectionException;

import java.util.List;

public interface GitRepoRepository {
    List<String> getOrgRepos(String orgName) throws GitClientConnectionException;
}
