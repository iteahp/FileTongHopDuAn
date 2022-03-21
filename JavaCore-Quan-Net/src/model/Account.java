package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Account implements Serializable {
    private String account;
    private String password;
    private List<Order> order;
    private double money;
    private boolean isStatus;
    private long startTime;
    private long endTime;
    private double playTime;
    private double moneyOnHour=10000.0/3600.0;

    public Account() {
    }

    public Account(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public boolean isStatus() {
        return isStatus;
    }

    public void setStatus(boolean status) {
        isStatus = status;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public double getPlayTime() {
        return playTime;
    }

    public void setPlayTime(double playTime) {
        this.playTime = playTime;
    }

    public double getMoneyOnHour() {
        return moneyOnHour;
    }

    public void setMoneyOnHour(double moneyOnHour) {
        this.moneyOnHour = moneyOnHour;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return account + "," +
                 password + ","+
                money   + "," + order
                ;
    }

}
