package git.monthly.reports.domain.interfaces;

import git.monthly.reports.domain.entities.GitUserMonthlyReport;

import java.util.List;

public interface ConvertMonthlyReportToCSV {
    void convertReportToCSV(List<GitUserMonthlyReport> monthlyReports, String orgName, String date);
}
