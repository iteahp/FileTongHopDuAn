package com.example.shopbibe.controller.PmController;

import com.example.shopbibe.model.*;
import com.example.shopbibe.service.IUserService;
import com.example.shopbibe.service.PmService.ICategoryService;
import com.example.shopbibe.service.PmService.IImgService;
import com.example.shopbibe.service.PmService.IProductService;
import com.example.shopbibe.service.PmService.IPromotionService;
import com.example.shopbibe.service.indexService.IOrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/pm")
public class PmController {
    @Autowired
    ICategoryService categoryService;

    @Autowired
    IProductService productService;

    @Autowired
    IPromotionService promotionService;

    @Autowired
    IImgService imgService;
    @Autowired
    IUserService iUserService;

    @Autowired
    IOrderImpl iOrder;
    @GetMapping("/product/{username}")
    public ResponseEntity<List<Product>> findAllProduct(@PathVariable String username){
        return new ResponseEntity<>(productService.findAllByUserName(username), HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Category>> findAllCategory(){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/promotion")
    public ResponseEntity<List<Promotion>> findAllPromotion(){
        return new ResponseEntity<>(promotionService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/img/{id}")
    public ResponseEntity<List<Img>> findAllImg(@PathVariable long id){
        return new ResponseEntity<>(imgService.getImgByProductId(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable long id){
        return new ResponseEntity<>(productService.findById(id),HttpStatus.OK);
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<List<Product>> findAllByName(@PathVariable String name){
        return new ResponseEntity<>(productService.findAllByName(name),HttpStatus.OK);
    }

    @GetMapping("/find/category/{id}")
    public ResponseEntity<List<Product>> findAllByCategory(@PathVariable Long id){
        return new ResponseEntity<>(productService.findAllByCategory(id),HttpStatus.OK);
    }

    @GetMapping("/listOrderDoneToday/{username}")
    public ResponseEntity<List<Orders>> findOrderByIdAndStatusToday(@PathVariable String username){
        User user = iUserService.findByUsername(username).get();
        return new ResponseEntity<>(iOrder.findOrdersByIdAndStatusToday(user.getId()),HttpStatus.OK);
    }

    @GetMapping("/listOrderDone/{username}")
    public ResponseEntity<List<Orders>> findOrderByIdAndStatus(@PathVariable String username){
        User user = iUserService.findByUsername(username).get();
        return new ResponseEntity<>(iOrder.findOrdersByIdAndStatus(user.getId()),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product ,@RequestParam String username){
        User user = iUserService.findByUsername(username).get();
        product.setCreateAt(new Date());
        product.setUser(user);
        product.setPriceSale(product.getPrice()-(product.getPrice()*product.getPromotion().getDiscount())/100);
        product.setQuantityMax(product.getQuantity());
        return new ResponseEntity<>(productService.save(product),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        productService.delete(id);
    }

    @PutMapping
    public Product edit(@RequestBody Product product,@RequestParam String username){
        User user = iUserService.findByUsername(username).get();
        product.setUser(user);
        product.setPriceSale(product.getPrice()-(product.getPrice()*product.getPromotion().getDiscount())/100);
        product.setQuantityMax(product.getQuantity());
        productService.save(product);
        return product;
    }


}
