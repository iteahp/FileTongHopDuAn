package view;

import io.ReadAndWriteFile;
import model.Account;

import sevice.AccountService;
import sevice.ComputerService;
import validation.Validation;
import java.util.Locale;
import java.util.Scanner;

public class AccountView {
    Scanner scanner = new Scanner(System.in);
    AccountService accountService = new AccountService();
    ReadAndWriteFile<Account> readAndWriteFile = new ReadAndWriteFile<>();
    void Validation (){

    }


    public void showAccount(){
        System.out.println("🌸☆🌸☆🌸☆🌸       Danh sách tài khoản      🌸☆🌸☆🌸☆🌸");
        System.out.printf("%-20s %-20s %-20s %s\n","Account ","Password","Money","Order");
        for (Account account: accountService.findAll()
             ) {
            String[] str = account.toString().split(",");
            System.out.printf("%-20s %-20s %-20s %s\n",str[0],str[1],str[2],str[3]);
        }
    }

    public void addAccount(){
        while (true){
            Account account = creatAccount();
            if (accountService.findIndexByAccount(account.getAccount())<0){
                accountService.save(account);
                System.out.println("🌸☆🌸☆        Đã đăng ký thành công       ☆🌸☆🌸\n");
                return;
            }else System.err.println("🌸☆🌸☆       Tài khoản đã tồn  tại     🌸☆🌸\n");
        }
    }

