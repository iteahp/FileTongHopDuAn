package com.example.shopbibe.service.indexService.email;


import com.example.shopbibe.model.User;

public interface iRegistrationService {
    public String sendMailComfimPmForAdmin(Long id,  String mail);
    public String sendMailComfimPmForUser(Long id,  String mail);
    String sendMailOKUser(User users);
    String disablePmForUser(User users);
}
