package com.example.shopbibe.service.indexService.email;


import com.example.shopbibe.model.User;
import com.example.shopbibe.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class RegistrationService implements iRegistrationService{

    @Autowired
    private SendEmailService sendEmailService;

    @Override
    public String sendMailComfimPmForAdmin(Long id , String mail) {
        String verificationLink = "http://localhost:8080/admin/upToPm/"+ id ;
        String emailTo = "luciferms1211@gmail.com";
        String body = "There are new sellers who want the price to enter Shopbe . Please click the link to verify the seller! " + verificationLink;
        String title = "Verify Pm account" + mail;
        sendEmailService.sendEmail(emailTo,body,title);
        return "registered";
    }

    @Override
    public String sendMailComfimPmForUser(Long id,String mail) {
        String emailTo = mail;
        String body = "You have successfully submitted your registration. Please wait for ShopBi to censor shortly ";
        String title = "Welcome to " + mail;
        sendEmailService.sendEmail(emailTo,body,title);
        return "registered";
    }

    @Override
    public String sendMailOKUser(User users) {
        String emailTo = users.getEmail();
        String body = "You have become a seller on ShopBi ";
        String title = "Welcome to " + users.getEmail();
        sendEmailService.sendEmail(emailTo,body,title);
        return "registered";
    }

    @Override
    public String disablePmForUser(User users) {
        String emailTo = users.getEmail();
        String body = "You have been canceled the right to sell on ShopBi ";
        String title = "Welcome to " + users.getEmail();
        sendEmailService.sendEmail(emailTo,body,title);
        return "registered";
    }





}
