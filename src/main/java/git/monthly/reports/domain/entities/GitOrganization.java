package git.monthly.reports.domain.entities;

import java.util.List;
import java.util.Map;

public class GitOrganization {
    private String orgName;
    private List<String> orgRepoNames;
    private Map<String,GitTeam> orgTeams;
    private Map<String,GitUserMonthlyReport> monthlyReports;
}
