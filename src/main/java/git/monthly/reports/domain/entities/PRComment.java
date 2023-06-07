package git.monthly.reports.domain.entities;

public class PRComment {
    private String commentBody;

    public PRComment(String commentBody) {
        this.commentBody = commentBody;
    }

    public int getCommentLength(){
        return this.commentBody.length();
    }
}
