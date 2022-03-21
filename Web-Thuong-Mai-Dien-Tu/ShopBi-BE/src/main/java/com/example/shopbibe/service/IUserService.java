package com.example.shopbibe.service;



import com.example.shopbibe.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<User> findByUsername(String name); //Tim kiem User co ton tai trong DB khong?
    Boolean existsByUsername(String username); //username da co trong DB chua, khi tao du lieu
    Boolean existsByEmail(String email); //email da co trong DB chua
    User save(User user);
    Page<User> findAll(Pageable pageable);
    User findUserByID(long id);
    List<User> findAllByEmailContaining(String email);

    List<User> findAllUserBuyInPm(long id);

    List<User> findAllUserBuyInPmAndEmail(long id, String email);
}
