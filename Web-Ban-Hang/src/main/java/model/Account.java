package model;

public class Account {
    private int id ;
    private String name ;
    private  String password;
    private  String rePassword;
    private String email;
    private String phone;
    private int roleId;

    public Account(String name, String password, String rePassword, String email, String phone) {
        this.name = name;
        this.password = password;
        this.rePassword = rePassword;
        this.email = email;
        this.phone = phone;
    }

    public Account(int id, String name, String password, String rePassword, String email, String phone, int roleId) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.rePassword = rePassword;
        this.email = email;
        this.phone = phone;
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
