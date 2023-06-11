package git.monthly.reports.domain.interfaces;


import git.monthly.reports.domain.exceptions.GitClientConnectionException;

public interface GitRepositoryClientConnection {
    String execute(String query) throws GitClientConnectionException;
}
