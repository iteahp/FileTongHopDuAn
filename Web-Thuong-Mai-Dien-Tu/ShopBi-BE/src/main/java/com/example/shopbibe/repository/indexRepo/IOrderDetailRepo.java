package com.example.shopbibe.repository.indexRepo;

import com.example.shopbibe.model.OrderDetail;
import com.example.shopbibe.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderDetailRepo extends JpaRepository<OrderDetail,Long> {
    @Query(nativeQuery = true,value = "select order_detail.* from order_detail join orders on orders.id=order_detail.orders_id  where (order_detail.product_id in (select product.id from product where user_id =:userId)) and orders_id =:orderId ")
        List<OrderDetail> findAllOrderDetailByOrderId(@Param("userId") long userId,@Param("orderId") long orderId);

    @Query(nativeQuery = true,value = "SELECT * FROM shopbi.order_detail where orders_id =:id")

    List<OrderDetail> findAllOrderById(@Param("id") long id);
    @Query(nativeQuery = true,value = "SELECT shopbi.order_detail.* FROM shopbi.order_detail join shopbi.orders on orders_id = order_detail.orders_id  join users on orders.user_buyer_id = users.id where users.username = :username")
    List<OrderDetail> findAllByUsername(@Param("username") String username);

    List<OrderDetail> findAllByOrders_Id(Long id);

    @Query(nativeQuery = true,value = "SELECT * FROM shopbi.order_detail where orders_id =:idOrder")
    List<OrderDetail> listOrderDetail(@Param("idOrder") long idOrder);
}
