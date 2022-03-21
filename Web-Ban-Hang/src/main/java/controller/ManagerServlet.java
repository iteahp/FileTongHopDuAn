package controller;

import dao.AccountDao;
import model.Category;
import model.Product;
import service.CategoryService;
import service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/managerProduct")
public class ManagerServlet extends HttpServlet {
    ProductService productService = new ProductService();
    CategoryService categoryService = new CategoryService();
    List<Product> productList = productService.getProductList();
    RequestDispatcher requestDispatcher;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action==null){
            action = "";
        }
        switch (action){
            case "delete": {
                deleteProduct(req,resp);
                break;
            }
            case "update": {
                showUpdateForm(req,resp);
                break;
            }

            case "view" :{
                productDetail(req,resp);
                break;
            }
            case "showListAccount" : {
                showListAccount(req,resp);
                break;
            }

            case "showByPage" : {
                showProductByPage(req,resp);
                break;
            }
            default: {
                showProductList(req,resp);
                break;
            }

        }
    }


    private void showListAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountDao accountDao = new AccountDao();
        req.setAttribute("listAccount",accountDao.getAllAccount());
        requestDispatcher = req.getRequestDispatcher("/manager/managerAccount.jsp");
        requestDispatcher.forward(req,resp);
    }


    private void showProductByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = Integer.parseInt(req.getParameter("page"));
        req.setAttribute("productList",productService.getListProductByPage(page));
        req.setAttribute("categoryList",categoryService.getCategoryList());
        req.setAttribute("countPage",productService.countPage());
        requestDispatcher = req.getRequestDispatcher("/manager/managerProduct.jsp");
        requestDispatcher.forward(req,resp);
    }

    private void findByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameFind = req.getParameter("nameFind");
        req.setAttribute("productList",productService.findProductByName(nameFind));
        requestDispatcher = req.getRequestDispatcher("/manager/managerProduct.jsp");
        requestDispatcher.forward(req,resp);
    }
    private void showUpdateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        productList=productService.getProductList();
        Product product = productList.get(productService.findIndexById(id));
        req.setAttribute("productUpdate",product);
        requestDispatcher = req.getRequestDispatcher("/manager/updateProduct.jsp");
        requestDispatcher.forward(req,resp);
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        productService.deleleProduct(id);
        resp.sendRedirect("/managerProduct");
    }

    private void showProductList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("productList",productService.getListProductByPage(1));
        req.setAttribute("categoryList",categoryService.getCategoryList());
        req.setAttribute("countPage",productService.countPage());
        requestDispatcher = req.getRequestDispatcher("/manager/managerProduct.jsp");
        requestDispatcher.forward(req,resp);
    }
    private Category getCategoryByProductId(Product product){
        int categoryId = product.getCategoryId();
       return categoryService.findCategoryById(categoryId);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action==null){
            action = "";
        }
        switch (action){
            case "create": {
                createProduct(req,resp);
                break;
            }
            case "findByName":{
                findByName(req,resp);
                break;
            }
            case "update" :{
                updateProduct(req,resp);
                break;
            }

            default: {
                showProductList(req,resp);
                break;
            }
        }
    }

    private void productDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product productFound = productService.getProductList().get(productService.findIndexById(id));
        Category category = getCategoryByProductId(productFound);
        req.setAttribute("productFound",productFound);
        req.setAttribute("category",category);
        requestDispatcher = req.getRequestDispatcher("/manager/detailProduct.jsp");
        requestDispatcher.forward(req,resp);
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String img = req.getParameter("img");
        double price = Double.parseDouble(req.getParameter("price"));
        long quantity = Long.parseLong(req.getParameter("quantity"));
        Product productUpdate = new Product(id,name,price,description,quantity,img);
        productService.updateProduct(productUpdate);
        resp.sendRedirect("/managerProduct");
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        float price = Float.parseFloat(req.getParameter("price"));
        String description = req.getParameter("description");
        long quantity = Long.parseLong(req.getParameter("quantity"));
        String img = req.getParameter("img");
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        Product product = new Product(1,name,price,description,quantity,img,categoryId);
        productService.addProduct(product);
        resp.sendRedirect("/managerProduct");
    }
}
