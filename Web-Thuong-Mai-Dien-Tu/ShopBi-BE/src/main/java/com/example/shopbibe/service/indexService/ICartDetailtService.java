package com.example.shopbibe.service.indexService;

import com.example.shopbibe.model.CartDetail;

import java.util.List;

public interface ICartDetailtService {
    List<CartDetail> findCartDetailsByCart_Id(Long id);
    void saveCartDetail(CartDetail cartDetail);
    void delete(Long id);
}
