package git.monthly.reports.domain.exceptions;

public class EmptyOrganizationRepoException extends Exception{
    public EmptyOrganizationRepoException() { super("Organization does not have any repositories."); }
}
