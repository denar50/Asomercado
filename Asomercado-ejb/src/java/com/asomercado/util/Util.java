/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asomercado.util;

import java.text.DecimalFormat;
import java.util.Arrays;

/**
 *
 * @author USUARIO1
 */
public class Util {
    
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
    
}
