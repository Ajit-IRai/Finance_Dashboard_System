package finance.dashboard.system.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import finance.dashboard.system.entity.User;
import finance.dashboard.system.repository.UserRepository;

@Repository
public class UserDao {

    @Autowired
    private UserRepository repository;

    public User saveUser(User user) {
        return repository.save(user);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User updateUser(User user) {
		return repository.save(user);  
	}  
    
    public Optional<User> getUserById(Integer id) {
        return repository.findById(id);
    }

    public String deleteUser(Integer id) {
		return "The User is deleted by id "+id;  
	}
}