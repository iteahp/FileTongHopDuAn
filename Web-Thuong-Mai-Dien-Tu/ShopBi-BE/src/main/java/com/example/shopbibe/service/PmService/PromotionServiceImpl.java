package com.example.shopbibe.service.PmService;

import com.example.shopbibe.model.Promotion;
import com.example.shopbibe.repository.PmRepo.IPromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PromotionServiceImpl implements IPromotionService{
    @Autowired
    IPromotionRepository promotionRepository;
    @Override
    public List<Promotion> findAll() {
        return promotionRepository.findAll();
    }

    @Override
    public Promotion save(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    @Override
    public void delete(long id) {
        promotionRepository.deleteById(id);
    }

    @Override
    public Promotion findById(long id) {
        return promotionRepository.findById(id).get();
    }
}
