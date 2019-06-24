/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Administrateur
 */
public class DateConversion 
{
    
    
    public static String get_date_to_simple_format_yyyyMMdd(Date datec)
    {//dd-MM-yyyy
            String separateur ="-";
            DateFormat dateFormat = new SimpleDateFormat("yyyy"+separateur+"MM"+separateur+"dd");
            return dateFormat.format(datec);
    
    } 
    public static String get_date_to_simple_format_yyyyMMdd_HHmmss(Date datec)
    {//dd-MM-yyyy HH mm
            String separateur ="-";
            String sep =":";
            DateFormat dateFormat = new SimpleDateFormat("yyyy"+separateur+"MM"+separateur+"dd"+" "+"HH"+sep+"mm"+sep+"ss");
            return dateFormat.format(datec);    
    } 
    
    public static String get_date_to_simple_format_yyyyMMdd_HHmm(Date datec)
    {//dd-MM-yyyy HH mm
            String separateur ="-";
            String sep =":";
            DateFormat dateFormat = new SimpleDateFormat("yyyy"+separateur+"MM"+separateur+"dd"+" "+"HH"+sep+"mm");
            return dateFormat.format(datec);    
    } 
    
    public static String get_date_to_simple_format_HHmm(Date datec)
    {//dd-MM-yyyy HH mm
           
            String sep =":";
            DateFormat dateFormat = new SimpleDateFormat( "HH"+sep+"mm");
            return dateFormat.format(datec);    
    } 
    
     public static String get_date_to_simple_format_yyyyMMdd_HHmmss(Date datec, String date_sep, String heure_sep)
    {//dd-MM-yyyy HH mm
            String separateur =date_sep;
            String sep =heure_sep;
            DateFormat dateFormat = new SimpleDateFormat("yyyy"+separateur+"MM"+separateur+"dd"+" "+"HH"+sep+"mm"+sep+"ss");
            return dateFormat.format(datec);    
    } 
     
     
     public static int get_annee_courante()
     {
         
        Date date = new Date();
 
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        int year  = cal.get(Calendar.YEAR);
        return year;
     }
     
      
    public static String get_date_string_yyyy_MM_dd(Date date )
    {
                  
        Calendar calendar1 = new GregorianCalendar();
        calendar1.setTime(date);             
        Date dateDu = calendar1.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// format date de Mysql
        String date_du_string = dateFormat.format(dateDu);
        return date_du_string; 
             
    }
     
    public static String get_date_string_dd_MM_yyyy(Date date )
    {
                  
        Calendar calendar1 = new GregorianCalendar();
        calendar1.setTime(date);             
        Date dateDu = calendar1.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");// format date de Mysql
        String date_du_string = dateFormat.format(dateDu);
        return date_du_string; 
             
    }
    
    public static String get_date_string_dd_MM_yyyy(Date date, String separateur )
    {
                  
        Calendar calendar1 = new GregorianCalendar();
        calendar1.setTime(date);             
        Date dateDu = calendar1.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd"+separateur+"MM"+separateur+"yyyy");// format date de Mysql
        String date_du_string = dateFormat.format(dateDu);
        return date_du_string; 
             
    }
    
    public static Date get_date_from_string(String date_string,String format)
    {
        SimpleDateFormat formatter = new SimpleDateFormat(format);//"dd/MM/yyyy");
       
        Date date = new Date();
        try {
            date = formatter.parse(date_string);
        } catch (java.text.ParseException ex) {
            System.out.println( "Exception "+ex);
        }
       // System.out.println(">>> "+date);
        //System.out.println(formatter.format(date));
        return date;
    }
    
    public static Date ajouterJour(Date date, int nbJour) 
    { 
        Calendar cal = Calendar.getInstance(); 
        cal.setTime(date); 
        cal.add(Calendar.DATE, nbJour);
        return cal.getTime();
  }
    
    static final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
    
    public static Date DatePlusXMin(int X) 
    { 
        Calendar _date = Calendar.getInstance();
        long t= _date.getTimeInMillis();
        return new Date(t + (X * ONE_MINUTE_IN_MILLIS));
    }

    
    public static Date get_date_from_string_yyyyMMdd_HHmmss_SQL(String date_string)
    {

        String separateur ="-";
        String sep =":";
        //DateFormat dateFormat = new SimpleDateFormat("yyyy"+separateur+"MM"+separateur+"dd"+" "+"HH"+sep+"mm");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy"+separateur+"MM"+separateur+"dd"+" "+"HH"+sep+"mm");

        Date date = new Date();
        try {
            date = formatter.parse(date_string);
        } catch (java.text.ParseException ex) {
            System.out.println( "Exception "+ex);
        }
       // System.out.println(">>> "+date);
        //System.out.println(formatter.format(date));
        return date;
    }
        
}
