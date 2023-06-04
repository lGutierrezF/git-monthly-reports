package git.monthly.reports.domain.interfaces;

import git.monthly.reports.domain.entities.GitUserMonthlyReport;

import java.util.List;

public interface OrgMonthlyReportRepository {
    boolean findOrgMontlyReport(String orgName, String date);
    List<GitUserMonthlyReport> getOrgMonthlyReport(String orgName, String date);
    void  saveOrgMonthlyReport(List<GitUserMonthlyReport> orgMonthlyReport);
}
