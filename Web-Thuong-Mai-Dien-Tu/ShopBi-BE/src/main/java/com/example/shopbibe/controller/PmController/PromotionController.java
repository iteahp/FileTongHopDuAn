package com.example.shopbibe.controller.PmController;


import com.example.shopbibe.model.Promotion;
import com.example.shopbibe.service.PmService.IPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/promotion")
public class PromotionController {
    @Autowired
    IPromotionService promotionService;

    @GetMapping
    public ResponseEntity<List<Promotion>> findAllByPromotion(){
        return new ResponseEntity<>(promotionService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Promotion> save(@RequestBody Promotion promotion){
        return new ResponseEntity<>(promotionService.save(promotion), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        promotionService.delete(id);
    }

    @PutMapping
    public Promotion edit(@RequestBody Promotion promotion){
        promotionService.save(promotion);
        return promotion;
    }
}
