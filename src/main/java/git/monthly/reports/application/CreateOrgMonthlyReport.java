package git.monthly.reports.application;


import git.monthly.reports.domain.entities.*;

import java.util.ArrayList;
import java.util.List;

public class CreateOrgMonthlyReport {
    private static GitOrganization gitOrganization;
    private static String date;

    public CreateOrgMonthlyReport(GitOrganization gitOrganization, String date) {
        this.gitOrganization = gitOrganization;
        this.date =  date;
    }

    public List<GitUserMonthlyReport> execute(){
        for (GitTeam team: gitOrganization.getOrgTeams()) {
            for (GitUser member: team.getTeamMembers()) {
                var orgName = gitOrganization.getOrgName();
                var teamName = team.getTeamName();
                var memberName = member.getUserName();
                var reviewedPRs = member.getReviwedPR();
                var executedPRs = member.getExecutedPR();
                var commentAvgLength = getCommentAvgLenght(member);
                var totalCommitStats = getTotalCommitStats(member.getCommits());
                gitOrganization.getMonthlyReports().add(new GitUserMonthlyReport(orgName,teamName,
                        memberName,date,reviewedPRs,executedPRs,commentAvgLength,
                        totalCommitStats.get(3), //Total commits
                        totalCommitStats.get(0), // Total lines
                        totalCommitStats.get(1), // Total additions
                        totalCommitStats.get(2))); //Total deletions
            }
        }
        return gitOrganization.getMonthlyReports();
    }

    private List<Integer> getTotalCommitStats(List<Commit> commits){
        int total = 0;
        int additions = 0;
        int deletions = 0;
        List<Integer> lineStats = new ArrayList<>();

        for (Commit stats: commits){
            if(stats==null){
                total+= 0;
                additions += 0;
                deletions += 0;
            }
            else {
                total+= stats.getStats().getTotalLines();
                additions += stats.getStats().getAdditions();
                deletions += stats.getStats().getDeletions();
            }
        }
        lineStats.add(total);
        lineStats.add(additions);
        lineStats.add(deletions);
        lineStats.add(commits.size());
        return lineStats;
    }

    private float getCommentAvgLenght(GitUser user){
        int totalLength = 0;
        if(user.getCommentCount() == 0)
            return 0;
        else {
            for (PRComment comment : user.getComments()) {
                totalLength += comment.getCommentLength();
            }
            return totalLength / user.getCommentCount();
        }
    }
}
