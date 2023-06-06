package git.monthly.reports.infrastructure;

import git.monthly.reports.domain.entities.GitUserMonthlyReport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MongoDBClient extends MongoRepository<GitUserMonthlyReport, String> {
    @Query("{orgName:'?0',date: '?1'}")
    public List<GitUserMonthlyReport> findByOrgNameDate(String orgName, String date);
}
