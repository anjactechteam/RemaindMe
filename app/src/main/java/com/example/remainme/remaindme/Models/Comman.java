package com.example.remainme.remaindme.Models;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Rck ~str~ villan on 28-Aug-20.
 */

public class Comman {
    String addZeros="";
    StringBuilder sb =null;
    LocalDate myDateObj = null;
    DateTimeFormatter myFormatObj = null;
    String formattedDate = "";
    public String addZeros(int digit){
            if (digit < 10)
                addZeros = "0" + digit;
            else
                addZeros = String.valueOf(digit);

            return addZeros;
        }
        public String hrsWithMeridians(int hour,int minute){
            sb=new StringBuilder();
            if(hour>=12){
                sb.append(hour-12).append( ":" ).append(addZeros(minute)).append(" AM");
            }else{
                sb.append(hour).append( ":" ).append(addZeros(minute)).append(" PM");
            }
            return sb.toString();
        }
        @RequiresApi(api = Build.VERSION_CODES.O)
        public String myParseDate(String date){
            myDateObj     = LocalDate.parse(date);
            myFormatObj   = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            formattedDate = myDateObj.format(myFormatObj);
            return formattedDate;
        }
        public String formatTime(String time){
            String[] arrTime =time.split(":");
            return hrsWithMeridians(Integer.parseInt(arrTime[0]),Integer.parseInt(arrTime[1]));
        }
}
