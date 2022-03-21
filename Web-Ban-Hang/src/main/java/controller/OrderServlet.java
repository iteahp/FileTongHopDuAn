package controller;

import dao.OrderDao;
import dao.OrderDetailDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/order")
public class OrderServlet extends HttpServlet {
    OrderDao orderDao = new OrderDao();
    RequestDispatcher requestDispatcher ;
    OrderDetailDao orderDetailDao = new OrderDetailDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action==null){
            action = "";
        }
        switch (action){
            case "delete": {
                deleteOrder(req,resp);
                break;
            }
            case "deleteOrderDetail":{
                deleteOrderDetail(req,resp);
                break;
            }
            case "orderDetail" : {
                orderDetail(req,resp);
                break;
            }
            default: {
                showOrder(req,resp);
                break;
            }
        }
    }

    private void deleteOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        orderDetailDao.deleteOrderDetail(id);
        resp.sendRedirect("/order");
    }

    private void orderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("orderDetailList",orderDetailDao.getAllOrderDetail(id));
        requestDispatcher = req.getRequestDispatcher("/manager/DetailOrder.jsp");
        requestDispatcher.forward(req,resp);
    }

    private void deleteOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int idOrder = Integer.parseInt(req.getParameter("id"));
        orderDetailDao.deleteOrder(idOrder);
        orderDao.deleteOrder(idOrder);
        resp.sendRedirect("/order");
    }

    private void showOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("orderList",orderDao.getAllOrder());
        requestDispatcher = req.getRequestDispatcher("/manager/managerOrder.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
