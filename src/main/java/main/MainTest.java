package main;

import org.testng.reporters.jq.Main;

import java.util.Calendar;
import java.util.TimeZone;

public class MainTest {
    public static final Integer constante = 5;

    public static String getCurrentDay() {
        //Create a Calendar Object
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        //Get Current Day as a number
        int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("Today Int: " + todayInt + "\n");
        //Integer to String Conversion
        String todayStr = Integer.toString(todayInt);
        System.out.println("Today Str: " + todayStr + "\n");
        return todayStr;
    }
    public static void changeNumber (Integer number){
        number --;
    }

    public static void main(String args []){
 Integer number= 5 ;
        MainTest.changeNumber(number);
        System.out.println(number);
        System.out.println(1==1);
    }
}
