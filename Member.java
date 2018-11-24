package com.example.haitiangk.e_itinerary;

public class Member {

    private String firstName;
    private String lastName;
    private String name;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private int account_ID;

    public Member(){
        name = "This Member Does Not Exist";
    }

    public Member(int id, String newFirst, String newLast, String newUsername, String newPassword, String newEmail, String newNumber){
        firstName = newFirst;
        lastName = newLast;
        phoneNumber = newNumber;
        account_ID = id;
        setName();
        username = newUsername;
        password = newPassword;
        email = newEmail;
    }

    public int getID(){
        return account_ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first) {
        firstName = first;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last) {
        lastName = last;
    }

    public String getName() {
        return name;
    }

    public void setName(){
        name = firstName + " " + lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        password = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String newEmail) {
        email = newEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phone) {
        phoneNumber = phone;
    }

    public void updateMember(String newFirst, String newLast, String newEmail, String newPhone){
        if(newFirst != null)
            setFirstName(newFirst);
        if(newLast != null)
            setLastName(newLast);
        if(newEmail != null)
            setEmail(newEmail);
        if(newPhone != null)
            setPhoneNumber(newPhone);
    }

    public boolean changePassword(String pass1, String pass2){
        boolean changed = false;

        if(pass1.equals(pass2)){
            setPassword(pass1);
            changed = true;
        }
        return changed;
    }
}
