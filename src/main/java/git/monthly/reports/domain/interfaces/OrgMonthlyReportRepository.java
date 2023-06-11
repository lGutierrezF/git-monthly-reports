package git.monthly.reports.domain.interfaces;

import git.monthly.reports.domain.entities.GitUserMonthlyReport;
import git.monthly.reports.domain.exceptions.ReportClientConnectionException;

import java.util.List;

public interface OrgMonthlyReportRepository {
    boolean findOrgMontlyReport(String orgName, String date) throws ReportClientConnectionException;

    List<GitUserMonthlyReport> getOrgMonthlyReport(String orgName, String date) throws ReportClientConnectionException;

    void  saveOrgMonthlyReport(List<GitUserMonthlyReport> orgMonthlyReport) throws ReportClientConnectionException;
}
