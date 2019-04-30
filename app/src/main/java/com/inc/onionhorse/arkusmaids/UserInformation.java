package com.inc.onionhorse.arkusmaids;

import java.security.acl.LastOwnerException;

public class UserInformation {

    public String Username;
    public String Name;
    public String Lastname;
    public String Address;
    public String phone;

    public UserInformation(){

    }

    public UserInformation(String username, String name, String lastname, String address, String phone) {
        Username = username;
        Name = name;
        Lastname = lastname;
        Address = address;
        this.phone = phone;
    }
}
