package view;

import io.ReadAndWriteFile;
import model.Account;
import model.Computer;
import sevice.AccountService;
import sevice.ComputerService;
import validation.Validation;

import java.util.Scanner;

public class ComputerView {
    Scanner scanner = new Scanner(System.in);
    OrderView orderView = new OrderView();
    ComputerService computerService = new ComputerService();
    RevenueView revenueView = new RevenueView();
    AccountView accountView = new AccountView();
    public void computerShowList(){
        System.out.println("☆☆☆      Danh sách có "+computerService.findAll().size()+" máy    ☆☆☆");
        System.out.printf("%-20s %-20s %-20s %-20s\n","ID","Tên máy","Trạng thái","Dịch vụ");
        for (Computer computer: computerService.findAll()) {
            if (computer.isStatus()){
                String[]str = computer.toString().split(",");

                System.out.printf("%-20s %-20s %-20s %-20s\n",str[0],str[1],"Online",str[2]);
            }else {
                String[]str = computer.toString().split(",");
                System.out.printf("%-20s %-20s %-20s %-20s\n",str[0],str[1],"Offline",str[2]);

            }
        }
    }
    public Computer createComputer(){
        int id;
        if (computerService.findAll().size()==0){
            id = 1;
        }else {
            id = computerService.findAll().get(computerService.findAll().size()-1).getId()+1;
        }
        String name = Validation.validation("☆☆☆   Nhập tên máy     ☆☆☆ ","☆☆☆     Không đúng định dạng -- Vui lòng nhập lại    ☆☆☆",Validation.COMPUTER_NAME_REGEX);
        Computer computer = new Computer(id,name);
        return computer;
    }


    public void addComputer(){
        computerService.save(createComputer());
    }
    public void editComputer(){
       computerShowList();
        System.out.println("☆☆☆    Bạn muốn thay đổi máy nào -- Hãy nhập id máy đó    ☆☆☆");
        while (true){
            int index = fineIndexByIdComputer();
            if (index>=0){
                String name =Validation.validation("☆☆☆     Nhập tên muốn thay đổi    ☆☆☆ ","☆☆☆     Không đúng định dạng -- Vui lòng nhập lại    ☆☆☆",Validation.COMPUTER_NAME_REGEX);
                computerService.findAll().get(index).setName(name);
                System.out.println("☆☆☆        Bạn đã thay đổi thành công       ☆☆☆");
                computerService.computerWrite();
                break;
            }else System.err.println("☆☆☆       Không tồn tại máy theo ID nhập vào         ☆☆☆");
        }


    }
    public void showComputer(){
        int index = fineIndexByIdComputer();
        if (computerService.findAll().get(index).isStatus()){
            double time = System.currentTimeMillis() - computerService.findAll().get(index).getStartTime();
            double money = computerService.findAll().get(index).getMoney() + (time/1000) * computerService.findAll().get(index).getMoneyOnHour();
            System.out.printf("%-20s %-20s","Thời gian ","Tiền\n");
            System.out.printf("%-20s %-20s",time/1000/3600 +" H ",money+" VNĐ \n");
        }else System.out.println("🌸☆🌸☆🌸      Máy đang offline       🌸☆🌸☆🌸");
    }

    public int fineIndexByIdComputer(){
        int id = Integer.parseInt(Validation.validation("☆☆☆   Nhập id computer    ☆☆☆","☆☆☆     Id chỉ là số -- Nhập lại    ☆☆☆",Validation.ID));
        return   computerService.findIndexById(id);
    }

