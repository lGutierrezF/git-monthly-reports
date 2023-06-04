package git.monthly.reports.domain.entities;

import java.util.List;

public class GitTeam {
    private String teamName;
    private List<GitUser> teamMembers;

    public GitTeam(String teamName) {
        this.teamName = teamName;
    }

    public List<GitUser> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<GitUser> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public String getTeamName() {
        return teamName;
    }
}
