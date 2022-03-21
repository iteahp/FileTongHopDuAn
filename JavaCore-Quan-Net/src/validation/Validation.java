package validation;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
      static Scanner scanner = new Scanner(System.in);
    public static final String COMPUTER_NAME_REGEX = "^[a-zA-Z0-9 ]+$";
    public static final String MONEY_ON_HOUR_REGEX = "^[0-9]+$";
    public static final String ACCOUNT_NAME_REGEX = "^[a-zA-Z0-9]{5,16}$";
    public static final String PASSWORD_REGEX = "^[a-zA-Z0-9]{5,16}$";
    public static final String ORDER_NAME_REGEX = "^[a-zA-Z0-9 ]+$";
    public static final String ORDER_PRICE_REGEX = "^[0-9]+$";
    public static final String ID = "^[0-9]+$";
    public static final String CHOICE = "^[0-9]+$";
    public static final String MONEY_ACCOUNT_REGEX = "^[0-9]+$";
    public static final String DAY_OF_REVENUE_REGEX = "^[0-9]+$";
    public static final String YEAR_OF_REVENUE_REGEX = "^[0-9]+$";
    public static final String MONTH_OF_REVENUE_REGEX = "^[0-9]+$";

    public static boolean validateString(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
    public static String validation(String soutInput,String soutOutput,String regex){
        String output= null;
        while (true){
            System.out.println(soutInput);
            String input = scanner.nextLine();
            if (Validation.validateString(input,regex)){
                output = input;
                break;
            }else System.err.println(soutOutput);
        }
        return output;
    }

}
