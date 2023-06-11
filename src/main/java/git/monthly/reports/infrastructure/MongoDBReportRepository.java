package git.monthly.reports.infrastructure;

import com.mongodb.MongoClientException;
import git.monthly.reports.domain.entities.GitUserMonthlyReport;
import git.monthly.reports.domain.exceptions.ReportClientConnectionException;
import git.monthly.reports.domain.interfaces.OrgMonthlyReportRepository;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class MongoDBReportRepository implements OrgMonthlyReportRepository {
    private static MongoDBClient mongoClient;

    public MongoDBReportRepository(MongoDBClient mongoClient) {
        this.mongoClient = mongoClient;
    }


    @Override
    public boolean findOrgMontlyReport(String orgName, String date) throws ReportClientConnectionException {
        List<GitUserMonthlyReport> reports;
        try {
            reports = mongoClient.findByOrgNameDate(orgName,date);
        } catch (MongoClientException e) {
            throw new ReportClientConnectionException(e.getMessage());
        }
        return !reports.isEmpty();
    }

    @Override
    public List<GitUserMonthlyReport> getOrgMonthlyReport(String orgName, String date) throws ReportClientConnectionException {
        System.out.println("Fetching Organization Monthly Report Data from MongoDB");
        List<GitUserMonthlyReport> reports;

        try {
            reports = mongoClient.findByOrgNameDate(orgName,date);
        } catch (MongoClientException e) {
            throw new ReportClientConnectionException(e.getMessage());
        }

        return reports;
    }

    @Override
    public void saveOrgMonthlyReport(List<GitUserMonthlyReport> orgMonthlyReport) throws ReportClientConnectionException {
        System.out.println("Saving Organization Monthly Report Data to MongoDB");
        try {
            mongoClient.saveAll(orgMonthlyReport);
        } catch (MongoClientException e) {
            throw new ReportClientConnectionException(e.getMessage());
        }
    }
}
