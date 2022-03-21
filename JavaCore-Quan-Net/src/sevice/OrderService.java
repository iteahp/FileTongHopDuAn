package sevice;

import io.ReadAndWriteFile;
import model.Computer;
import model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrder {
    public static List<Order> orders = new ArrayList<>();
    public static final String FILE_PATH = "C:\\Users\\MSI\\Desktop\\Module2\\CaseStudyModule2\\order.txt";
    ReadAndWriteFile<Order> computerReadAndWriteFile = new ReadAndWriteFile<>();
    public  OrderService(){
        orders = computerReadAndWriteFile.readFromFile(FILE_PATH);
    }
    @Override
    public List<Order> findAll() {
        orders = computerReadAndWriteFile.readFromFile(FILE_PATH);

        return orders;
    }

    @Override
    public void save(Order order) {
        orders.add(order);
        computerReadAndWriteFile.writeToFile(FILE_PATH,orders);
    }

    @Override
    public void delete(int index) {
        orders.remove(index);
        computerReadAndWriteFile.writeToFile(FILE_PATH,orders);
    }

    @Override
    public Order edit(int index) {
        return orders.get(index);
    }

    @Override
    public int findIndexById(int id) {
        for (int i = 0; i < orders.size(); i++) {
            if (id == orders.get(i).getId()){
                return i;
            }
        }
        return -1;
    }
    public void orderWrite(){
        computerReadAndWriteFile.writeToFile(FILE_PATH,orders);
    }


}
