package git.monthly.reports.domain.services;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public final class MonthConstraintsCalculator {
    public MonthConstraintsCalculator() {
    }

    public List<LocalDate> execute(String date) {
        String[] parts = date.split("-");

        String stringYear = parts[0];
        String stringMonth = parts[1];
        List<LocalDate> monthConstraints = new ArrayList<>();

        int year = Integer.parseInt(stringYear);
        int month = Integer.parseInt(stringMonth);

        YearMonth yearMonth = YearMonth.of(year, month);

        LocalDate startDate = yearMonth.atDay(1);

        LocalDate endDate = yearMonth.atEndOfMonth();
        monthConstraints.add(startDate);
        monthConstraints.add(endDate);
        return monthConstraints;
    }

    public boolean isMonthEnded(String date) {
        String[] parts = date.split("-");

        String stringYear = parts[0];
        String stringMonth = parts[1];

        int year = Integer.parseInt(stringYear);
        int month = Integer.parseInt(stringMonth);

        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate endDate = yearMonth.atEndOfMonth();

        LocalDate currentDate = LocalDate.now();

        return endDate.isBefore(currentDate);
    }

}