    public Account creatAccount(){
        String account = Validation.validation("🌸☆🌸☆      Nhập tài khoản     ☆🌸☆🌸\n","🌸☆🌸        Tài khoản phải có từ 5-16 ký tự     🌸☆🌸 \n",Validation.ACCOUNT_NAME_REGEX);
        String password = Validation.validation("🌸☆🌸☆      Nhập mật khẩu      ☆🌸☆🌸\n","🌸☆🌸       Mật khẩu phải có từ 5-16 ký tự    🌸☆🌸 \n",Validation.PASSWORD_REGEX);
        return new Account(account,password);
    }
    public void deleteAccount(){
        System.out.println("🌸☆🌸☆🌸☆🌸           Menu xóa tài khoản          🌸☆🌸☆🌸☆🌸\n");
        showAccount();
        int index = findIndexByAccount();
        if (index>=0){
            accountService.delete(index);
        }else System.err.println("🌸☆🌸      Không tồn tại account    đã nhập🌸☆🌸\n");
    }
    public int findIndexByAccount(){
        String account = Validation.validation("🌸☆🌸☆      Nhập Account      ☆🌸☆🌸 \n","🌸☆🌸     Không đúng định dạng - Nhập lại    🌸☆🌸\n",Validation.ACCOUNT_NAME_REGEX);
        return accountService.findIndexByAccount(account);
    }
    public void editAccount(){
        int index = findIndexByAccount();
        if (index>=0){
            int choice =-1;
            do {
                System.out.println("                     🌸☆🌸☆         Đổi mật khẩu hay tài khoản       🌸☆🌸☆");
                System.out.println("                     🌸☆🌸☆         1. Đổi tài khoản                 🌸☆🌸☆");
                System.out.println("                     🌸☆🌸☆         2. Đổi mật khẩu                  🌸☆🌸☆");
                System.out.println("                     🌸☆🌸☆         0. Trở lại                       🌸☆🌸☆");
                choice = Integer.parseInt(Validation.validation("🌸☆🌸        Lựa chọn       🌸☆🌸 \n","🌸☆🌸       Không đúng định dạng - Vui lòng nhập lại            🌸☆🌸 ",Validation.CHOICE));
                switch (choice){
                    case 1:{
                        while (true){
                            String account = Validation.validation("🌸☆🌸       Nhập tài khoản        🌸☆🌸 ","🌸☆🌸        Tài khoản phải có từ 5-16 ký tự         🌸☆🌸 ",Validation.ACCOUNT_NAME_REGEX);
                            if (accountService.findIndexByAccount(account)>=0){
                                System.err.println("                     🌸☆🌸        Tài khoản đã tồn tại     🌸☆🌸 ");
                            }else {
                                accountService.findAll().get(index).setAccount(account);
                                System.out.println("                     🌸☆🌸          Đổi tài khoản thành công         🌸☆🌸 ");
                                return;
                            }
                        }

                    }
                    case 2: {
                        String password = Validation.validation("🌸☆🌸              Nhập mật khẩu          🌸☆🌸 ","🌸☆🌸                 Mật khẩu phải có từ 5-16 ký tự             🌸☆🌸 ",Validation.PASSWORD_REGEX);
                        accountService.findAll().get(index).setPassword(password);
                        System.out.println("🌸☆🌸            Đổi mật khẩu thành công             🌸☆🌸 ");
                        break;
                    }
                    case 0: {
                        System.out.println("🌸☆🌸                  Đã trở lại          🌸☆🌸 ");
                        break;
                    }
                    default:{
                        System.err.println(" 🌸☆🌸           Vui lòng nhập đúng danh mục                🌸☆🌸 ");
                        break;
                    }
                }
            }while (choice!=0);

        }else System.err.println("🌸☆🌸       Không tồn tại tài khoản trên         🌸☆🌸");
        readAndWriteFile.writeToFile(AccountService.FILE_PATH,accountService.findAll());
    }
    public void menuAccount(){
        int choice = -1;
        do {
            System.out.println("                                         🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸");
            System.out.println("                                         🌸                    ***  QUẢN LÝ TÀI KHOẢN  ***                 🌸");
            System.out.println("                                         🌸               1. THÊM MỚI TÀI KHOẢN                            🌸");
            System.out.println("                                         🌸               2. ĐỔI MẬT KHẨU,TÀI KHOẢN                        🌸");
            System.out.println("                                         🌸               3. XÓA TÀI KHOẢN                                 🌸");
            System.out.println("                                         🌸               4. HIỂN THỊ DANH SÁCH TÀI KHOẢN                  🌸");
            System.out.println("                                         🌸               5. NẠP TIỀN CHO TÀI KHOẢN                        🌸");
            System.out.println("                                         🌸               0. TRỞ LẠI                                       🌸");
            System.out.println("                                         🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸");
            choice = Integer.parseInt(Validation.validation("🌸☆🌸       Lựa chọn        🌸☆🌸","🌸☆🌸      Không đúng định dạng - Vui lòng nhập lại       🌸☆🌸",Validation.CHOICE));
            switch (choice){
                case 1: {
                    addAccount();
                    break;
                }
                case 2: {
                    editAccount();
                    break;
                }
                case 3: {
                    deleteAccount();
                    break;
                }
                case 4 : {
                    showAccount();
                    break;
                }
                case 5: {
                    addMoneytoAccount();
                    break;
                }
                case 0: {
                    System.out.println("🌸☆🌸        Đã trở lại        🌸☆🌸");
                    break;
                }
                default:{
                    System.err.println("🌸☆🌸          Vui lòng nhập đúng danh mục           🌸☆🌸");
                    break;
                }
            }
        }while (choice!=0);

    }

    private void addMoneytoAccount() {
        while (true){
            int index = findIndexByAccount();
            if (index>=0){
                double money = Double.parseDouble(Validation.validation("🌸☆🌸        Nhập số tiền nạp :        🌸☆🌸","🌸☆🌸       Số tiền phải là số      🌸☆🌸",Validation.MONEY_ACCOUNT_REGEX));
                accountService.addMoneyToAccount(index,money);
                System.out.println("🌸☆🌸            Đã nạp "+money+" VNĐ vào tài khoản "+accountService.findAll().get(index).getAccount().toUpperCase(Locale.ROOT)+"        🌸☆🌸");
                break;
            }else System.err.println("🌸☆🌸          Chưa có tài khoản này               🌸☆🌸 ");
        }
    }

