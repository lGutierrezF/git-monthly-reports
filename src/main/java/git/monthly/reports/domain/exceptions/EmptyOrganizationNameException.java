package git.monthly.reports.domain.exceptions;

public class EmptyOrganizationNameException extends Exception{
    public EmptyOrganizationNameException() { super("Organization name cannot be blank."); }
}
