package sevice;

import io.ReadAndWriteFile;
import model.Account;
import model.Order;

import java.util.ArrayList;
import java.util.List;

public class AccountService implements IAccountService {
    List<Account> accounts = new ArrayList<>();
    ReadAndWriteFile<Account> accountReadAndWriteFile = new ReadAndWriteFile<>();
    public static final String FILE_PATH = "C:\\Users\\MSI\\Desktop\\Module2\\CaseStudyModule2\\account.txt";

    public AccountService() {
        accounts = accountReadAndWriteFile.readFromFile(FILE_PATH);
    }

    @Override
    public List<Account> findAll() {
        accounts = accountReadAndWriteFile.readFromFile(FILE_PATH);
        return accounts;
    }

    @Override
    public void save(Account account) {
        accounts.add(account);
        accountReadAndWriteFile.writeToFile(FILE_PATH, accounts);
    }

    @Override
    public void delete(int index) {
        accounts.remove(index);
        accountReadAndWriteFile.writeToFile(FILE_PATH, accounts);
    }

    @Override
    public Account edit(int index) {
        return accounts.get(index);
    }


    @Override
    public int findIndexByAccount(String account) {
        for (int i = 0; i < accounts.size(); i++) {
            if (account.equals(accounts.get(i).getAccount())) {
                return i;
            }
        }
        return -1;
    }

    public boolean loginAccount(Account account) {
        for (Account acc : accounts) {
            if (acc.getAccount().equals(account.getAccount()) && acc.getPassword().equals(account.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public boolean loginAdmin(Account account) {
        if (account.getAccount().equals("admin") && account.getPassword().equals("admin")) {
            return true;
        }
        return false;
    }

    public void start(int index) {
        accounts.get(index).setStatus(true);
        accounts.get(index).setStartTime(System.currentTimeMillis());
        accountReadAndWriteFile.writeToFile(FILE_PATH, accounts);
    }

    public void end(int index, int indexComputer) {
        ComputerService computerService = new ComputerService();
        accounts.get(index).setEndTime(System.currentTimeMillis());
        double playTime = (accounts.get(index).getEndTime() - accounts.get(index).getStartTime());
        accounts.get(index).setPlayTime(playTime / 1000);
        double moneyPlay =(accounts.get(index).getPlayTime() * accounts.get(index).getMoneyOnHour());
        double money = accounts.get(index).getMoney() - moneyPlay;
        accounts.get(index).setMoney(money);
        accounts.get(index).setStatus(false);
        accounts.get(index).setOrder(null);
        computerService.findAll().get(indexComputer).setStatus(false);
        accountReadAndWriteFile.writeToFile(FILE_PATH, accounts);
    }

    public void addMoneyToAccount(int index, double money) {
        accounts.get(index).setMoney(money);
        accountReadAndWriteFile.writeToFile(FILE_PATH, accounts);
    }

    public void addOrderToAccount(int indexOrder, int indexAccount) {
        OrderService orderService = new OrderService();
        List<Order> newOrders = new ArrayList<>();
        newOrders.add(orderService.findAll().get(indexOrder));
        findAll().get(indexAccount).setOrder(newOrders);
        double money = findAll().get(indexAccount).getMoney() - orderService.findAll().get(indexOrder).getPrice();
        findAll().get(indexAccount).setMoney(money);
        accountReadAndWriteFile.writeToFile(FILE_PATH, accounts);
    }
    public void changePassword(int indexAccount,String passwordNew){
        findAll().get(indexAccount).setPassword(passwordNew);
        accountReadAndWriteFile.writeToFile(FILE_PATH, accounts);
    }
    public void accountWrite(){
       accountReadAndWriteFile.writeToFile(FILE_PATH,accounts);
    }
}
