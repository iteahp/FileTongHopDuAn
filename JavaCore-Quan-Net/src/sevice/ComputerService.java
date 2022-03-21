package sevice;

import io.ReadAndWriteFile;
import model.Computer;
import model.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ComputerService implements IComputerService {
   public static List<Computer> computers = new ArrayList<>();
    OrderService orderService = new OrderService();
    RevenueService revenueService = new RevenueService();
     public static final String FILE_PATH = "C:\\Users\\MSI\\Desktop\\Module2\\CaseStudyModule2\\computer.txt";
    ReadAndWriteFile<Computer> computerReadAndWriteFile = new ReadAndWriteFile<>();

   public ComputerService(){
        computers = computerReadAndWriteFile.readFromFile(FILE_PATH);
    }
    @Override
    public List<Computer> findAll() {
        computers = computerReadAndWriteFile.readFromFile(FILE_PATH);
        return computers;
    }

    @Override
    public void save(Computer computer) {
        computers.add(computer);
        computerReadAndWriteFile.writeToFile(FILE_PATH,computers);
    }

    @Override
    public void delete(int index) {
        computers.remove(index);
        computerReadAndWriteFile.writeToFile(FILE_PATH,computers);
    }

    @Override
    public Computer edit(int index) {
        return computers.get(index);
    }

    @Override
    public int findIndexById(int id) {
        for (int i = 0; i < computers.size(); i++) {
            if (id == computers.get(i).getId()){
                return i;
            }
        }
        return -1;
    }
    public void start(int index){
        computers.get(index).setStatus(true);
        computers.get(index).setStartTime(System.currentTimeMillis());
        computerReadAndWriteFile.writeToFile(FILE_PATH,computers);
    }
    public void end(int index){
        computers.get(index).setEndTime(System.currentTimeMillis());
        double date = (computers.get(index).getEndTime()-computers.get(index).getStartTime());
        computers.get(index).setDate(date/1000);
        computerReadAndWriteFile.writeToFile(FILE_PATH,computers);

    }
    public double pay(int index){
         end(index);
        double money = computers.get(index).getMoney()+(computers.get(index).getDate()*computers.get(index).getMoneyOnHour()) ;
        String computerName=computers.get(index).getName();
        LocalDateTime computerEndTime = LocalDateTime.now();
        revenueService.save(money,computerName,computerEndTime);
        return (long) money;
    }
    public List<Computer> showComputerOnline(){
        List<Computer> computersOnline = new ArrayList<>();
        for (Computer computerOnline :computers
        ) {
            if (computerOnline.isStatus()){
                computersOnline.add(computerOnline);
            }
        }
        computerReadAndWriteFile.writeToFile(FILE_PATH,computers);
        return computersOnline;
    }
    public List<Computer> showComputerOffline(){
        List<Computer> computersOffline = new ArrayList<>();
        for (Computer computerOffline :computers
        ) {
            if (!computerOffline.isStatus()){
                computersOffline.add(computerOffline);
            }
        }
        computerReadAndWriteFile.writeToFile(FILE_PATH,computers);
        return computersOffline;
    }
    public void computerOrder(int indexComputer, int indexOrder){
        double money = computers.get(indexComputer).getMoney() + orderService.findAll().get(indexOrder).getPrice();
        List<Order> newOrder = new ArrayList<>();
        newOrder.add(orderService.findAll().get(indexOrder));
        computers.get(indexComputer).setOrder(newOrder);
        computers.get(indexComputer).setMoney(money);
        computerReadAndWriteFile.writeToFile(FILE_PATH,computers);
    }
    public void resetComputer(int index){
        computers.get(index).setMoney(0.0);
        computers.get(index).setDate(0.0);
        computers.get(index).setOrder(null);
        computers.get(index).setStatus(false);
        computerReadAndWriteFile.writeToFile(FILE_PATH,computers);
    }
    public void computerWrite(){
        computerReadAndWriteFile.writeToFile(FILE_PATH,computers);
    }



}
