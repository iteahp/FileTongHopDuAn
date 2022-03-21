package com.example.shopbibe.repository.indexRepo;

import com.example.shopbibe.model.Orders;

import com.example.shopbibe.model.Product;
import com.example.shopbibe.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IOrderRepo extends JpaRepository<Orders,Long> {
    @Query(nativeQuery = true,value = "SELECT * FROM shopbi.orders where orders.user_pm_id =:id and orders.status =:status")
    List<Orders> findAllOrderByUserIdAndStatus(@Param("id") long id,@Param("status") String status );
    @Query(nativeQuery = true,value = "SELECT * FROM shopbi.orders where orders.user_pm_id =:id and orders.status =:status")
    List<Orders> findAllOrderByUserIdAndStatuss( @Param("id") long id, @Param("status") String status );

    @Query(nativeQuery = true,value = "SELECT * FROM shopbi.orders where orders.user_pm_id =:id")
    List<Orders> findAllOrder( @Param("id") long id);
    @Query(nativeQuery = true,value = "SELECT * FROM shopbi.orders where user_pm_id =:idPm and user_buyer_id in(select users.id from users where users.email like concat('%',:emailFind,'%') );")
    List<Orders> searchByEmailUser(@Param("idPm") long idPm,@Param("emailFind") String emailFind);


    @Query(nativeQuery = true,value = "SELECT * FROM shopbi.orders where user_pm_id =:idPm and user_buyer_id =:idBuyer")
    List<Orders> findAllByUserPmAndUserBuyer(@Param("idPm") long idPm, @Param("idBuyer") long idBuyer);

    @Query(nativeQuery = true,value = "SELECT * FROM shopbi.order_detail where orders_id =:id")
    List<Orders> findAllOrderById(@Param("id") long id);

    @Query(nativeQuery = true,value = "SELECT avg(rate_id) FROM shopbi.orders where user_pm_id = :id ")
    double avgRateUser(@Param("id") Long id);

    @Query(nativeQuery = true,value = "SELECT * FROM shopbi.orders where user_pm_id = :id and status = 'done' and create_at > :createAt ")
    List<Orders> findOrdersByIdAndStatusToday(@Param("id") Long id, @Param("createAt") String createAt);

    @Query(nativeQuery = true,value = "SELECT * FROM shopbi.orders where user_pm_id = :id and status = 'done' ")
    List<Orders> findOrdersByIdAndStatus(@Param("id") Long id);




    @Query(value = "select o from Orders o where o.userBuyer.username = :username order by o.id desc")
    Page<Orders> findAllByUserBuyer(@Param("username") String username, Pageable pageable);
}
