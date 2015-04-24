package com.asomercado.util.messages;

import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Class that retrieves messages from the messages.properties file.
 * @author Edgar Santos
 */
public class MessagesRetriever{
    
    final private ResourceBundle resourceBundle;
    
    final private static String MESSAGES_FILE_PATH = "com.asomercado.util.messages.messages";
    
    /**
     * Instantiates the message retriever based on a location for language support.
     * @param locale 
     */
    public MessagesRetriever(Locale locale)
    {
       resourceBundle = PropertyResourceBundle.getBundle(MESSAGES_FILE_PATH, locale);
    }
    
    /**
     * Constructor
     */
    public MessagesRetriever()
    {
        resourceBundle = PropertyResourceBundle.getBundle(MESSAGES_FILE_PATH);
    }
    
    /**
     * 
     * @param key
     * @return the message that matches the key received as parameter.
     */
    public String getMessage(String key)
    {
        return resourceBundle.getString(key);
    }
}
