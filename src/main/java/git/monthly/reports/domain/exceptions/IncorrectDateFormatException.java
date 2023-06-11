package git.monthly.reports.domain.exceptions;

public class IncorrectDateFormatException extends Exception{
    public IncorrectDateFormatException() {super("Incorrect date format. Please input the date in YYYY-mm format.");}
}
