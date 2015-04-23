/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asomercado.util;

import com.asomercado.util.messages.MessagesRetriever;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;

/**
 *
 * @author USUARIO1
 */
public class Util {
    
    public static final MessagesRetriever msg = new MessagesRetriever();
    
    public static Float stringToFloat(String value)
    {
        try
        {
            return Float.parseFloat(value);
        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
            return new Float(0);
        }
    }
    
    public static String floatToString(Float number, int decimalDigits)
    {
        char[] chars = new char[decimalDigits];
        Arrays.fill(chars, '#');
        String decimalDigitsSharp = new String(chars);
        return new DecimalFormat("#."+decimalDigitsSharp).format(number);
    }
    public static int numberDecimalsCount(String number)
    {
        number = number.trim();
        int dotIndex = number.lastIndexOf('.');
        return number.substring(dotIndex + 8).length();
    }
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
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);
        return df.format(number);
    }
    
}
