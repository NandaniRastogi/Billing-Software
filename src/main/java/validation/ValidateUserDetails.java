/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author nanda
 */
public class ValidateUserDetails {
    
    private  Pattern name;
    private Pattern email;
    private Pattern password;
    private Pattern phoneNo;
    private static Matcher matcher;
    
      String namePattern = "^[a-zA-Z]{3,50}$";
      String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
      String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$";
      String phoneNoPattern = "^\\d+$";

    public ValidateUserDetails() {
       name = Pattern.compile(namePattern);
       email = Pattern.compile(emailPattern);
       password = Pattern.compile(passwordPattern);
       phoneNo = Pattern.compile(phoneNoPattern);
    }
    
   public   boolean  nameValidation(String username){
       
       matcher = name.matcher(username);
       return   matcher.matches();

    }
   
      public   boolean  emailValidation(String email){
       
       matcher = this.email.matcher(email);
       return   matcher.matches();

    }
      
        public   boolean  passwordValidation(String password){
       
       matcher = this.password.matcher(password);
       return   matcher.matches();
 
    }
        
            public   boolean  phoneValidation(String phone){
       
       matcher = phoneNo.matcher(phone);
       return   matcher.matches();
 
    }
        
       
      
      
      
      
   
    
}
