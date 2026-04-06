package finance.dashboard.system.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import finance.dashboard.system.dao.TransactionDao;
import finance.dashboard.system.dto.ResponseStructure;
import finance.dashboard.system.entity.Transaction;
import finance.dashboard.system.entity.User;
import finance.dashboard.system.exception.IdNotFoundException;
import finance.dashboard.system.exception.NoRecordFoundException;
import finance.dashboard.system.exception.ResourceNotFoundException;
import finance.dashboard.system.repository.TransactionRepository;
import finance.dashboard.system.repository.UserRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionDao dao;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private TransactionRepository transactionRepository;

    // save transaction
    public ResponseEntity<ResponseStructure<Transaction>> saveTransaction(Transaction transaction) {

        ResponseStructure<Transaction> structure = new ResponseStructure<>();

        // ✅ Null check
        if (transaction.getUser() == null || transaction.getUser().getId() == null) {
        	System.out.println("Transaction user: " + transaction.getUser());
            throw new RuntimeException("User id is required");
        }

        Integer userId = transaction.getUser().getId();

        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {

            transaction.setUser(optionalUser.get());

            Transaction saved = dao.saveTransaction(transaction);

            structure.setStatusCode(HttpStatus.CREATED.value());
            structure.setMessage("Transaction created successfully");
            structure.setData(saved);

            return new ResponseEntity<>(structure, HttpStatus.CREATED);

        } else {
            throw new RuntimeException("User not found with id: " + userId);
        }
    }

    // get transaction by id
    public ResponseEntity<ResponseStructure<Transaction>> getTransactionById(Integer id) {

        ResponseStructure<Transaction> structure = new ResponseStructure<>();

        Optional<Transaction> optional = dao.findById(id);

        if (optional.isPresent()) {

            Transaction transaction = optional.get();

            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Transaction fetched successfully");
            structure.setData(transaction);

            return new ResponseEntity<ResponseStructure<Transaction>>(structure, HttpStatus.OK);

        } else {
            throw new IdNotFoundException("Transaction not found with id: " + id);
        }
    }

    // get all transaction by user
    public ResponseEntity<ResponseStructure<List<Transaction>>> getTransactionsByUser(Integer userId) {

        ResponseStructure<List<Transaction>> structure = new ResponseStructure<>();

        Optional<User> optionalUser = userRepository.findById(userId);

        if (!optionalUser.isPresent()) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }

        List<Transaction> list = dao.findByUserId(userId);

        if (list.isEmpty()) {
            throw new NoRecordFoundException("No transactions found for user with id: " + userId);
        }

        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Transactions fetched successfully");
        structure.setData(list);

        return new ResponseEntity<ResponseStructure<List<Transaction>>>(structure, HttpStatus.OK);
    }

    // delete transaction
    public ResponseEntity<ResponseStructure<String>> deleteTransaction(Integer id) {

        ResponseStructure<String> structure = new ResponseStructure<>();
  
        Optional<Transaction> optional = dao.findById(id);

        if (optional.isPresent()) {

           transactionRepository.delete(optional.get());
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Transaction deleted successfully");
            structure.setData("Deleted");

            return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);

        } else {
            throw new NoRecordFoundException("Transaction not found with id: " + id);
        }
    }
}