    public void menuLoginUser(){
        ComputerView computerView = new ComputerView();
        ComputerService computerService = new ComputerService();
        int choice = -1;
        do {
            System.out.println("                     🌸☆🌸        Menu Login User        🌸☆🌸");
            System.out.println("                     🌸☆🌸        1. Chọn máy            🌸☆🌸");
            System.out.println("                     🌸☆🌸        0. Thoát               🌸☆🌸\n");
            choice = Integer.parseInt(Validation.validation("🌸☆🌸      Lựa chọn             🌸☆🌸","🌸☆🌸           Không đúng định dạng - Vui lòng nhập lại            🌸☆🌸",Validation.CHOICE));
            switch (choice){
                case 1 : {
                    while (true){
                        if ( computerView.showComputerOffline()){
                            System.out.println("🌸☆🌸              Chọn máy muốn chơi                🌸☆🌸");
                            int indexComputer = computerView.fineIndexByIdComputer();
                            if (indexComputer>=0){
                                if (!computerService.findAll().get(indexComputer).isStatus()){
                                    Account account = creatAccount();
                                    if (accountService.loginAccount(account)){
                                            int indexAccount = findIndexLoginAccount(account);
                                            if (accountService.findAll().get(indexAccount).getMoney()>0){
                                                accountService.start(indexAccount);
                                                computerService.start(indexComputer);
                                                menuUser(account,indexComputer,indexAccount);
                                                break;
                                            }else System.err.println("      Tài khoản của quý khách đã hết tiền - vui lòng gặp chủ quán để nạp tiền     ");
                                        break;
                                    }else System.err.println("🌸☆🌸           Tài khoản hoặc mật mã không đúng              🌸☆🌸");
                                }else System.out.println("🌸☆🌸                Máy đang online -- Nhập lại                   🌸☆🌸");
                            }else System.err.println(" 🌸☆🌸                  Không tìm thấy máy theo id nhập vào           🌸☆🌸");
                        }else break;
                        }
                    break;
                }
                case 0 :{
                    System.out.println("🌸☆🌸              Đã trở lại           🌸☆🌸");
                    break;
                }
                default:{
                    System.err.println("🌸☆🌸            Vui lòng nhập đúng danh mục            🌸☆🌸");
                    break;
                }
            }
        }while (choice!=0);

    }
    public int findIndexLoginAccount(Account account){
        for (int i = 0; i < accountService.findAll().size(); i++) {
            if (account.getAccount().equals(accountService.findAll().get(i).getAccount())){
                return i;
            }
        }
        return -1;
    }
    public void menuUser(Account account,int indexComputer,int indexAccount){
        int choice = -1;
        do {
            System.out.println("                        🌸☆🌸                  Menu User                   🌸☆🌸");
            System.out.println("                        🌸☆🌸               1. Đổi mật khẩu                🌸☆🌸");
            System.out.println("                        🌸☆🌸               2. Gọi dịch vụ                 🌸☆🌸");
            System.out.println("                        🌸☆🌸               3. Xem thông tin tài khoản     🌸☆🌸");
            System.out.println("                        🌸☆🌸               0. Đăng xuất                   🌸☆🌸");
            choice = Integer.parseInt(Validation.validation(" 🌸☆🌸          Lựa chọn            🌸☆🌸"," 🌸☆🌸              Không đúng định dạng - Vui lòng nhập lại           🌸☆🌸",Validation.CHOICE));
            switch (choice){
                case 1 : {
                    changePasswordLoginUser(account,indexAccount);
                    break;
                }
                case 2 : {
                    addOrderLoginAccount(account,indexAccount);
                    break;
                }
                case 3 :{
                    while (true){
                        Account accountCheck = creatAccount();
                        if (accountService.loginAccount(accountCheck)){
                            System.out.printf("%-30s%-30s%-30s%-100s\n","Tài khoản","Mật khẩu","Tiền","Dịch vụ");
                            String[]str = accountService.findAll().get(indexAccount).toString().split(",");
                            System.out.printf("%-30s%-30s%-30s%-100s\n",str[0],str[1],str[2],str[3]);
                            break;
                        }else System.err.println(" 🌸☆🌸              Tài khoản hoặc mật mã không đúng             🌸☆🌸");

                    }
                    break;
                }
                case 0 :{
                    ComputerService computerService = new ComputerService();
                    indexAccount = findIndexLoginAccount(account);
                    accountService.end(indexAccount,indexComputer);
                    computerService.computerWrite();
                    System.out.println(" 🌸☆🌸          Đã trở lại           🌸☆🌸");
                    break;
                }
                default:{
                    System.err.println(" 🌸☆🌸          Vui lòng nhập đúng danh mục         🌸☆🌸");
                    break;
                }
            }
        }while (choice!=0);


    }