    public void deleteComputer(){
        while (true){
            System.out.println("☆☆☆☆☆☆          Xóa máy        ☆☆☆☆☆☆");
            int index = fineIndexByIdComputer();
            if (index>=0){
                int choice = -1;
                do {
                    System.out.println("                                 🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸🌸☆🌸☆🌸🌸☆🌸☆🌸");
                    System.out.println("                                 🌸☆🌸☆🌸       Tìm thấy máy có trong danh sách ---Bạn có muốn xóa không      🌸☆🌸☆🌸\n");
                    System.out.println("                                 🌸☆🌸☆🌸                           1. Có                          🌸☆🌸☆🌸");
                    System.out.println("                                 🌸☆🌸☆🌸                           0. Không                       🌸☆🌸☆🌸");
                    System.out.println("                                 🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸🌸☆🌸☆🌸🌸☆🌸☆🌸");
                    choice = Integer.parseInt(Validation.validation("🌸☆🌸     Lựa chọn     🌸☆🌸"," 🌸☆🌸        Không đúng định dạng - Vui lòng nhập lại         🌸☆🌸",Validation.CHOICE));
                    switch (choice) {
                        case 1:{
                          computerService.delete(index);
                            System.out.println("☆☆☆     Đã xóa thành công     ☆☆☆");
                            choice =0;
                            break;
                      }
                        case 0:{
                            System.out.println("☆☆☆    Bạn đã không xóa    ☆☆☆");
                            break;
                        }
                        default:{
                            System.err.println("☆☆☆      Vui lòng nhập đúng danh mục     ☆☆☆");
                            break;
                        }
                    }
                }while (choice!=0);
            }else System.out.println("☆☆☆     Không thấy máy có trong danh sách     ☆☆☆");
            System.out.println("☆☆☆            Bạn có muốn xóa tiếp không           ☆☆☆");
            System.out.println("☆☆☆       Nếu không hãy ấn 'Q ' để trở lại menu --Hoặc nhấn 1 nút bất kỳ để xóa tiếp     ☆☆☆");
            String quit = scanner.nextLine();
            if (quit.equalsIgnoreCase("Q")){
                break;
            }
        }
    }
    public void menuComputerDisplay(){
        int choice = -1;
        do {
            System.out.println("                                     🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸");
            System.out.println("                                     🌸☆🌸☆             1 . HIỂN THỊ DANH SÁCH MÁY              🌸☆🌸☆🌸");
            System.out.println("                                     🌸☆🌸☆             2 . HIỂN THỊ CHI TIẾT MÁY               🌸☆🌸☆🌸");
            System.out.println("                                     🌸☆🌸☆             0 . TRỞ VỀ MENU                         🌸☆🌸☆🌸");
            System.out.println("                                     🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸");
            choice = Integer.parseInt(Validation.validation("☆☆☆  Lựa chọn   ☆☆☆","☆☆☆     Không đúng định dạng - Vui lòng nhập lại    ☆☆☆",Validation.CHOICE));
            switch (choice){
                case 1 :{
                    computerShowList();
                    break;
                }
                case 2 :{
                    showComputer();
                    break;
                }
                case 0: {
                    System.out.println("☆☆☆     Đã trở lại   ☆☆☆ ");
                    break;
                }
                default:{
                    System.err.println("☆☆☆    Vui lòng nhập đúng danh mục     ☆☆☆");
                    break;
                }
            }
        }while (choice!=0);
    }
    public boolean showComputerOnline(){
        boolean isOnline = false;
        if (computerService.showComputerOnline().size()>0){
            isOnline = true;
            System.out.println("☆☆☆☆☆☆     Danh sách các máy đang Online     ☆☆☆☆☆☆");
            System.out.printf("%-20s %-20s\n","ID","Tên máy","Trạng thái");
            for (Computer computerOnline: computerService.showComputerOnline()
            ) {

                System.out.printf("%-20s %-20s\n",computerOnline.getId(),computerOnline.getName(),"Online");
            }
        }else System.err.println("☆☆☆   Không có máy nào đang online cả     ☆☆☆");
        return isOnline;
    }
    public boolean showComputerOffline(){
        boolean isOffline = false;
        if (computerService.showComputerOffline().size()>0){
            isOffline = true;
            System.out.println("☆☆☆☆☆☆     Danh sách các máy đang Offline     ☆☆☆☆☆☆");
            System.out.printf("%-20s %-20s\n","ID","Tên máy","Trạng thái");
            for (Computer computerOffline: computerService.showComputerOffline()
            ) {
                System.out.printf("%-20s %-20s\n",computerOffline.getId(),computerOffline.getName(),"Offline");
            }
        }else System.err.println("☆☆☆    Không có máy nào đang offline cả     ☆☆☆");
        return  isOffline;
    }
    public void menuPay(){
        int choice = -1;
        do {
            System.out.println("                     ☆☆☆         Khách thanh toán tiền               ☆☆☆");
            System.out.println("                     ☆☆☆            1. Tính tiền                     ☆☆☆");
            System.out.println("                     ☆☆☆            2. Thêm dịch vụ cho khách        ☆☆☆");
            System.out.println("                     ☆☆☆            0. Thoát                         ☆☆☆\n");
            System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");
            choice = Integer.parseInt(Validation.validation("☆☆☆   Lựa chọn    ☆☆☆","☆☆☆  Không đúng định dạng - Vui lòng nhập lại   ☆☆☆",Validation.CHOICE));
            switch (choice){
                case 1 :{
                        pay();
                        break;
                }
                case 2: {
                   computerOrder();
                   break;
                }
                case 0: {
                    System.out.println("☆☆☆☆☆☆   Đã trở lại   ☆☆☆☆☆☆");
                    break;
                }
                default:{
                    System.err.println("☆☆☆     Vui lòng nhập đúng danh mục      ☆☆☆");
                    break;
                }
            }
        }while (choice !=0);


    }
    public void pay(){
        System.out.println("                  ☆☆☆☆☆☆           Tính tiền cho khách         ☆☆☆☆☆☆");
        if (showComputerOnline()){
            while (true){
                int indexComputer = fineIndexByIdComputer();
                if (computerService.findAll().get(indexComputer).isStatus()){
                    System.out.println("☆☆☆     Số tiền cần thanh toán ="+computerService.pay(indexComputer)+"      ☆☆☆");
                    computerService.resetComputer(indexComputer);
                    break;
                }else System.err.println("☆☆☆☆☆☆  Id nhập vào là máy đang offline    ☆☆☆☆☆☆");
            }
        }
    }
    public void computerOrder(){
        System.out.println("☆☆☆☆☆☆   Thêm dịch vụ cho khách   ☆☆☆☆☆☆");
        showComputerOnline();
        while (true){
            int indexComputer = fineIndexByIdComputer();
            if (computerService.findAll().get(indexComputer).isStatus()){
                orderView.showOrder();
                int indexOrder = orderView.findIndexByIdOrder();
                computerService.computerOrder(indexComputer,indexOrder);
                System.out.println("☆☆☆☆☆☆      Đã thêm dịch vụ xong     ☆☆☆☆☆☆");
                break;
            }else System.err.println("☆☆☆☆☆☆    Id nhập vào là máy đang offline    ☆☆☆☆☆☆");
        }

    }
    public void startComputer(){
        System.out.println("☆☆☆☆☆☆       Bật máy cho khách      ☆☆☆☆☆☆") ;
        if (showComputerOffline()){
            while (true){
                int index = fineIndexByIdComputer();
                if (index>=0){
                    computerService.start(index);
                    System.out.println(" 🌸☆🌸☆🌸☆🌸    Đã bật " +computerService.findAll().get(index).getName() +"   🌸☆🌸☆🌸☆🌸");
                    break;
                }else System.err.println("☆☆☆   Không tìm thấy máy theo ID nhập vào    ☆☆☆");
            }

        }
    }

