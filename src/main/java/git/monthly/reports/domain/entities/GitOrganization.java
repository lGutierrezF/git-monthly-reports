package git.monthly.reports.domain.entities;

import java.util.List;

public final class  GitOrganization {
    private String orgName;
    private List<String> orgRepoNames;
    private List<GitTeam> orgTeams;
    private List<GitUserMonthlyReport> monthlyReports;

    public GitOrganization(String orgName) {
        this.orgName = orgName;
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
