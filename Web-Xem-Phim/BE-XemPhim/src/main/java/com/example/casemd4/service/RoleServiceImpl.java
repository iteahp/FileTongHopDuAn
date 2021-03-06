package com.example.casemd4.service;

import com.example.casemd4.model.Role;
import com.example.casemd4.model.RoleName;
import com.example.casemd4.repository.IRoleRepository;
import com.example.casemd4.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    IRoleRepository roleRepository;
    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}
