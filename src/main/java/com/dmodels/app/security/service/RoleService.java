package com.dmodels.app.security.service;

import com.dmodels.app.security.model.Role;
import com.dmodels.app.security.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    public Role findRoleByName(String name){ return roleRepository.findRoleByName(name); }
}
