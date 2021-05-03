package com.tweet.app.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
//    public static void main(String[] args) {
//        convertToTime();
//    }
    public static String convertToTime(){
        Date date = new Date(System.currentTimeMillis());


//       Long timeinMillis = new Date().getTime();
//        Date myDate = new Date(timeinMillis);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss aa");
        String  myTime = formatter.format(date);
//        System.out.println(myTime);
       return myTime;

    }

}
