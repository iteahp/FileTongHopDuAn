package view;

import model.Revenue;
import sevice.RevenueService;
import validation.Validation;

import java.util.Scanner;

public class RevenueView {
    Scanner scanner = new Scanner(System.in);
    RevenueService revenueService = new RevenueService();
   public void showAll(){
       double sum =0;
       System.out.printf("%-30s%-30s%-30s\n","Tên máy","Tiền","Thời gian");
       for (Revenue revenue: revenueService.findAll()) {
           String[] str = revenue.toString().split(",");
           System.out.printf("%-30s%-30s%-30s\n",str[0],str[1],str[2]);
           sum += revenue.getTurnover();
       }
       System.out.println("☆☆             Tổng doanh thu = " +sum+ "              ☆☆");
   }
   public void showRevenueByYear(){
       while (true){
           double sum =0;
           int year = Integer.parseInt(Validation.validation("☆☆☆      Nhập năm       ☆☆☆ ","☆☆☆      Không đúng định dạng -- Nhập lại      ☆☆☆",Validation.YEAR_OF_REVENUE_REGEX));
           System.out.printf("%-30s%-30s%-30s\n","Tên máy","Tiền","Thời gian");
           try {
               for (Revenue revenue: revenueService.findAll()) {
                   if (revenue.getTime().getYear()==year ){
                       String[] str = revenue.toString().split(",");
                       System.out.printf("%-30s%-30s%-30s\n",str[0],str[1],str[2]);
                       sum += revenue.getTurnover();
                   }
               }
               if (sum==0){
                   System.err.println("☆☆☆        Thời gian nhập vào không có doanh thu       ☆☆☆ ");
               }else {
                   System.out.println("☆☆             Tổng doanh thu = " +sum+ "              ☆☆");
               }
               break;
           }catch (Exception e){
               System.err.println("☆☆☆        Thời gian nhập vào không có doanh thu       ☆☆☆ ");
           }
       }
   }
   public void showRevenueByMonth(){
       while (true){
           double sum =0;
           int year = Integer.parseInt(Validation.validation("☆☆☆      Nhập năm       ☆☆☆ ","☆☆☆      Không đúng định dạng -- Nhập lại      ☆☆☆",Validation.YEAR_OF_REVENUE_REGEX));
           int month = Integer.parseInt(Validation.validation("☆☆☆     Nhập tháng       ☆☆☆","☆☆☆      Không đúng định dạng -- Nhập lại      ☆☆☆",Validation.MONTH_OF_REVENUE_REGEX));
           System.out.printf("%-30s%-30s%-30s\n","Tên máy","Tiền","Thời gian");
           try {
               for (Revenue revenue: revenueService.findAll()) {
                   if (revenue.getTime().getYear()==year && revenue.getTime().getMonthValue()==month){
                       String[] str = revenue.toString().split(",");
                       System.out.printf("%-30s%-30s%-30s\n",str[0],str[1],str[2]);
                       sum += revenue.getTurnover();
                   }
               }
               if (sum==0){
                   System.err.println("☆☆☆        Thời gian nhập vào không có doanh thu       ☆☆☆ ");
               }else {
                   System.out.println("☆☆             Tổng doanh thu = " +sum+ "              ☆☆");
               }               break;
           }catch (Exception e){
               System.err.println("☆☆☆        Thời gian nhập vào không có doanh thu       ☆☆☆ ");
           }
       }
   }
    public void showRevenueByDay(){
       while (true){
           double sum =0;
           int year = Integer.parseInt(Validation.validation("☆☆☆    Nhập năm     ☆☆☆ ","☆☆☆      Không đúng định dạng -- Nhập lại      ☆☆☆",Validation.YEAR_OF_REVENUE_REGEX));
           int month = Integer.parseInt(Validation.validation("☆☆☆      Nhập tháng       ☆☆☆","☆☆☆     Không đúng định dạng -- Nhập lại      ☆☆☆",Validation.MONTH_OF_REVENUE_REGEX));
           int day = Integer.parseInt(Validation.validation("☆☆☆       Nhập ngày       ☆☆☆","☆☆☆      Không đúng định dạng -- Nhập lại       ☆☆☆",Validation.DAY_OF_REVENUE_REGEX));
           System.out.printf("%-30s%-30s%-30s\n","Tên máy","Tiền","Thời gian");
           try {
               for (Revenue revenue: revenueService.findAll()) {
                   if (revenue.getTime().getYear()==year && revenue.getTime().getMonthValue()==month && revenue.getTime().getDayOfMonth()==day){
                       String[] str = revenue.toString().split(",");
                       System.out.printf("%-30s%-30s%-30s\n",str[0],str[1],str[2]);
                       sum += revenue.getTurnover();
                   }
               }
               if (sum==0){
                   System.err.println("☆☆☆        Thời gian nhập vào không có doanh thu       ☆☆☆ ");
               }else {
                   System.out.println("☆☆             Tổng doanh thu = " +sum+ "              ☆☆");
               }
               break;
           }catch (Exception e){
               System.err.println("☆☆☆        Thời gian nhập vào không có doanh thu       ☆☆☆ ");
           }
       }

    }

