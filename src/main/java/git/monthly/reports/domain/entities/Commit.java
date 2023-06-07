package git.monthly.reports.domain.entities;

public class Commit {
    private String SHA;
    private String author;
    private CommitStats stats;

    public Commit(String SHA, String author) {
        this.SHA = SHA;
        this.author = author;
    }

    public String getSHA() {
        return SHA;
    }

    public void setSHA(String SHA) {
        this.SHA = SHA;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public CommitStats getStats() {
        return stats;
    }

    public void setStats(CommitStats stats) {
        this.stats = stats;
    }
}
