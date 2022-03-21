package com.example.shopbibe.controller;

import com.example.shopbibe.model.*;
import com.example.shopbibe.dto.request.ChangeAvatar;
import com.example.shopbibe.dto.request.SignInForm;
import com.example.shopbibe.dto.request.SignUpForm;
import com.example.shopbibe.dto.response.JwtResponse;
import com.example.shopbibe.dto.response.ResponMessage;
import com.example.shopbibe.security.jwt.JwtProvider;
import com.example.shopbibe.security.userprincal.UserDetailServices;
import com.example.shopbibe.security.userprincal.UserPrinciple;
import com.example.shopbibe.service.impl.RoleServiceImpl;
import com.example.shopbibe.service.impl.UserServiceImpl;
import com.example.shopbibe.service.indexService.ICartDetailtService;
import com.example.shopbibe.service.indexService.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequestMapping
@RestController
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    ICartService iCartService;
    @Autowired
    ICartDetailtService iCartDetailtService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    RoleServiceImpl roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
  @Autowired
  UserDetailServices userDetailServices;
    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm){
        if(userService.existsByUsername(signUpForm.getUsername())){
            return new ResponseEntity<>(new ResponMessage("no_user"), HttpStatus.OK);
        }
        if(userService.existsByEmail(signUpForm.getEmail())){
            return new ResponseEntity<>(new ResponMessage("no_email"), HttpStatus.OK);
        }
        if(signUpForm.getAvatar() == null || signUpForm.getAvatar().trim().isEmpty()){
            signUpForm.setAvatar("https://firebasestorage.googleapis.com/v0/b/chinhbeo-18d3b.appspot.com/o/avatar.png?alt=media&token=3511cf81-8df2-4483-82a8-17becfd03211");
        }
        User user = new User(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getEmail(),passwordEncoder.encode(signUpForm.getPassword()), signUpForm.getAvatar());
        Set<String> strRoles = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role ->{
            switch (role){
                case "admin":
                    Role adminRole = roleService.findByName(RoleName.ADMIN).orElseThrow(
                            ()-> new RuntimeException("Role not found")
                    );
                    roles.add(adminRole);
                    break;
                case "pm":
                    Role pmRole = roleService.findByName(RoleName.PM).orElseThrow( ()-> new RuntimeException("Role not found"));
                    roles.add(pmRole);
                    break;
                default:
                    Role userRole = roleService.findByName(RoleName.USER).orElseThrow( ()-> new RuntimeException("Role not found"));
                    roles.add(userRole);
            }
        });
        user.setRoles(roles);
        Cart cart = new Cart();
        iCartService.saveCart(cart);
        user.setCart(cart);
        userService.save(user);


        return new ResponseEntity<>(new ResponMessage("yes"), HttpStatus.OK);
    }
    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        List<CartDetail> cartDetailList = iCartDetailtService.findCartDetailsByCart_Id(userPrinciple.getCart().getId());
        JwtResponse jwtResponse = new JwtResponse(token, userPrinciple.getName(),userPrinciple.getAvatar(), userPrinciple.getAuthorities(),userPrinciple.getCart(),cartDetailList,userPrinciple.getUsername(),userPrinciple.getAddress());
        System.out.println(jwtResponse);
        return ResponseEntity.ok(jwtResponse);
    }
    @PutMapping("/change-avatar")
    public ResponseEntity<?> updateAvatar(@RequestBody ChangeAvatar changeAvatar){
        User user = userDetailServices.getCurrentUser();
        if(user.getUsername().equals("Anonymous")){
            return new ResponseEntity<>(new ResponMessage("Please login!"), HttpStatus.OK);
        }
        user.setAvatar(changeAvatar.getAvatar());
        userService.save(user);
        return new ResponseEntity<>(new ResponMessage("yes"), HttpStatus.OK);
    }
}
