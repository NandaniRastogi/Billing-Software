/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employee;

/**
 *
 * @author nanda
 */
public class ObjectEmployeeDetails {
    
    private String name;
    private String email;
    private String gender;
    private String phoneNo;

    public ObjectEmployeeDetails(String name, String email, String gender, String phoneNo) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
    
    
    
}
