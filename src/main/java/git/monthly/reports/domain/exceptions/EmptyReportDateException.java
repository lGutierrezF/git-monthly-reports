package git.monthly.reports.domain.exceptions;

public class EmptyReportDateException extends Exception{
    public EmptyReportDateException(String s) { super("Please input a date to make a report on."); }

}
