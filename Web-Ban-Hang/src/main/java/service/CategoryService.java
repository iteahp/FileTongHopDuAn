package service;

import dao.CategoryDao;
import model.Category;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    CategoryDao categoryDao= new CategoryDao();
    static List<Category> categoryList = new ArrayList<>();



    public  List<Category> getCategoryList() {
        return categoryList= categoryDao.getAllCategory();
    }
    public void deleteCategory(int id){
        categoryDao.deleteCategory(id);
        categoryList= categoryDao.getAllCategory();
    }
    public void addCategory(Category category){
        categoryDao.saveCategory(category);
        categoryList= categoryDao.getAllCategory();
    }
    public void updateCategory(Category category){
        categoryDao.updateCategory(category);
        categoryList= categoryDao.getAllCategory();

    }
    public  int findIndexById(int id){
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getId()==id){
                return i;
            }
        }
        return -1;
    }
    public Category findCategoryById(int id){
       return categoryDao.findCategoryById(id);
    }
    public Category getCategoryByProductId(Product product){
        int categoryId = product.getCategoryId();
        return findCategoryById(categoryId);
    }
}
