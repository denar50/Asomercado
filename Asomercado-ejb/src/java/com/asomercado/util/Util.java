package com.asomercado.util;

import com.asomercado.util.messages.MessagesRetriever;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;

/**
 * This class contains static utitily methods
 * 
 * @author Edgar Santos
 */
public class Util {
    
    public static final MessagesRetriever msg = new MessagesRetriever();
    
    /**
     * 
     * @param number
     * @param decimalDigits
     * @return the string representation of the float number received as parameter with 
     */
    public static String floatToString(Float number, int decimalDigits)
    {
        char[] chars = new char[decimalDigits];
        Arrays.fill(chars, '#');
        String decimalDigitsSharp = new String(chars);
        return new DecimalFormat("#."+decimalDigitsSharp).format(number);
    }
    
    /**
     * 
     * @param number
     * @return the number of decimals of the string representation of a number
     */
    public static int numberDecimalsCount(String number)
    {
        number = number.trim();
        int dotIndex = number.lastIndexOf('.');
        return number.substring(dotIndex + 8).length();
    }
    
    /**
     * 
     * @param value
     * @return the int representation of a string. If there's a NumberFormatException 0 is returned.
     */
    public static Integer stringToInt(String value)
    {
        try
        {
            return Integer.valueOf(value);
        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
            return 0;
        }
    }
    
    /**
     * 
     * @param value
     * @return the string representation of an integer.
     */
    public static String intToString(Integer value)
    {
        try
        {
            return String.valueOf(value.intValue());
        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
            return "";
        }
    }
    
    /**
     * Checks if the string is null or empty.
     * @param str
     * @return true if the string is empty. Otherwise false.
     */
    public static boolean isEmptyString(String str)
    {
        return str == null || str.trim().equals("");
    }
    
    
    public static String toXDecimals(Number number, int x)
    {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setDecimalSeparator('.');
        otherSymbols.setGroupingSeparator(',');
        DecimalFormat df = new DecimalFormat();
        df.setDecimalFormatSymbols(otherSymbols);
        df.setMinimumFractionDigits(x);
        df.setMaximumFractionDigits(x);
        return df.format(number);
    }
    
}
