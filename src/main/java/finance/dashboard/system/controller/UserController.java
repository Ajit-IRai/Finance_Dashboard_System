package finance.dashboard.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import finance.dashboard.system.dto.ResponseStructure;
import finance.dashboard.system.entity.User;
import finance.dashboard.system.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    // save user
    @PostMapping
    public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    // get all
    @GetMapping
    public ResponseEntity<ResponseStructure<List<User>>> getAllUsers() {
        return service.getAllUsers();
    }

    //get by id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<User>> getUserById(@PathVariable Integer id) {
        return service.getUserById(id);
    }

    // update
    @PutMapping("/api") 
    public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteUser(@PathVariable Integer id) {
        return service.deleteUser(id);
    }
}
