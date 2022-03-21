package dao;

import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    private Connection connection = ConnectMySql.getConnection();
    public List<Category> getAllCategory() {
        String sqlGetAll = "SELECT * FROM productcategory";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlGetAll);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Category> categoryList = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                categoryList.add(new Category(id,name));
            }
            return categoryList;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public  void saveCategory(Category category) {
        boolean result=false;
        String saveSQl = "INSERT INTO productcategory(name) VALUES (?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(saveSQl);
            preparedStatement.setString(1,category.getName());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public  void deleteCategory(int id){
        String deleteSQL = "DELETE from productcategory where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public  void updateCategory(Category category) {
        String saveSQl = "UPDATE productcategory SET  name = ? WHERE (id = ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(saveSQl);
            preparedStatement.setString(1,category.getName());
            preparedStatement.setInt(2,category.getId());
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Category findCategoryById(int id){
        String sqlGetAll = "SELECT * FROM productcategory where id = ?";
        Category category = new Category();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlGetAll);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                 int idCategory = resultSet.getInt("id");
                String name = resultSet.getString("name");
                category = new Category(idCategory,name);
            }
            return category;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
