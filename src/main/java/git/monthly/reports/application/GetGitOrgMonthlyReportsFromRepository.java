package git.monthly.reports.application;

import git.monthly.reports.domain.entities.GitOrganization;
import git.monthly.reports.domain.entities.GitUserMonthlyReport;
import git.monthly.reports.domain.interfaces.OrgMonthlyReportRepository;

import java.util.ArrayList;
import java.util.List;

public class GetGitOrgMonthlyReportsFromRepository {
    private static GitOrganization gitOrganization;
    private static OrgMonthlyReportRepository orgMonthlyReportRepository;
    private static String date;

    public GetGitOrgMonthlyReportsFromRepository(GitOrganization gitOrganization,
                                                 OrgMonthlyReportRepository orgMonthlyReportRepository, String date) {
        this.gitOrganization = gitOrganization;
        this.orgMonthlyReportRepository = orgMonthlyReportRepository;
        this.date = date;
    }

    public List<GitUserMonthlyReport> getOrgMonthlyReport(){
        if (findOrgMontlyReport()){
            return  orgMonthlyReportRepository.getOrgMonthlyReport(gitOrganization.getOrgName(), date);
        }
        else return new ArrayList<GitUserMonthlyReport>();
    }

    private boolean findOrgMontlyReport(){
        //TODO: If the report exists it returns true
        return orgMonthlyReportRepository.findOrgMontlyReport(gitOrganization.getOrgName(), date);
    }
}
