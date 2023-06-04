package git.monthly.reports.domain.entities;

public class PRComment {
    private String id;
    private String commentBody;

    public PRComment(String id, String commentBody) {
        this.id = id;
        this.commentBody = commentBody;
    }

    public int getCommentLength(){
        return this.commentBody.length();
    }
}
