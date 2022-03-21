package service;

import dao.OrderDao;
import dao.OrderDetailDao;
import model.Order;
import model.OrderDetail;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    List<Product> productListAddOrder = new ArrayList<>();
    OrderDao orderDao = new OrderDao();
   OrderDetailDao orderDetailDao = new OrderDetailDao();
    public List<Product> getProductListAddOrder(){
        return productListAddOrder;
    }
    public void addProductToOrder(Product product){
        productListAddOrder.add(product);
    }
    public void deleteProductFromOrder(int index){
        productListAddOrder.remove(index);
    }
    public  int findIndexById(int id){
        for (int i = 0; i < productListAddOrder.size(); i++) {
            if (productListAddOrder.get(i).getId()==id){
                return i;
            }
        }
        return -1;
    }
    public double totalListOrder(){
        double sum=0;
        for (Product productOrder: productListAddOrder
             ) {
            sum+=productOrder.getPrice();
        }
        return sum;
    }
    public void  saveOrder(Order order){
        orderDao.saveOrder(order);

    }
    public void saveOrderDetail(){
        ProductService productService =new ProductService();
        for (Product pr: productListAddOrder) {
            OrderDetail orderDetail = new OrderDetail(1,orderDao.findOrderMax().getId(),pr.getId(),pr.getQuantity());
            orderDetailDao.saveOrderDetail(orderDetail);
            double quantity = productService.getProductList().get(productService.findIndexById(pr.getId())).getQuantity() -pr.getQuantity();
            productService.updateQuantityProduct(pr.getId(),quantity);
        }
       resetOrderDetail();
    }
    public void resetOrderDetail(){
        productListAddOrder = new ArrayList<>();
    }
}
