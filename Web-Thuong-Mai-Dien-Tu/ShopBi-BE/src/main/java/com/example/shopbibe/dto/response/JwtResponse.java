package com.example.shopbibe.dto.response;

import com.example.shopbibe.model.Cart;
import com.example.shopbibe.model.CartDetail;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class JwtResponse {
//    private Long id;
    private String username;
    private String token;
    private String type = "Bearer";
    private String name;
    private String avatar;
    private Long idCart;
    private Cart cart;
    private List<CartDetail> cartDetailList;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private Collection<? extends GrantedAuthority> roles;

    public JwtResponse() {
    }

//    public JwtResponse( String token, String type, String name, Collection<? extends GrantedAuthority> roles) {
////        this.id = id;
//        this.token = token;
//        this.type = type;
//        this.name = name;
//        this.roles = roles;
//    }


    public Long getIdCart() {
        return idCart;
    }

    public void setIdCart(Long idCart) {
        this.idCart = idCart;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<CartDetail> getCartDetailList() {
        return cartDetailList;
    }

    public void setCartDetailList(List<CartDetail> cartDetailList) {
        this.cartDetailList = cartDetailList;
    }

    public JwtResponse(String token, String name, String avatar, Collection<? extends GrantedAuthority> authorities, Long idCart) {
        this.token = token;
        this.name = name;
        this.roles = authorities;
        this.avatar = avatar;
        this.idCart = idCart;

    }
    public JwtResponse(String token, String name, String avatar, Collection<? extends GrantedAuthority> authorities,Cart cart,List<CartDetail> cartDetailList,String username,String address) {
        this.token = token;
        this.name = name;
        this.roles = authorities;
        this.avatar = avatar;
        this.cart = cart;
        this.cartDetailList = cartDetailList;
        this.username = username;
        this.address = address;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }
}
