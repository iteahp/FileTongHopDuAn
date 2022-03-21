package service;

import dao.ProductDao;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
     ProductDao productDao= new ProductDao();
  static List<Product> productList = new ArrayList<>();

    public ProductService() {
        productList = productDao.getAllProduct();
    }

    public  List<Product> getProductList() {
        return productList = productDao.getAllProduct();
    }
    public void deleleProduct(int id){
        productDao.deleteProduct(id);
        productList = productDao.getAllProduct();
    }
    public void addProduct(Product product){
        productDao.saveProduct(product);
        productList = productDao.getAllProduct();
    }
    public void updateProduct(Product product){
       productDao.updateProduct(product);
        productList = productDao.getAllProduct();
    }
    public  int findIndexById(int id){
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId()==id){
                return i;
            }
        }
        return -1;
    }
    public List<Product> findProductByCategoryId(int categoryId){
        return productDao.findProductByCategoryId(categoryId);
    }
    public List<Product> findProductByName(String nameFind){
        return productDao.findProductByName(nameFind);
    }
    public void updateQuantityProduct(int productId,double quantity){
        productDao.updateQuantityProduct(productId,quantity);
    }
    public  int countPage(){
        int page = productDao.countProduct()/8;
        if ((productDao.countProduct()%8 )!=0){
            page = page+1;
        }
        return page;
    }
    public List<Product> getListProductByPage(int page){
        return productDao.getProductByPacking(page);
    }
}

