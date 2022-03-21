package com.example.shopbibe.controller.PmController;

import com.example.shopbibe.model.OrderDetail;
import com.example.shopbibe.model.Orders;
import com.example.shopbibe.model.User;
import com.example.shopbibe.service.IUserService;
import com.example.shopbibe.service.indexService.IOrderDetailImpl;
import com.example.shopbibe.service.indexService.IOrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/orderInPm")
public class OrderInPmController {
    @Autowired
    IOrderImpl iOrder;
    @Autowired
    IOrderDetailImpl iOrderDetail;
    @Autowired
    IUserService iUserService;
    // ham tra ve list order theo userId va status truyen vao

    @GetMapping("/listOrderByStatus/{username}")
    public List<Orders> findAllByStatus(@PathVariable String username,@RequestParam(defaultValue = "Pending") String status){
        User user = iUserService.findByUsername(username).get();
        return iOrder.findAllByStatus(user.getId(),status);
    }

    @GetMapping("/listOrder/{username}")
    public List<Orders> findAllOrderById(@PathVariable String username){
        User user = iUserService.findByUsername(username).get();
        return iOrder.findAllOrder(user.getId());
    }


    // ham edit Order theo Doi tuong order truyen sang
    @PutMapping
    public Orders  editOrder(@RequestBody Orders orders){
            iOrder.save(orders);
            return orders;
    }
    @GetMapping("/detailOrder/{orderId}")
    public List<OrderDetail> findAllByStatus(@PathVariable long orderId, @RequestParam long userId){
        return iOrderDetail.findAllOrderDetailByOrderId(userId,orderId);
    }

    @GetMapping("/listUserInPm/{username}")
    public List<User>  findAllUserBuyInPm(@PathVariable String username){
        User user = iUserService.findByUsername(username).get();
        return iUserService.findAllUserBuyInPm(user.getId());
    }
    @GetMapping("/checkkedOrder/{idOrder}")
    public  Orders checkkedOrder(@PathVariable long idOrder){
        Orders orders = iOrder.findByID(idOrder);
        orders.setStatus("Paid");
        iOrder.save(orders);
             return orders;
    }
    @GetMapping("/unCheckkedOrder/{idOrder}")
    public  Orders unCheckkedOrder(@PathVariable long idOrder){
        Orders orders = iOrder.findByID(idOrder);
        orders.setStatus("Pending");
        iOrder.save(orders);
             return orders;
    }
    @GetMapping("/checkDone/{idOrder}")
    public  Orders checkDone(@PathVariable long idOrder){
        Orders orders = iOrder.findByID(idOrder);
        orders.setStatus("Done");
        iOrder.save(orders);
        return orders;
    }

    @GetMapping("/searchByEmail/{username}")
    public List<Orders>  searchByEmail(@PathVariable String username,@RequestParam(defaultValue = "") String emailFind){
        User user = iUserService.findByUsername(username).get();
        return iOrder.searchByEmailUser(user.getId(),emailFind);
    }

    @GetMapping("/findUserInPm/{username}")
    public List<User>  findAllUserBuyEmailInPm(@PathVariable String username, @RequestParam String email){
        User user = iUserService.findByUsername(username).get();
        return iUserService.findAllUserBuyInPmAndEmail(user.getId(), email);

    }

    @GetMapping("/listOrderUserInPm/{username}")
    public List<Orders>  findAllByUserPmAndUserBuyer (@PathVariable String username, @RequestParam long id ){
        User user = iUserService.findByUsername(username).get();
        return iOrder.findAllByUserPmAndUserBuyer(user.getId(),id);
    }

    @GetMapping("/listOrderDetail/{id}")
    public List<OrderDetail>  findAllOrderById (@PathVariable long id ){
       return iOrderDetail.findAllOrderById(id);
    }
    @GetMapping("/detailOrderInPm/{orderId}")
    public List<OrderDetail> orderDetailListInPm(@PathVariable long orderId){
        return iOrderDetail.listOrderDetail(orderId);
    }


}

