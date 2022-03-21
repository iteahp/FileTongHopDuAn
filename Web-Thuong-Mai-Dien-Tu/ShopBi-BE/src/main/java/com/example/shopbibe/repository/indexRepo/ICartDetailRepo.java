package com.example.shopbibe.repository.indexRepo;

import com.example.shopbibe.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICartDetailRepo extends JpaRepository<CartDetail,Long> {
            List<CartDetail> findCartDetailsByCart_Id(Long id);
}
