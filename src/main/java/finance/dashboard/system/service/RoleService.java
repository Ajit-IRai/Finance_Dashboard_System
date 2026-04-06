package finance.dashboard.system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import finance.dashboard.system.dao.RoleDao;
import finance.dashboard.system.dto.ResponseStructure;
import finance.dashboard.system.entity.Role;
import finance.dashboard.system.exception.ResourceNotFoundException;
import finance.dashboard.system.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleDao dao;
    
    @Autowired
    private RoleRepository roleRepository;

    // save role
    public ResponseEntity<ResponseStructure<Role>> saveRole(Role role) {

        if (roleRepository.existsByName(role.getName())) {
            throw new DataIntegrityViolationException("Role already exists");
        }

        Role saved = dao.saveRole(role);

        ResponseStructure<Role> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.CREATED.value());
        structure.setMessage("Role created successfully");
        structure.setData(saved);

        return new ResponseEntity<>(structure, HttpStatus.CREATED);
    }

    // get all roles
    public ResponseEntity<ResponseStructure<List<Role>>> getAllRoles() {

        List<Role> roles = dao.getAllRoles();

        ResponseStructure<List<Role>> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Roles fetched successfully");
        structure.setData(roles);

        return new ResponseEntity<>(structure, HttpStatus.OK);
    }

    // get role by id
    public ResponseEntity<ResponseStructure<Role>> getRoleById(Integer id) {

        Optional<Role> optional = dao.getRoleById(id);

        if (optional.isPresent()) {

            ResponseStructure<Role> structure = new ResponseStructure<>();
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Role found");
            structure.setData(optional.get());

            return new ResponseEntity<>(structure, HttpStatus.OK);

        } else {
            throw new ResourceNotFoundException("Role not found");
        }
    }
}
