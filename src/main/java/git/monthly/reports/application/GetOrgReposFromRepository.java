package git.monthly.reports.application;

import git.monthly.reports.domain.entities.GitOrganization;
import git.monthly.reports.domain.exceptions.GitClientConnectionException;
import git.monthly.reports.domain.interfaces.GitRepoRepository;

import java.util.List;

public class GetOrgReposFromRepository {
    private static GitOrganization gitOrganization;
    private static GitRepoRepository gitRepoRepository;

    public GetOrgReposFromRepository(GitRepoRepository gitRepoRepository, GitOrganization gitOrganization) {
        this.gitRepoRepository = gitRepoRepository;
        this.gitOrganization = gitOrganization;
    }

    public List<String> execute() throws GitClientConnectionException {
        gitOrganization.setOrgRepoNames(gitRepoRepository.getOrgRepos(gitOrganization.getOrgName()));
        return gitOrganization.getOrgRepoNames();
    }

}
