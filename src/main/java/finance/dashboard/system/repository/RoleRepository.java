package finance.dashboard.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import finance.dashboard.system.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	boolean existsByName(String name);
    
}
