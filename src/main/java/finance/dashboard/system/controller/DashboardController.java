package finance.dashboard.system.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import finance.dashboard.system.dto.ResponseStructure;
import finance.dashboard.system.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService service;

    // Total Credit (Income)
 // ✅ Income
    @GetMapping("/income/{userId}")
    public ResponseEntity<ResponseStructure<Double>> getIncome(@PathVariable Integer userId) {
        return service.getTotalIncome(userId);
    }

    // ✅ Expense
    @GetMapping("/expense/{userId}")
    public ResponseEntity<ResponseStructure<Double>> getExpense(@PathVariable Integer userId) {
        return service.getTotalExpense(userId);
    }

    //Net Balance
    @GetMapping("/balance/{userId}")
    public ResponseEntity<ResponseStructure<Double>> getNetBalance(
            @PathVariable Integer userId) {

        return service.getNetBalance(userId);
    }

    // Category-wise Summary
    @GetMapping("/category/{userId}")
    public ResponseEntity<ResponseStructure<Map<String, Double>>> getCategorySummary(
            @PathVariable Integer userId) {

        return service.getCategorySummary(userId);
    }
    
    
    //Monthly Summary
    @GetMapping("/monthly/{userId}")
    public ResponseEntity<ResponseStructure<Map<Integer, Double>>> getMonthlySummary(
            @PathVariable Integer userId) {

        return service.getMonthlySummary(userId);
    }
}
