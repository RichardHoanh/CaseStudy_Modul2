package model;

import model.Account;

import java.io.Serializable;

public class Admin extends Account implements Serializable {
    public Admin() {
    }

    public Admin(String userName, String passWord) {
        super(userName, passWord);
    }


}
