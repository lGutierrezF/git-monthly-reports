package git.monthly.reports.application;

import git.monthly.reports.domain.entities.GitOrganization;
import git.monthly.reports.domain.interfaces.ConvertMonthlyReportToCSV;
import git.monthly.reports.domain.interfaces.OrgMonthlyReportRepository;

public class PrintMonthlyReportCSV {
    private static GitOrganization gitOrganization;
    private static String date;
    private ConvertMonthlyReportToCSV writer;

    public PrintMonthlyReportCSV(GitOrganization gitOrganization, ConvertMonthlyReportToCSV writer, String date) {
        this.gitOrganization = gitOrganization;
        this.date = date;
        this.writer = writer;
    }

    public void execute(){
        writer.convertReportToCSV(gitOrganization.getMonthlyReports(),gitOrganization.getOrgName(),date);
    }
}
