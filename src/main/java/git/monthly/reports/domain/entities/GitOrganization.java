package git.monthly.reports.domain.entities;

import git.monthly.reports.domain.exceptions.EmptyOrganizationNameException;

import java.util.ArrayList;
import java.util.List;

public final class  GitOrganization {
    private String orgName;
    private List<String> orgRepoNames;
    private List<GitTeam> orgTeams;
    private List<GitUserMonthlyReport> monthlyReports;

    public GitOrganization(String orgName) throws EmptyOrganizationNameException {
        guardOrgNameIsNotEmpty(orgName);
        this.orgRepoNames = new ArrayList<>();
        this.orgTeams = new ArrayList<>();
        this.monthlyReports = new ArrayList<>();
        this.orgName = orgName;
    }

    private void guardOrgNameIsNotEmpty(String orgName) throws EmptyOrganizationNameException {
        if (orgName.isEmpty()) {
            throw new EmptyOrganizationNameException();
        }
    }

    public String getOrgName() {
        return orgName;
    }

    public List<String> getOrgRepoNames() {
        return orgRepoNames;
    }

    public void setOrgRepoNames(List<String> orgRepoNames) {
        this.orgRepoNames = orgRepoNames;
    }

    public List<GitTeam> getOrgTeams() {
        return orgTeams;
    }

    public void setOrgTeams(List<GitTeam> orgTeams) {
        this.orgTeams = orgTeams;
    }

    public List<GitUserMonthlyReport> getMonthlyReports() {
        return monthlyReports;
    }

    public void setMonthlyReports(List<GitUserMonthlyReport> monthlyReports) {
        this.monthlyReports = monthlyReports;
    }
}
