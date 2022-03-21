package controller;

import dao.OrderDao;
import model.Account;
import model.Category;
import model.Order;
import model.Product;
import service.CategoryService;
import service.OrderService;
import service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/product")
public class ProductServlet extends HttpServlet {
    ProductService productService = new ProductService();
    RequestDispatcher requestDispatcher;
    CategoryService categoryService = new CategoryService();
    OrderService orderService = new OrderService();
    OrderDao orderDao = new OrderDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String action = req.getParameter("action");
      if (action==null){
          action = "";
      }
      switch (action){
          case "create":{
              createProductForm(req,resp);
              break;
          }
          case "findByCate":{
                findBycate(req,resp);
              break;
          }
          case "addToCart" : {
              addToCart(req,resp);
              break;
          }
          case "checkOut": {
              checkOutOrder(req,resp);
              break;
          }
          case "orderDetail" :{
              showOrderDetail(req,resp);
              break;
          }
          case "plus":{
              plusProductOrder(req,resp);
              break;
          }
          case "minus":{
              minusProductOrder(req,resp);
              break;
          }
          case "productDetail" :{
              showProductDetail(req,resp);
              break;
          }
          default:{
              showProductList(req,resp);
              break;
          }
      }
    }

    private void checkOutOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("acc");
        Order order = new Order(1,account.getId(),orderService.totalListOrder());
        orderService.saveOrder(order);
        orderService.saveOrderDetail();
        resp.sendRedirect("/product");
    }

    private void plusProductOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.getProductList().get(productService.findIndexById(id));
        Product productOrder=orderService.getProductListAddOrder().get(orderService.findIndexById(id));
        productOrder.setQuantity(productOrder.getQuantity()+1);
        productOrder.setPrice(productOrder.getPrice()+product.getPrice());
        showOrderDetail(req,resp);
    }

    private void minusProductOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.getProductList().get(productService.findIndexById(id));
        Product productOrder=orderService.getProductListAddOrder().get(orderService.findIndexById(id));
        productOrder.setQuantity(productOrder.getQuantity()-1);
        productOrder.setPrice(productOrder.getPrice()-product.getPrice());
        req.setAttribute("product",product);
        showOrderDetail(req,resp);
    }


    private void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("listProductOrder",orderService.getProductListAddOrder());
        req.setAttribute("sum",orderService.totalListOrder());
        requestDispatcher = req.getRequestDispatcher("views/cart.jsp");
        requestDispatcher.forward(req,resp);
    }

    private void addToCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int idP = Integer.parseInt(req.getParameter("idP"));
        Product product = productService.getProductList().get(productService.findIndexById(idP));
        int index = orderService.findIndexById(idP);
        if (index>=0){
           Product productOrder = orderService.getProductListAddOrder().get(index);
           productOrder.setQuantity(productOrder.getQuantity()+1);
           productOrder.setPrice(productOrder.getPrice()+product.getPrice());

        }else {
            orderService.addProductToOrder(new Product(product.getId(),product.getName(),product.getPrice(),1,product.getImg(),product.getPrice()));
        }
        req.setAttribute("product",product);

         resp.sendRedirect("/product");
    }

    private void findByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameFind = req.getParameter("nameFind");
        req.setAttribute("productList",productService.findProductByName(nameFind));
        req.setAttribute("categoryList",categoryService.getCategoryList());
        requestDispatcher = req.getRequestDispatcher("views/product.jsp");
        requestDispatcher.forward(req,resp);
    }

    private void showProductDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product productFound = productService.getProductList().get(productService.findIndexById(id));
        Category category = categoryService.getCategoryByProductId(productFound);
        req.setAttribute("productFound",productFound);
        req.setAttribute("category",category);
        requestDispatcher = req.getRequestDispatcher("/views/productDetail.jsp");
        requestDispatcher.forward(req,resp);

    }

    private void findBycate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int categoryId = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("productList",productService.findProductByCategoryId(categoryId));
        req.setAttribute("categoryList",categoryService.getCategoryList());
        requestDispatcher = req.getRequestDispatcher("views/product.jsp");
        requestDispatcher.forward(req,resp);
    }

    private void createProductForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requestDispatcher =req.getRequestDispatcher("/manager/managerProduct.jsp");
        requestDispatcher.forward(req,resp);
    }

    private void showProductList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         req.setAttribute("productList",productService.getProductList());
         req.setAttribute("categoryList",categoryService.getCategoryList());
         req.setAttribute("listProductOrder",orderService.getProductListAddOrder());
         req.setAttribute("countPage",productService.countPage());
         requestDispatcher = req.getRequestDispatcher("views/product.jsp");
         requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "findByName": {
                findByName(req, resp);
                break;
            }
        }
    }
}
