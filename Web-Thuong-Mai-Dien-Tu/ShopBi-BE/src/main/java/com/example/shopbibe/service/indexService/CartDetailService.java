package com.example.shopbibe.service.indexService;

import com.example.shopbibe.model.CartDetail;
import com.example.shopbibe.repository.indexRepo.ICartDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartDetailService implements ICartDetailtService{
    @Autowired
    ICartDetailRepo iCartDetailRepo;

    @Override
    public List<CartDetail> findCartDetailsByCart_Id(Long id) {
        return iCartDetailRepo.findCartDetailsByCart_Id(id);
    }

    @Override
    public void saveCartDetail(CartDetail cartDetail) {
        iCartDetailRepo.save(cartDetail);
    }

    @Override
    public void delete(Long id) {
        iCartDetailRepo.deleteById(id);
    }
}
