package com.example.shopbibe.repository.PmRepo;

import com.example.shopbibe.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPromotionRepository extends  JpaRepository<Promotion, Long> {
}
