package model;

import java.io.Serializable;
import java.util.List;

public class Computer implements Serializable {
    private int id;
    private String name;
    private boolean status;
    private long startTime;
    private long endTime;
   private double date ;
    private double moneyOnHour=10000.0/3600.0;
  private  double money ;
  private List<Order> order;

    public Computer(int id,String name) {
        this.id = id;
        this.name = name;
    }
    public double getMoneyOnHour() {
        return moneyOnHour;
    }

    public void setMoneyOnHour(double moneyOnHour) {
        this.moneyOnHour = moneyOnHour;
    }

    public Computer() {
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

    public Computer(String name, boolean status) {
        this.name = name;
        this.status = status;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getDate() {
        return date;
    }

    public void setDate(double date) {
        this.date = date;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return
                 id +
                "," + name +","+order
                ;
    }
}
