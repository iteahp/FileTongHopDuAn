package com.example.casemd4.service;

import com.example.casemd4.model.Users;

import java.util.Optional;

public interface IUserService {
    Optional<Users> findByUsername(String name); //Tim kiem User co ton tai trong DB khong?
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Users save(Users users);
    void deleteById(Long id);
    Optional<Users> findById(Long id);
}
