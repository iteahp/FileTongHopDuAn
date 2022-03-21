package com.example.shopbibe.service.indexService;

import com.example.shopbibe.model.Rate;
import com.example.shopbibe.model.RateProduct;
import org.springframework.data.repository.query.Param;

public interface IRateOrderService {
    RateProduct saveRateOrder (RateProduct rateProduct);
    Rate findRateByOrdersId(Long id);
    double avgByPmRate( Long id);
}
