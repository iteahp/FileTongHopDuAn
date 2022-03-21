package com.example.shopbibe.service.PmService;

import com.example.shopbibe.model.Img;
import com.example.shopbibe.model.Product;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IImgService {
    List<Img> getImgByProductId(Long id);

    void delete(long id);

    Img save(Img img);

    Img findById(long id);
}
