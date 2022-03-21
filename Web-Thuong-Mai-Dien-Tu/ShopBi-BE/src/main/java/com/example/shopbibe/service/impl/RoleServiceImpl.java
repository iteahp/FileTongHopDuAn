package com.example.shopbibe.service.impl;


import com.example.shopbibe.model.Role;
import com.example.shopbibe.model.RoleName;
import com.example.shopbibe.repository.IRoleRepository;
import com.example.shopbibe.service.IRoleService;
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
