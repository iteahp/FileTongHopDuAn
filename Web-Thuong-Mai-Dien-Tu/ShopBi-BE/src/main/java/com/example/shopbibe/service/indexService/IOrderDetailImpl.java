package com.example.shopbibe.service.indexService;

import com.example.shopbibe.model.OrderDetail;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderDetailImpl {
    void save(OrderDetail orderDetail);
    List<OrderDetail> findAllOrderDetailByOrderId( long userId, long orderId);
    List<OrderDetail> findAllOrderById(long id);
    List<OrderDetail> findAllByOrders_Id(Long id);
    List<OrderDetail> listOrderDetail( long idOrder);
}
