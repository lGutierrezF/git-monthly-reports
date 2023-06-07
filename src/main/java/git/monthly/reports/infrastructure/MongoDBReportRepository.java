package git.monthly.reports.infrastructure;

import git.monthly.reports.domain.entities.GitUserMonthlyReport;
import git.monthly.reports.domain.interfaces.OrgMonthlyReportRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MongoDBReportRepository implements OrgMonthlyReportRepository {
    private static MongoDBClient mongoClient;

    public MongoDBReportRepository(MongoDBClient mongoClient) {
        this.mongoClient = mongoClient;
    }


    @Override
    public boolean findOrgMontlyReport(String orgName, String date) {
        var reports = mongoClient.findByOrgNameDate(orgName,date);
        mongoClient.findByOrgNameDate(orgName,date);
        return !reports.isEmpty();
    }

    @Override
    public List<GitUserMonthlyReport> getOrgMonthlyReport(String orgName, String date) {
        System.out.println("Fetching Organization Monthly Report Data from MongoDB");
        return mongoClient.findByOrgNameDate(orgName,date);
    }

    @Override
    public void saveOrgMonthlyReport(List<GitUserMonthlyReport> orgMonthlyReport) {
        System.out.println("Saving Organization Monthly Report Data to MongoDB");
        mongoClient.saveAll(orgMonthlyReport);
    }
}
