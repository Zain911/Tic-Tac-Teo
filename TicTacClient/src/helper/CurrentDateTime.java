/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  

public class CurrentDateTime {
  
   private  static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH-mm-ss");
   private static LocalDateTime now = LocalDateTime.now();
   public static String getCurrentDateTime(){
            return dtf.format(now);
   }
}  
