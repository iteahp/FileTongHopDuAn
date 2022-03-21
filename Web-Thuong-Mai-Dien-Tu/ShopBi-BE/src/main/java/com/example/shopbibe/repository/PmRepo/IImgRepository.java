package com.example.shopbibe.repository.PmRepo;

import com.example.shopbibe.model.Img;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IImgRepository extends JpaRepository<Img, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM shopbi.img where product_id = :id")
    List<Img> getImgByProductId(@Param("id") Long id);
}
