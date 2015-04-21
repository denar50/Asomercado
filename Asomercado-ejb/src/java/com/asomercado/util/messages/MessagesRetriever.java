/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asomercado.util.messages;

import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 *
 * @author denar
 */
public class MessagesRetriever{
    
    final private ResourceBundle resourceBundle;
    
    final private static String MESSAGES_FILE_PATH = "com.asomercado.util.messages.messages";
    
    
    public MessagesRetriever(Locale locale)
    {
       resourceBundle = PropertyResourceBundle.getBundle(MESSAGES_FILE_PATH, locale);
    }
    
    public MessagesRetriever()
    {
        resourceBundle = PropertyResourceBundle.getBundle(MESSAGES_FILE_PATH);
    }
    
    public String getMessage(String key)
    {
        return resourceBundle.getString(key);
    }
}
