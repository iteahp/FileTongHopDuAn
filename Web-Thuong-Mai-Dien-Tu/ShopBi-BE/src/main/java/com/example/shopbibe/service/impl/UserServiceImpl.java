package com.example.shopbibe.service.impl;

import com.example.shopbibe.model.User;
import com.example.shopbibe.repository.IUserRepository;
import com.example.shopbibe.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserRepository userRepository;
    @Override
    public Optional<User> findByUsername(String name) {
        return userRepository.findByUsername(name);
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
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findUserByID(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> findAllByEmailContaining(String email) {
        return userRepository.findAllByEmailContaining(email);
    }

    @Override
    public List<User> findAllUserBuyInPm(long id) {
        return userRepository.findAllUserBuyInPm(id);
    }

    @Override
    public List<User> findAllUserBuyInPmAndEmail(long id, String email) {
        return userRepository.findAllUserBuyInPmAndEmail(id,email);
    }

}