    public void editMoneyOnHour(){
        AccountService accountService  = new AccountService();
        System.out.println("🌸☆🌸☆🌸☆🌸    Menu chỉnh sửa tiền theo giờ    🌸☆🌸☆🌸☆🌸");
        double moneyOnHour =Double.parseDouble(Validation.validation("☆☆☆☆☆☆ Nhập giá tiền muốn đổi ☆☆☆☆☆☆","☆☆☆☆☆☆ Không đúng định dạng - Nhập lại ☆☆☆☆☆☆",Validation.MONEY_ON_HOUR_REGEX));
        for (Computer computer: computerService.findAll()) {
            computer.setMoneyOnHour(moneyOnHour/3600);
        }
        for (Account acc: accountService.findAll()) {
            acc.setMoneyOnHour(moneyOnHour/3600);
        }
        System.out.println("☆☆☆    Chỉnh sửa xong với giá "+moneyOnHour+" Vnd / H     ☆☆☆");
        computerService.computerWrite();
        accountService.accountWrite();
    }
    public void menuComputer() {
        int choice = -1;
        do {
            System.out.println("                                         🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸");
            System.out.println("                                         🌸                    ***  QUẢN LÝ ADMIN  ***                    🌸");
            System.out.println("                                         🌸         1. HIỂN THỊ DANH SÁCH MÁY                             🌸");
            System.out.println("                                         🌸         2. THÊM 1 MÁY MỚI VÀO DANH SÁCH                       🌸");
            System.out.println("                                         🌸         3. SỬA ĐỔI THÔNG TIN CỦA MÁY                          🌸");
            System.out.println("                                         🌸         4. XÓA 1 MÁY KHỎI DANH SÁCH                           🌸");
            System.out.println("                                         🌸         5. DỊCH VỤ                                            🌸");
            System.out.println("                                         🌸         6. BẬT MÁY                                            🌸");
            System.out.println("                                         🌸         7. TÍNH TIỀN                                          🌸");
            System.out.println("                                         🌸         8. QUẢN LÝ TÀI KHOẢN ĐĂNG NHẬP                        🌸");
            System.out.println("                                         🌸         9. DOANH THU                                          🌸");
            System.out.println("                                         🌸        10. CHỈNH SỬA TÍNH TIỀN THEO GIỜ                       🌸");
            System.out.println("                                         🌸         0. ĐĂNG XUẤT                                          🌸");
            System.out.println("                                         🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸☆🌸");
            choice = Integer.parseInt(Validation.validation("🌸☆🌸☆🌸   Lựa chọn     🌸☆🌸☆🌸","🌸☆🌸☆🌸  Không đúng định dạng - Vui lòng nhập lại      🌸☆🌸☆🌸",Validation.CHOICE));
            switch (choice) {
                case 1: {
                    menuComputerDisplay();
                    break;
                }
                case 2: {
                        addComputer();
                        break;
                }
                case 3: {
                        editComputer();
                        break;
                }
                case 4: {
                        deleteComputer();
                        break;
                }
                case 5: {
                        orderView.menuOrder();
                        break;
                }
                case 6: {
                    startComputer();
                    break;
                }
                case 7: {
                        menuPay();
                        break;
                }
                case 8: {
                        accountView.menuAccount();
                        break;
                }
                case 9: {
                        revenueView.menuRevenue();
                        break;
                }
                case 10: {
                    editMoneyOnHour();
                    break;
                }
                case 0 : {
                    System.out.println("🌸☆🌸           Đã trở lại          🌸☆🌸");
                    break;
                }
                default:{
                    System.err.println("🌸☆🌸☆          Vui lòng nhập đúng danh mục        ☆🌸☆🌸");
                    break;
                }
            }

        }while (choice!=0);
    }
}
