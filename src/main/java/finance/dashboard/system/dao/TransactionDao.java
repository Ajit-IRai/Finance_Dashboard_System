package finance.dashboard.system.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import finance.dashboard.system.entity.Transaction;
import finance.dashboard.system.repository.TransactionRepository;

@Repository
public class TransactionDao {

    @Autowired
    private TransactionRepository repo;

    public Transaction saveTransaction(Transaction transaction) {
        return repo.save(transaction);
    }

    public Optional<Transaction> findById(Integer id) {
        return repo.findById(id);
    }

    public List<Transaction> findByUserId(Integer userId) {
        return repo.findByUserId(userId);
    }

    public String deleteTransaction(Integer id) {
        return "The transaction is deleted by id"+id;
    }
}
