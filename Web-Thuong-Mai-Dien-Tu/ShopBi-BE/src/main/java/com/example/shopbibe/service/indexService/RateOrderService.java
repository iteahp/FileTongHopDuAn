package com.example.shopbibe.service.indexService;

import com.example.shopbibe.model.Rate;
import com.example.shopbibe.model.RateProduct;
import com.example.shopbibe.repository.indexRepo.IRateOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class RateOrderService implements IRateOrderService{
    @Autowired
    IRateOrderRepo iRateOrderRepo;
    @Override
    public RateProduct saveRateOrder(RateProduct rateProduct) {
        return iRateOrderRepo.save(rateProduct);
    }

    @Override
    public Rate findRateByOrdersId(Long id) {
        return iRateOrderRepo.findByOrdersIdAnd(id);
    }

    @Override
    public double avgByPmRate(Long id) {
        return iRateOrderRepo.avgByPmRate(id);
    }


}
