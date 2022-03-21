package com.example.shopbibe.dto.request;

import com.example.shopbibe.model.Img;
import com.example.shopbibe.model.Product;

import java.util.List;

public class ImgForm {
    List<String> imgList;
    Product product;

    public ImgForm(List<String> imgList, Product product) {
        this.imgList = imgList;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }
}
