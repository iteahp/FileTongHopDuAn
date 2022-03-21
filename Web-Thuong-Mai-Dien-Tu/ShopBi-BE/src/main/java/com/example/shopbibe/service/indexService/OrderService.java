package com.example.shopbibe.service.indexService;

import com.example.shopbibe.model.Orders;

import com.example.shopbibe.repository.PmRepo.IProductRepository;
import com.example.shopbibe.repository.indexRepo.IOrderDetailRepo;
import com.example.shopbibe.repository.indexRepo.IOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class OrderService implements IOrderImpl{
    @Autowired
    IOrderRepo iOrderRepo;
    @Autowired
    IOrderDetailRepo iOrderDetailRepo;
    @Autowired
    IProductRepository iProductRepository;
    @Override
    public void save(Orders orders) {
        orders.setModifyAt(new Date());
        iOrderRepo.save(orders);
    }

    @Override
    public List<Orders> findAllByStatus(long id,String status) {
        return iOrderRepo.findAllOrderByUserIdAndStatuss(id,status);
    }

    @Override
    public List<Orders> findAllByUserPmAndUserBuyer(long idPm, long idBuyer) {
        return iOrderRepo.findAllByUserPmAndUserBuyer(idPm,idBuyer);
    }
    @Override
    public Orders findByID(long id) {
        return iOrderRepo.findById(id).get();
    }

    @Override
    public List<Orders> findAllOrder(long id) {
        return iOrderRepo.findAllOrder(id);
    }


    @Override
    public List<Orders> searchByEmailUser(long idPm, String emailFind) {
        return iOrderRepo.searchByEmailUser(idPm,emailFind);
    }


    @Override
    public Page<Orders> findAllByUserBuyer(String username, Pageable pageable) {
        return iOrderRepo.findAllByUserBuyer(username,pageable);
    }

    @Override
    public double avgRateUser(Long id) {
        return iOrderRepo.avgRateUser(id);
    }

    @Override
    public List<Orders> findOrdersByIdAndStatus(Long id) {
        return iOrderRepo.findOrdersByIdAndStatus(id);
    }

    @Override
    public List<Orders> findOrdersByIdAndStatusToday(Long id) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return iOrderRepo.findOrdersByIdAndStatusToday(id,LocalDate.now().format(formatter));
    }

}
