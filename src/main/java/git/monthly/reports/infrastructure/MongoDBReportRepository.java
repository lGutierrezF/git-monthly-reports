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
        return mongoClient.findByOrgNameDate(orgName,date);
    }

    @Override
    public void saveOrgMonthlyReport(List<GitUserMonthlyReport> orgMonthlyReport) {
        mongoClient.saveAll(orgMonthlyReport);
    }
}
