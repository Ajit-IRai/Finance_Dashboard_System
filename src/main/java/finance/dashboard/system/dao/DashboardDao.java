package finance.dashboard.system.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import finance.dashboard.system.repository.TransactionRepository;

@Repository
public class DashboardDao {

    @Autowired
    private TransactionRepository repo;

    public Double getTotalIncome(Integer userId) {
        return repo.getTotalIncome(userId);
    }

    public Double getTotalExpense(Integer userId) {
        return repo.getTotalExpense(userId);
    }

    public List<Object[]> getCategorySummary(Integer userId) {
        return repo.getCategorySummary(userId);
    }

    public List<Object[]> getMonthlySummary(Integer userId) {
        return repo.getMonthlySummary(userId);
    }
}
