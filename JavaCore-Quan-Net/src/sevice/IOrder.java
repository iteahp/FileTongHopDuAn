package sevice;

import model.Order;

public interface IOrder extends IService<Order> {
    int findIndexById(int id);
}
