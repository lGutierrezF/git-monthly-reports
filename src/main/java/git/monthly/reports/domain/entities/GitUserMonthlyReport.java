package git.monthly.reports.domain.entities;


public class GitUserMonthlyReport {
    private String orgName;
    private String teamName;
    private String userName;
    private String date;
    private int reviewedPRs;
    private int executedPRs;
    private float commentAvgLength;
    private int totalLines;
    private int linesAdded;
    private int linesDeleted;

    public GitUserMonthlyReport(String orgName, String teamName, String userName, String date, int reviewedPRs,
                                int executedPRs, float commentAvgLength, int totalLines,
                                int linesAdded, int linesDeleted) {
        this.orgName = orgName;
        this.teamName = teamName;
        this.userName = userName;
        this.date = date;
        this.reviewedPRs = reviewedPRs;
        this.executedPRs = executedPRs;
        this.commentAvgLength = commentAvgLength;
        this.totalLines = totalLines;
        this.linesAdded = linesAdded;
        this.linesDeleted = linesDeleted;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getuserName() {
        return userName;
    }

    public String getDate() {
        return date;
    }

    public int getreviewedPRs() {
        return reviewedPRs;
    }

    public int getexecutedPRs() {
        return executedPRs;
    }

    public float getcommentAvgLength() {
        return commentAvgLength;
    }

    public int getTotalLines() {
        return totalLines;
    }

    public int getLinesAdded() {
        return linesAdded;
    }

    public int getLinesDeleted() {
        return linesDeleted;
    }

}