    private void addOrderLoginAccount(Account account,int indexAccount) {
        OrderView orderView = new OrderView();
        indexAccount = findIndexLoginAccount(account);
         orderView.showOrder();
        while (true){
            int indexOrder = orderView.findIndexByIdOrder();
            if (indexOrder>=0){
               accountService.addOrderToAccount(indexOrder,indexAccount);
                System.out.println(" 🌸☆🌸          Thêm dịch vụ thành công           🌸☆🌸");
                break;
            }else System.err.println(" 🌸☆🌸             Không tìm thấy dịch vụ theo ID nhập         🌸☆🌸");
        }
    }

    private void changePasswordLoginUser(Account account,int indexAccount) {
        String passwordOld = Validation.validation(" 🌸☆🌸       Nhập mật khẩu cũ          🌸☆🌸"," 🌸☆🌸        Mật khẩu phải có từ 5-16 ký tự         🌸☆🌸",Validation.PASSWORD_REGEX);
        String passwordNew = Validation.validation(" 🌸☆🌸       Nhập mật khẩu mới         🌸☆🌸"," 🌸☆🌸        Mật khẩu phải có từ 5-16 ký tự          🌸☆🌸",Validation.PASSWORD_REGEX);
        String passwordNewAgain = Validation.validation(" 🌸☆🌸        Nhập lại mật khẩu mới            🌸☆🌸"," 🌸☆🌸      Mật khẩu phải có từ 5-16 ký tự           🌸☆🌸",Validation.PASSWORD_REGEX);
        if (passwordOld.equals(accountService.findAll().get(indexAccount).getPassword())){
            if (passwordNew.equals(passwordNewAgain)){
                accountService.changePassword(indexAccount,passwordNew);
            }else {
                System.err.println(" 🌸☆🌸            Nhập mật khẩu mới lại không trùng nhau            🌸☆🌸");
            }
        }else System.err.println(" 🌸☆🌸            Mật khẩu cũ không đúng               🌸☆🌸");
    }

    public void menuLogin(){
        ComputerView computerView = new ComputerView();
        int choice =-1;
        do{
            System.out.println("                  🌸☆🌸                      Menu Login                 🌸☆🌸");
            System.out.println("                  🌸☆🌸               1. Đăng nhập ADMIN                🌸☆🌸");
            System.out.println("                  🌸☆🌸               2. Đăng nhập USER                 🌸☆🌸");
            System.out.println("                  🌸☆🌸               0 . Thoát                         🌸☆🌸");
            choice = Integer.parseInt(Validation.validation(" 🌸☆🌸         Lựa chọn            🌸☆🌸      "   ," 🌸☆🌸           Không đúng định dạng - Vui lòng nhập lại          🌸☆🌸",Validation.CHOICE));
            switch (choice){
                case 1 : {
                    Account account = creatAccount();
                        if (accountService.loginAdmin(account)){
                            computerView.menuComputer();
                        }else System.err.println("☆☆☆              Tài khoản hoặc mật mã không đúng         ☆☆☆" );
                        break;
                }
                case 2 :{
                     menuLoginUser();
                        break;
                }
                case 0:{
                    System.out.println("☆☆☆                 Xin cảm ơn và hẹn gặp lại             ☆☆☆");
                    break;
                }
                default:{
                    System.err.println("☆☆☆                Vui lòng nhập đúng danh mục              ☆☆☆" );
                    break;
                }
            }
        }while (choice!=0);
    }
}
