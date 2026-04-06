package finance.dashboard.system.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import finance.dashboard.system.dao.UserDao;
import finance.dashboard.system.dto.ResponseStructure;
import finance.dashboard.system.entity.Role;
import finance.dashboard.system.entity.User;
import finance.dashboard.system.exception.IdNotFoundException;
import finance.dashboard.system.exception.ResourceNotFoundException;
import finance.dashboard.system.repository.RoleRepository;
import finance.dashboard.system.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserDao dao;
    
    @Autowired
   private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    


    // save user
    public ResponseEntity<ResponseStructure<User>> saveUser(User user) {

        ResponseStructure<User> structure = new ResponseStructure<>();
        
        if(userRepository.existsByEmail(user.getEmail())) {
    		throw new ResourceNotFoundException("The email is already existing in the DB");
    		}
        
        Integer roleId=user.getRole().getId();
        Optional<Role> optionalRole = roleRepository.findById(roleId);
        if (!optionalRole.isPresent()) {
            throw new RuntimeException("Role not found");
        }

        user.setRole(optionalRole.get());
        
        structure.setStatusCode(HttpStatus.CREATED.value());
        structure.setMessage("User created successfully");
        structure.setData(dao.saveUser(user));

        return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.CREATED);
    }

    //get all users
    public ResponseEntity<ResponseStructure<List<User>>> getAllUsers() {

        List<User> users = dao.getAllUsers();

        ResponseStructure<List<User>> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Users fetched successfully");
        structure.setData(users);

        return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.OK);
    }

    //get user by id
    public ResponseEntity<ResponseStructure<User>> getUserById(Integer id) {

        Optional<User> optional = dao.getUserById(id);

        if (optional.isPresent()) {

            ResponseStructure<User> structure = new ResponseStructure<>();
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("User found");
            structure.setData(optional.get());

            return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);

        } else {
            throw new IdNotFoundException("User not found");
        }
    }

    // update operation
    	public ResponseEntity<ResponseStructure<User>> updateUser(User user){
		
		if(user.getId()==null) {
			throw new IdNotFoundException("Id must be passed to update the User");
		}
		Optional<User> opt=userRepository.findById(user.getId());
		ResponseStructure<User> response=new ResponseStructure<User>();
		
		if(opt.isPresent()) {
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("User record with id "+user.getId()+" is updated");
			response.setData(dao.updateUser(user));
			return new ResponseEntity<ResponseStructure<User>>(response,HttpStatus.FOUND);
		}    
		else {
			throw new IdNotFoundException("User is not exist with id "+user.getId()+"in the DB"); 
		}  
	}

//delete operation
    	public ResponseEntity<ResponseStructure<String>> deleteUser(Integer id){
    		Optional<User> opt=userRepository.findById(id);
    		ResponseStructure<String> response=new ResponseStructure<String>();
    		  
    		if(opt.isPresent()) {
    			userRepository.delete(opt.get());
    			response.setStatusCode(HttpStatus.OK.value());
    			response.setMessage("The record is deleted successfully by id ="+id);
    			response.setData("Deleted from DB");
    			return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.OK);
    		}
    		else
    			throw new IdNotFoundException("The id is not exist in the DB");
    	} 
}