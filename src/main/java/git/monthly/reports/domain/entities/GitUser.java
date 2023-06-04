package git.monthly.reports.domain.entities;

import java.util.List;

public class GitUser {
    private String userName;
    private int executedPR;
    private int reviwedPR;
    private List<PRComment> comments;
    private List<Commit> commits;

}
