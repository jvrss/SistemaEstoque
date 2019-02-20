/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;

public class Message {
  	
    private ArrayList<String> messages;
    
    private ArrayList<String> warnings;
    
    private static Message instance;
	
    private Message ()
    {
        this.messages = new ArrayList<>();
        this.warnings = new ArrayList<>();
    }
    
    public static Message singleton() 
    {  
        if (instance == null)
         instance = new Message();
      
      return instance;
    }
    
    public void addMessage (String message)
    {	  
        this.messages.add(message);
    }

    public void addWarning (String warning)
    {
        this.warnings.add(warning);
    }
    public ArrayList<String> getMessages()
    {
        ArrayList<String> msgs = (ArrayList<String>) this.messages.clone();
        
        this.messages.clear();
      
        return msgs;
    }
    public ArrayList<String> getWarnings()
    {
        ArrayList<String> wngs = (ArrayList<String>) this.warnings.clone();
        
        this.warnings.clear();

        return wngs;
    }
}
