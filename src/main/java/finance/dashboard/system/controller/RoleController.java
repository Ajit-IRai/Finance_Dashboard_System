package finance.dashboard.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import finance.dashboard.system.dto.ResponseStructure;
import finance.dashboard.system.entity.Role;
import finance.dashboard.system.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService service;

    // save role
    @PostMapping
    public ResponseEntity<ResponseStructure<Role>> saveRole(@RequestBody Role role) {
        return service.saveRole(role);
    }

    // get all role
    @GetMapping
    public ResponseEntity<ResponseStructure<List<Role>>> getAllRoles() {
        return service.getAllRoles();
    }

    // get by id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Role>> getRoleById(@PathVariable Integer id) {
        return service.getRoleById(id);
    }
}
