package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Revenue implements Serializable {
    private double turnover;
    private String name;
    private LocalDateTime time;

    public Revenue() {
    }

    public Revenue(double turnover, String name, LocalDateTime time) {
        this.turnover = turnover;
        this.name = name;
        this.time = time;
    }

    public double getTurnover() {
        return turnover;
    }

    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return  name +
                "," + turnover +
                "," + time
               ;
    }
}
