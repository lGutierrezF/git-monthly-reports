package git.monthly.reports.domain.entities;

import java.util.ArrayList;
import java.util.List;

public class GitUser {
    private String userName;
    private int executedPR;
    private int reviwedPR;
    private List<PRComment> comments;
    private List<Commit> commits;

    public GitUser(String userName) {
        this.userName = userName;
        this.comments = new ArrayList<>();
        this.commits = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public int getExecutedPR() {
        return executedPR;
    }

    public void setExecutedPR(int executedPR) {
        this.executedPR = executedPR;
    }

    public int getReviwedPR() {
        return reviwedPR;
    }

    public void setReviwedPR(int reviwedPR) {
        this.reviwedPR = reviwedPR;
    }

    public List<PRComment> getComments() {
        return comments;
    }

    public void setComments(List<PRComment> comments) {
        this.comments = comments;
    }

    public int getCommentCount() {
        return comments.size();
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }

    public int getCommitCount() {
        return commits.size();
    }
}
