package com.example.casemd4.service;

import com.example.casemd4.model.Role;
import com.example.casemd4.model.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName name);
}
