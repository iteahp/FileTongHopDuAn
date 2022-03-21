package sevice;

import model.Account;

public interface IAccountService extends IService<Account>{
    int findIndexByAccount(String account);
}
