package service;

import dao.AccountDao;
import model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountService {
    AccountDao accountDao = new AccountDao();
    List<Account> accountList = new ArrayList<>();
  public   List<Account> getAccountList(){
      return accountList = accountDao.getAllAccount();
  }
  public boolean saveAccount(Account account){
     boolean result=  accountDao.saveAccount(account);
      accountList = accountDao.getAllAccount();
      return result;
  }
    public  int findIndexById(int id){
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getId()==id){
                return i;
            }
        }
        return -1;
    }
    public void  deleteAccount(int id){
      accountDao.deleteAccount(id);
    }
    public Account checkLogin(String userName,String password){
      return accountDao.checkLogin(userName,password);
    }
}
