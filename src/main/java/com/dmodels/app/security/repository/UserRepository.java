package com.dmodels.app.security.repository;

import com.dmodels.app.security.model.Role;
import com.dmodels.app.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByRoleSetContains(Role role);

}
