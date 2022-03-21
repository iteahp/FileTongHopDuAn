package com.example.shopbibe.repository;


import com.example.shopbibe.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String name); //Tim kiem User co ton tai trong DB khong?
    Boolean existsByUsername(String username); //username da co trong DB chua, khi tao du lieu
    Boolean existsByEmail(String email); //email da co trong DB chua

    Page<User> findAll(Pageable pageable);

    List<User> findAllByEmailContaining(String email);

    @Query(nativeQuery = true,value = "SELECT * FROM shopbi.users where users.id in( SELECT user_buyer_id FROM shopbi.orders where user_pm_id =:id group by user_buyer_id)")
    List<User> findAllUserBuyInPm(@Param("id") long id);

    @Query(nativeQuery = true,value = "SELECT * FROM shopbi.users where id in (SELECT user_buyer_id FROM shopbi.orders where user_pm_id =:id and email like concat('%',:email,'%') group by user_buyer_id)")
    List<User> findAllUserBuyInPmAndEmail(@Param("id") long id,@Param("email")String email);


}
