package com.example.shopbibe.controller.adminController;

import com.example.shopbibe.model.Role;
import com.example.shopbibe.model.RoleName;
import com.example.shopbibe.model.User;
import com.example.shopbibe.service.IUserService;
import com.example.shopbibe.service.indexService.email.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    RegistrationService registrationService;
    @Autowired
    IUserService iUserService;
    @GetMapping("/listUser")
    public ResponseEntity<Page<User>> getListUser(@RequestParam(defaultValue = "0") int pageNumber){
        return new ResponseEntity<>(iUserService.findAll(PageRequest.of(pageNumber,5)), HttpStatus.ACCEPTED);
    }
    @GetMapping("/detailUser/{id}")
    public ResponseEntity<User> deteailUser(@PathVariable long id){
        return new ResponseEntity<>(iUserService.findUserByID(id),HttpStatus.ACCEPTED);
    }

    @GetMapping("/upToPm/{id}")
    public void uptoPm(@PathVariable long id){
        User  user = iUserService.findUserByID(id);
        Set<Role> roleSet = user.getRoles();
        roleSet.add(new Role(2L,RoleName.PM));
        user.setRoles(roleSet);
        registrationService.sendMailOKUser(user);
        iUserService.save(user);
    }
    @GetMapping("/downToUser/{id}")
    public void downToUser(@PathVariable long id){
        User  user = iUserService.findUserByID(id);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role(1L,RoleName.USER));
        registrationService.disablePmForUser(user);
        user.setRoles(roleSet);
        iUserService.save(user);
    }
    @GetMapping("/findByEmail")
    public List<User> findByEmail(@RequestParam(defaultValue = "") String email ){
        return iUserService.findAllByEmailContaining(email);
    }

}
