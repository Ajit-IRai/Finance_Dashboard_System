package finance.dashboard.system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import finance.dashboard.system.dto.ResponseStructure;
import finance.dashboard.system.entity.Transaction;
import finance.dashboard.system.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    // save transaction
    @PostMapping 
    public ResponseEntity<ResponseStructure<Transaction>> saveTransaction(@RequestBody Transaction transaction) {
        return service.saveTransaction(transaction);
    }

    //get transaction by id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Transaction>> getTransactionById(@PathVariable Integer id) {
        return service.getTransactionById(id);
    }

    // get all transaction by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseStructure<List<Transaction>>> getTransactionsByUser(@PathVariable Integer userId) {
        return service.getTransactionsByUser(userId);
    }

    // delete transaction
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteTransaction(@PathVariable Integer id) {
        return service.deleteTransaction(id);
    }
}
