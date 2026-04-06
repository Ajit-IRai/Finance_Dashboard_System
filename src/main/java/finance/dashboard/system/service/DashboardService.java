package finance.dashboard.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import finance.dashboard.system.dao.DashboardDao;
import finance.dashboard.system.dto.ResponseStructure;
import finance.dashboard.system.entity.User;
import finance.dashboard.system.repository.UserRepository;

@Service
public class DashboardService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DashboardDao dao;
	
	public ResponseEntity<ResponseStructure<Double>> getTotalIncome(Integer userId) {

	    ResponseStructure<Double> structure = new ResponseStructure<>();

	    Optional<User> optional = userRepository.findById(userId);

	    if (!optional.isPresent()) {
	        throw new RuntimeException("User not found with id: " + userId);
	    }

	    Double income = dao.getTotalIncome(userId);

	    if (income == null) income = 0.0;

	    structure.setStatusCode(HttpStatus.OK.value());
	    structure.setMessage("Total income fetched successfully");
	    structure.setData(income);

	    return new ResponseEntity<>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Double>> getTotalExpense(Integer userId) {

	    ResponseStructure<Double> structure = new ResponseStructure<>();

	    Optional<User> optional = userRepository.findById(userId);

	    if (!optional.isPresent()) {
	        throw new RuntimeException("User not found with id: " + userId);
	    }

	    Double expense = dao.getTotalExpense(userId);

	    if (expense == null) expense = 0.0;

	    structure.setStatusCode(HttpStatus.OK.value());
	    structure.setMessage("Total expense fetched successfully");
	    structure.setData(expense);

	    return new ResponseEntity<>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Double>> getNetBalance(Integer userId) {

	    ResponseStructure<Double> structure = new ResponseStructure<>();

	    Optional<User> optional = userRepository.findById(userId);

	    if (!optional.isPresent()) {
	        throw new RuntimeException("User not found with id: " + userId);
	    }

	    Double income = dao.getTotalIncome(userId);
	    Double expense = dao.getTotalExpense(userId);

	    if (income == null) income = 0.0;
	    if (expense == null) expense = 0.0;

	    Double balance = income - expense;

	    structure.setStatusCode(HttpStatus.OK.value());
	    structure.setMessage("Balance fetched successfully");
	    structure.setData(balance);

	    return new ResponseEntity<>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Map<String, Double>>> getCategorySummary(Integer userId) {

	    ResponseStructure<Map<String, Double>> structure = new ResponseStructure<>();

	    Optional<User> optional = userRepository.findById(userId);

	    if (!optional.isPresent()) {
	        throw new RuntimeException("User not found with id: " + userId);
	    }

	    List<Object[]> result = dao.getCategorySummary(userId);

	    Map<String, Double> data = new HashMap<>();

	    for (Object[] row : result) {
	        String category = (String) row[0];
	        Double amount = (Double) row[1];
	        data.put(category, amount);
	    }

	    structure.setStatusCode(HttpStatus.OK.value());
	    structure.setMessage("Category summary fetched successfully");
	    structure.setData(data);

	    return new ResponseEntity<>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Map<Integer, Double>>> getMonthlySummary(Integer userId) {

	    ResponseStructure<Map<Integer, Double>> structure = new ResponseStructure<>();

	    Optional<User> optional = userRepository.findById(userId);

	    if (!optional.isPresent()) {
	        throw new RuntimeException("User not found with id: " + userId);
	    }

	    List<Object[]> result = dao.getMonthlySummary(userId);

	    Map<Integer, Double> data = new HashMap<>();

	    for (Object[] row : result) {
	        Integer month = (Integer) row[0];
	        Double amount = (Double) row[1];
	        data.put(month, amount);
	    }

	    structure.setStatusCode(HttpStatus.OK.value());
	    structure.setMessage("Monthly summary fetched successfully");
	    structure.setData(data);

	    return new ResponseEntity<>(structure, HttpStatus.OK);
	}
}
