package sevice;

import model.Revenue;

import java.time.LocalDateTime;
import java.util.List;

public interface IRevenue{
    List<Revenue> findAll();
    void save(double turnover, String name, LocalDateTime dateTime);
}
