package controller;

import model.Account;
import service.AccountService;
import service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/account")
public class AccountServlet extends HttpServlet {
    AccountService accountService = new AccountService();
    RequestDispatcher requestDispatcher;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action==null){
            action = "";
        }
        switch (action){
            case "login" : {
                showLogin(req,resp);
                break;
            }
            case "logout" : {
                HttpSession session = req.getSession();
                session.removeAttribute("acc");
                OrderService orderService = new OrderService();
                orderService.resetOrderDetail();
                resp.sendRedirect("/product");
                break;
            }
            case "delete" :{
                deteleAccount(req,resp);
                break;
            }

        }
    }

    private void deteleAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        accountService.deleteAccount(id);
        resp.sendRedirect("/managerProduct?action=showListAccount");
    }

    private void showLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requestDispatcher =req.getRequestDispatcher("/views/login.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action==null){
            action = "";
        }
        switch (action){
            case "login" : {
                login(req,resp);
                break;
            }
            case "singup": {
                singUp(req,resp);
            }
        }
    }

    private void singUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String rePassword = req.getParameter("re_password");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            Account account = new Account(username,password,rePassword,email,phone);
           if (!accountService.saveAccount(account)){
               req.setAttribute("messSingUp","Account or email already exists");
               showLogin(req,resp);
           }else {
               req.setAttribute("messSingUp","Account successfully created");
               showLogin(req,resp);
           }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String userName = req.getParameter("username");
            String password = req.getParameter("password");
        Account account = accountService.checkLogin(userName,password);
        if (account==null){
            req.setAttribute("mess","Wrong user or pass");
            showLogin(req,resp);
        }else {
            HttpSession session = req.getSession();
            session.setAttribute("acc",account);
            resp.sendRedirect("/product");
        }
    }
}
