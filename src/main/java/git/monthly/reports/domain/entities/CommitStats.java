package git.monthly.reports.domain.entities;

public class CommitStats {
    private int totalLines;
    private int additions;
    private int deletions;

    public CommitStats(int totalLines, int additions, int deletions) {
        this.totalLines = totalLines;
        this.additions = additions;
        this.deletions = deletions;
    }

    public int getTotalLines() {
        return totalLines;
    }

    public int getAdditions() {
        return additions;
    }

    public int getDeletions() {
        return deletions;
    }
}
