package com.example.shopbibe.service;


import com.example.shopbibe.model.Role;
import com.example.shopbibe.model.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName name);
}