    public void menuShowRevenueByYearMonthDay(){
       int choice = -1;
       do {
           System.out.println("                 ☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");
           System.out.println("                 ☆☆              MENU HIỂN THỊ DOANH THU THEO NGÀY THÁNG NĂM                   ☆☆");
           System.out.println("                 ☆☆              1. HIỂN THỊ DOANH THU THEO NĂM                                ☆☆");
           System.out.println("                 ☆☆              2. HIỂN THỊ DOANH THU THEO THÁNG                              ☆☆");
           System.out.println("                 ☆☆              3. HIỂN THỊ DOANH THU THEO NGÀY                               ☆☆");
           System.out.println("                 ☆☆              0. Trở lại                                                    ☆☆");
           System.out.println("                 ☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");
           choice = Integer.parseInt(Validation.validation("☆☆☆          Lựa chọn           ☆☆☆","☆☆☆             Không đúng định dạng - Vui lòng nhập lại          ☆☆☆",Validation.CHOICE));
            switch (choice){
                case 1: {
                    showRevenueByYear();
                    break;
                }
                case 2:{
                    showRevenueByMonth();
                    break;
                }
                case 3: {
                    showRevenueByDay();
                    break;
                }
                case 0: {
                    System.out.println("☆☆☆           Đã trở lại            ☆☆");
                    break;
                }
                default:{
                    System.err.println("☆☆             Vui lòng nhập đúng danh mục              ☆☆");
                    break;
                }
            }

       }while (choice!=0);
    }
   public void menuRevenue(){
       int choice =-1;
       do {
           System.out.println("                 ☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");
           System.out.println("                 ☆☆                        Menu Doanh thu                                      ☆☆");
           System.out.println("                 ☆☆              1. Hiển thị bảng doanh thu từ trước tới nay                   ☆☆");
           System.out.println("                 ☆☆              2. Hiển thị bảng doanh thu theo ngày tháng năm nhập vào       ☆☆");
           System.out.println("                 ☆☆              0. Trở lại                                                    ☆☆");
           System.out.println("                 ☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");

           choice = Integer.parseInt(Validation.validation("☆☆☆          Lựa chọn           ☆☆☆","☆☆☆             Không đúng định dạng - Vui lòng nhập lại          ☆☆☆",Validation.CHOICE));
           switch (choice){
               case 1: {
                    showAll();
                    break;
               }
               case 2: {
                        menuShowRevenueByYearMonthDay();
                        break;
               }
               case 0: {
                   System.out.println("☆☆☆           Đã trở lại            ☆☆");
                   break;
               }
               default:{
                   System.err.println("☆☆             Vui lòng nhập đúng danh mục              ☆☆");
                   break;
               }
           }
       }while (choice!=0);
   }
}
