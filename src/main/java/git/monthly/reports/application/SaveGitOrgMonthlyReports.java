package git.monthly.reports.application;

import git.monthly.reports.domain.entities.GitOrganization;
import git.monthly.reports.domain.exceptions.ReportClientConnectionException;
import git.monthly.reports.domain.interfaces.OrgMonthlyReportRepository;

public class SaveGitOrgMonthlyReports {
    private static GitOrganization gitOrganization;
    private static OrgMonthlyReportRepository orgMonthlyReportRepository;

    public SaveGitOrgMonthlyReports(GitOrganization gitOrganization,
                                                 OrgMonthlyReportRepository orgMonthlyReportRepository) {
        this.gitOrganization = gitOrganization;
        this.orgMonthlyReportRepository = orgMonthlyReportRepository;
    }

    public void execute() throws ReportClientConnectionException {
        orgMonthlyReportRepository.saveOrgMonthlyReport(gitOrganization.getMonthlyReports());
    }
}
