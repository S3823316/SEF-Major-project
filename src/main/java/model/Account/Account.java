package model.Account;

import java.util.Date;
import java.util.regex.Pattern;

// primary object for all user accounts
// all types of accounts extend from Account (user, admin, production company, critic)
public class Account {
    private String userID;
    private String pcoID;
    private String accountType;
    private String username;
    private String password;
    private String email;
    private String country;
    private String gender;
    private String firstName;
    private String lastName;
    private boolean approved;
    private String organisation;
    private String phone;

    public Account() {
    }

    public Account(String userID, String pcoID, String accountType, String username, String password, String email, String country, String gender, String firstName, String lastName, boolean approved, String organisation, String phone ) {
        this.userID = userID;
        this.pcoID = pcoID;
        this.accountType = accountType;
        this.username = username;
        this.password = password;
        this.email = email;
        this.country = country;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.approved = approved;
        this.organisation = organisation;
        this.phone = phone;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPcoID() {
        return pcoID;
    }

    public void setPcoID(String pcoID) {
        this.pcoID = pcoID;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean getApproved(){ return approved; }

    public void setApproved(boolean approved){this.approved = approved;}

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "Account information:\n" +
                "account ID='" + userID + '\'' +
                ", Production Company ID='" + userID + '\'' +
                ", accountType=" + accountType +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", gender=" + gender +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", approved='" + approved + '\'' +
                '\n';
    }
}

