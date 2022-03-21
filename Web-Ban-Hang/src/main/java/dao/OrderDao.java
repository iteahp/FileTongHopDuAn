package dao;

import model.Order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private Connection connection = ConnectMySql.getConnection();
    public List<Order> getAllOrder() {
        String sqlGetAll = "SELECT * FROM orders ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlGetAll);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Order> orderLists = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int accountId = resultSet.getInt("accountId");
                double totalPrice = resultSet.getDouble("totalPrice");
                Date date = resultSet.getDate("date");
                orderLists.add(new Order(id,accountId,totalPrice,date));
            }
            return orderLists;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public  boolean saveOrder(Order order) {
        boolean result=false;
        String saveSQl = "INSERT INTO orders(accountId,totalPrice,date) VALUES (?,?,?)";
        long longMillis = System.currentTimeMillis();
        Date date = new Date(longMillis);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(saveSQl);
            preparedStatement.setInt(1,order.getAccountId());
            preparedStatement.setDouble(2,order.getTotalPrice());
            preparedStatement.setDate(3,date);
            result = preparedStatement.executeUpdate()>0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public Order findOrderMax(){
        String sqlGetAll = "SELECT * FROM orders where id = (select max(id) from orders);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlGetAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            Order order = new Order();


            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int accountId = resultSet.getInt("accountId");
                double totalPrice = resultSet.getDouble("totalPrice");
                 order = new Order(id,accountId,totalPrice);

            }
            return order;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public  void deleteOrder(int id){
        String deleteSQL = "DELETE from orders where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
