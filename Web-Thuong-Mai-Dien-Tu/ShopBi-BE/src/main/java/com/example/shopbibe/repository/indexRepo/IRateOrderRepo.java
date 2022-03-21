package com.example.shopbibe.repository.indexRepo;

import com.example.shopbibe.model.Rate;
import com.example.shopbibe.model.RateProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IRateOrderRepo extends JpaRepository<RateProduct,Long> {
    @Query(nativeQuery = true, value = "SELECT rate FROM rate join shopbi.rate_product on rate.id = shopbi.rate_product.rate_id  where orders_id = :id ")
    Rate findByOrdersIdAnd(@Param("id") Long id);

    @Query(nativeQuery = true,value = "SELECT avg(rate_id) FROM shopbi.orders where user_pm_id = :id and rate_id > 0")
    double avgByPmRate(@Param("id") Long id);
}
