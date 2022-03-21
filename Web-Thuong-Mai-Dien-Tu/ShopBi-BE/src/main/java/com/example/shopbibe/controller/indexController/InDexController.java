package com.example.shopbibe.controller.indexController;

import com.example.shopbibe.dto.request.CartForm;
import com.example.shopbibe.dto.request.OrderPMs;
import com.example.shopbibe.model.*;
import com.example.shopbibe.service.IUserService;
import com.example.shopbibe.service.PmService.ICategoryService;
import com.example.shopbibe.service.PmService.IProductService;
import com.example.shopbibe.service.indexService.*;
import com.example.shopbibe.service.indexService.email.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/index")
public class InDexController {
    @Autowired
    IRateOrderService iRateOrderService;
    @Autowired
    IUserService iUserService;
    @Autowired
    IOrderDetailImpl iOrderDetail;
    @Autowired
    ICartDetailtService iCartDetailtService;
    @Autowired
    IProductService iProductService;
    @Autowired
    ICategoryService categoryService;
    @Autowired
    IOrderImpl iOrder;
    @Autowired
    ICartService iCart;
    @Autowired
    RegistrationService registrationService;
    // ham tra list sp theo categoryid truyen sang,sap xep theo quantitysale da phan trang
    @GetMapping("/category/{idCate}")
    public ResponseEntity<Page<Product>> findAllByCategory(@PathVariable long idCate,@RequestParam(defaultValue = "0") int pageNumber){
        return new ResponseEntity<>(iProductService.findAllByCategory(PageRequest.of(pageNumber,5, Sort.by("quantitySale").descending()),idCate), HttpStatus.ACCEPTED);
    }
    @GetMapping("/category")
    public ResponseEntity<List<Category>> findAllCategory(){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.ACCEPTED);
    }
    // ham tra list sp theo userID truyen sang,sap xep theo quantitysale da phan trang
    @GetMapping("/pm/{idPm}")
    public ResponseEntity<Page<Product>> findAllByPm(@PathVariable long idPm,@RequestParam(defaultValue = "0") int pageNumber){
        return new ResponseEntity<>(iProductService.findAllByUser(PageRequest.of(pageNumber,5, Sort.by("quantitySale").descending()),idPm), HttpStatus.ACCEPTED);
    }
    // ham tra list sp theo sap xep theo quantitysale giamr dan da phan trang
    @GetMapping("/bestSale")
    public ResponseEntity<Page<Product>> findAllByBestSale(@RequestParam(defaultValue = "0") int pageNumber){
        return new ResponseEntity<>(iProductService.findAllProductPage(PageRequest.of(pageNumber,10, Sort.by("quantitySale").descending())), HttpStatus.ACCEPTED);
    }
    // ham tra ve 1 doi tuong product theo id truyen sang
    @GetMapping("/detail/{idProduct}")
    public ResponseEntity<Product> detailProduct(@PathVariable long idProduct){
        return new ResponseEntity<>(iProductService.findById(idProduct),HttpStatus.ACCEPTED);
    }
    // tra ve list san pham duoc tim theo keyword truyen sang da phan trang
    @GetMapping("/search/{nameFind}")
    public ResponseEntity<Page<Product>> findAllByName(@PathVariable String nameFind,@RequestParam(defaultValue = "0") int pageNumber){
        return new ResponseEntity<>(iProductService.findAllByNameContaining(PageRequest.of(pageNumber,5),nameFind),HttpStatus.ACCEPTED);
    }
    // List  TOp Cate  ban chay nhat
    @GetMapping("/topCate")
    public ResponseEntity<List<Category>> top3Categories(){
        return new ResponseEntity<>(iProductService.top3Categories(),HttpStatus.ACCEPTED);
    }

    @GetMapping("/cartdetail/{idCart}")
    public ResponseEntity<List<CartDetail>> getListCartDetailByCartId(@PathVariable Long idCart){
        return new ResponseEntity<>(iCartDetailtService.findCartDetailsByCart_Id(idCart),HttpStatus.ACCEPTED);
    }

    @GetMapping("/findOrder/{username}")
    public ResponseEntity<Page<Orders>> getListOrderBuyer(@PathVariable String username,@RequestParam(defaultValue = "0") int pageNumber){
        return new ResponseEntity<>(iOrder.findAllByUserBuyer(username,PageRequest.of(pageNumber,5)), HttpStatus.ACCEPTED);
    }
    @GetMapping("/findOrderDetail/{id}")
    public ResponseEntity<List<OrderDetail>> getListOrderDetailByOrderId(@PathVariable Long id){
        return new ResponseEntity<>(iOrderDetail.findAllByOrders_Id(id), HttpStatus.ACCEPTED);
    }
    // lưu Order theo OrderPMs truyền vào ,Check out don hang
    @Transactional
    @PostMapping("/checkOutOrder")
    public  ResponseEntity<?> checkOutOrder(@RequestBody OrderPMs orderForm){
        //String address_ship, double totalBill, String status, User userBuyer, User userPm
        User userBuyer = iUserService.findByUsername(orderForm.getUsernameBuyer()).get();
        User userPm = iUserService.findByUsername(orderForm.getUsernameSaler()).get();
       Orders order = new Orders(orderForm.getAddress_ship(),orderForm.getBillTotal(),"Pending",userBuyer,userPm,new Date());
       iOrder.save(order);
        for (CartDetail cartDetail: orderForm.getCartDetails()) {
            iOrderDetail.save(new OrderDetail(order,cartDetail.getProduct(),cartDetail.getQuantity()));
                Product product = cartDetail.getProduct();
            if(product.getQuantity() >=cartDetail.getQuantity() ){
                product.setQuantity(product.getQuantity() - cartDetail.getQuantity());
                product.setQuantitySale(product.getQuantitySale()+cartDetail.getQuantity());
                product.setQuantityMax(product.getQuantity());
                iProductService.save(product);
            }
        }
        List<CartDetail> cartDetails = iCartDetailtService.findCartDetailsByCart_Id(userBuyer.getCart().getId());
        for (CartDetail cartDetail: cartDetails) {
            iCartDetailtService.delete(cartDetail.getId());
        }
        return new ResponseEntity<>(orderForm,HttpStatus.ACCEPTED);
    }
    // Luu Cart theo CartForm truyền vào
    @PostMapping("/addToCart")
    public  void addToCart(@RequestBody CartForm cartForm){
       iCart.saveCart(cartForm);
    }
    @PostMapping("/RateOrder")
    public  ResponseEntity<?> addToCart(@RequestBody RateProduct rateProduct){
        Orders orders = rateProduct.getOrders();
        orders.setRate(rateProduct.getRate());

        iOrder.save(orders);
        iRateOrderService.saveRateOrder(rateProduct);
        return new ResponseEntity<>(rateProduct,HttpStatus.ACCEPTED);
    }
    @GetMapping("/findRateProduct/{id}")
    public ResponseEntity<Rate> getRate(@PathVariable Long id){
        return new ResponseEntity<>(iRateOrderService.findRateByOrdersId(id), HttpStatus.ACCEPTED);
    }
    @GetMapping("/new10Product")
    public ResponseEntity<List<Product>> getProductNew(){
        return new ResponseEntity<>(iProductService.findProductByCreateAtNew(), HttpStatus.ACCEPTED);
    }
    @GetMapping("/Top15ProductsalePm/{id}")
    public ResponseEntity<List<Product>> getTop15ProductsalePm(@PathVariable Long id){
        return new ResponseEntity<>(iProductService.findProductByPmID(id), HttpStatus.ACCEPTED);
    }
    @GetMapping("/findByPmAndCate/{idU}")
    public ResponseEntity<List<Product>> findByPmAndCate(@PathVariable Long idU,@RequestParam Long idC){
        return new ResponseEntity<>(iProductService.findProductByPmIdAndCategoryId(idU,idC), HttpStatus.ACCEPTED);
    }
    @GetMapping("/findUserByUserName/{username}")
    public ResponseEntity<User> findUserByUserName(@PathVariable String username){
        return new ResponseEntity<>(iUserService.findByUsername(username).get(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/avgPmRate/{id}")
    public ResponseEntity<?> findAvgPmRate(@PathVariable Long id){
        return new ResponseEntity<>(iRateOrderService.avgByPmRate(id), HttpStatus.ACCEPTED);
    }
    @PostMapping("/sendRequestUpSaller")
    public  ResponseEntity<?> sendRequestUpSaller(@RequestBody User user){
        registrationService.sendMailComfimPmForAdmin(user.getId(),user.getEmail());
        registrationService.sendMailComfimPmForUser(user.getId(),user.getEmail());
        return new ResponseEntity<>("Successful Request",HttpStatus.ACCEPTED);
    }
    @PutMapping("/userEdit")
    public  ResponseEntity<?> updateUser(@RequestBody User user){
        User user1 = iUserService.findUserByID(user.getId());
        if (user.getPassword() == null){
            user.setPassword(user1.getPassword());
        }
    return new ResponseEntity<>(iUserService.save(user),HttpStatus.ACCEPTED);
    }
    @PostMapping("/saveCartDetail")
    public  ResponseEntity<?> saveCartDetail(@RequestBody CartDetail cartDetail){
        iCartDetailtService.saveCartDetail(cartDetail);
        return new ResponseEntity<>("Successful Request",HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/deleteCartDetail/{idCart}")
    public  ResponseEntity<?> deleteCartDetail(@PathVariable Long idCart){
        List<CartDetail> cartDetails = iCartDetailtService.findCartDetailsByCart_Id(idCart);
        for (CartDetail cartDetail: cartDetails) {
            iCartDetailtService.delete(cartDetail.getId());
        }
        return new ResponseEntity<>("Successful Request",HttpStatus.ACCEPTED);
    }
}
