package finance.dashboard.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import finance.dashboard.system.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

	
	List<Transaction> findByUserId(Integer userId);

	    
	 @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.user.id = :userId AND t.type = 'INCOME'")
	    Double getTotalIncome(Integer userId);

	    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.user.id = :userId AND t.type = 'EXPENSE'")
	    Double getTotalExpense(Integer userId);

	 
	    @Query("SELECT t.category, SUM(t.amount) FROM Transaction t WHERE t.user.id = :userId GROUP BY t.category")
	    List<Object[]> getCategorySummary(Integer userId);

	    


	    @Query("SELECT MONTH(t.date), SUM(t.amount) FROM Transaction t WHERE t.user.id = :userId GROUP BY MONTH(t.date)")
	    List<Object[]> getMonthlySummary(Integer userId);
	}

