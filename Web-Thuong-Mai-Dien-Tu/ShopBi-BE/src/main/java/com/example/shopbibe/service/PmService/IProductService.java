package com.example.shopbibe.service.PmService;

import com.example.shopbibe.dto.response.Top3Category;
import com.example.shopbibe.model.Category;
import com.example.shopbibe.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    Product save(Product product);

    void delete(long id);
    List<Product> findAllByUserName(String username);
    Product findById(long id);
    Page<Product> findAllByCategory(Pageable pageable,long idCate);
    Page<Product> findAllByUser(Pageable pageable,long idPm);
    Page<Product> findAllProductPage(Pageable pageable);
    Page<Product> findAllByNameContaining(Pageable pageable,String nameFind);
    public  List<Category> top3Categories();

    List<Product> findAllByName(String name);
    List<Product> findAllByCategory(Long id);
    List<Product> findProductByCreateAtNew();
    List<Product> findProductByPmID( Long id);
    List<Product> findProductByPmIdAndCategoryId( Long idU , Long idC );

}
