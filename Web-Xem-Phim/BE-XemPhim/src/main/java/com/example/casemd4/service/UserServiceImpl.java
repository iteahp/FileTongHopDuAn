package com.example.casemd4.service;

import com.example.casemd4.model.Users;
import com.example.casemd4.repository.IUserRepository;
import com.example.casemd4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserRepository userRepository;
    @Override
    public Optional<Users> findByUsername(String name) {
        return userRepository.findByUsername(name);
    }
    public Users findUserName(String name){
        return userRepository.findByUsername(name).get();
    }
    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Users save(Users users) {
        return userRepository.save(users);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<Users> findById(Long id) {
        return userRepository.findById(id);
    }
}
