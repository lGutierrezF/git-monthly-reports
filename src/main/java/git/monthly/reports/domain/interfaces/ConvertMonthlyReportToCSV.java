package git.monthly.reports.domain.interfaces;

import git.monthly.reports.domain.entities.GitUserMonthlyReport;

import java.util.Map;

public interface ConvertMonthlyReportToCSV {
    void convertReportToCSV(Map<String,GitUserMonthlyReport> report);
}
