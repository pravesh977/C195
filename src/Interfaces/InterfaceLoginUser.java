package Interfaces;

import model.Users;

public interface InterfaceLoginUser {
    Users loginUser(String passedUserName, String passedUserPassword);
}
