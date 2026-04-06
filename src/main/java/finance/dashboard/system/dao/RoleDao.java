package finance.dashboard.system.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import finance.dashboard.system.entity.Role;
import finance.dashboard.system.repository.RoleRepository;

@Repository
public class RoleDao {

    @Autowired
    private RoleRepository repo;

    public Role saveRole(Role role) {
        return repo.save(role);  
    }

    public List<Role> getAllRoles() {
        return repo.findAll();
    }

    public Optional<Role> getRoleById(Integer id) {
        return repo.findById(id);
    }

}