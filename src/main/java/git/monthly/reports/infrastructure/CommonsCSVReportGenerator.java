package git.monthly.reports.infrastructure;

import git.monthly.reports.domain.entities.GitUserMonthlyReport;
import git.monthly.reports.domain.entities.HeaderCSV;
import git.monthly.reports.domain.interfaces.ConvertMonthlyReportToCSV;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class CommonsCSVReportGenerator implements ConvertMonthlyReportToCSV {
   CSVFormat csvFormat;


    public CommonsCSVReportGenerator() {
        this.csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HeaderCSV.class)
                .build();
    }

    @Override
    public void convertReportToCSV(List<GitUserMonthlyReport> monthlyReports, String orgName, String date) {
        String csvFilePath = "./reports/"+orgName+"_"+date+"_report.csv";
        try (FileWriter fileWriter = new FileWriter(csvFilePath);
                CSVPrinter csvPrinter = new CSVPrinter(fileWriter, csvFormat)) {
            for (GitUserMonthlyReport report:monthlyReports) {
                try {
                    csvPrinter.printRecord(report.getOrgName(), report.getTeamName(), report.getuserName(),
                            date, report.getreviewedPRs(), report.getexecutedPRs(), report.getcommentAvgLength(),
                            report.getTotalLines(), report.getLinesAdded(), report.getLinesDeleted());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            csvPrinter.flush();
            fileWriter.flush();
            System.out.println("CSV file created successfully.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}