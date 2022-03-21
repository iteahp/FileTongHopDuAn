package dao;

import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private Connection connection = ConnectMySql.getConnection();
    public List<Account> getAllAccount() {
        String sqlGetAll = "SELECT * FROM accounts where roleId =2";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlGetAll);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Account> accountLists = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("name");
                String password = resultSet.getString("password");
                String rePassword = resultSet.getString("re_password");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                int roleId = resultSet.getInt("roleId");
                accountLists.add(new Account(id,userName,password,rePassword,email,phone,roleId));
            }
            return accountLists;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public  boolean saveAccount(Account account) {
        boolean result=false;
        String saveSQl = "INSERT INTO accounts(name,password,re_password,email,phone) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(saveSQl);
            preparedStatement.setString(1,account.getName());
            preparedStatement.setString(2,account.getPassword());
            preparedStatement.setString(3,account.getRePassword());
            preparedStatement.setString(4,account.getEmail());
            preparedStatement.setString(5,account.getPhone());
            result = preparedStatement.executeUpdate()>0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public  void deleteAccount(int id){
        String deleteSQL = "DELETE from accounts where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Account checkLogin(String name,String pass){
        Account account1 = null ;
        System.out.println(name);
        System.out.println(pass);
        try (
             PreparedStatement preparedStatement = connection.prepareStatement("select * from accounts where name =? and password = ?");) {
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,pass);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String userName = rs.getString("name");
                String password = rs.getString("password");
                String rePassword = rs.getString("re_password");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                int roleId = rs.getInt("roleId");
                account1 = new Account(id,userName,password,rePassword,email,phone,roleId);
            }
        } catch (SQLException e) {
        }
        return account1;

    }

}
