package by.issoft.service;

import by.issoft.domain.User;

public class UserValidator {

    public boolean isValid(User user){
        if(user.getFirstName()==null){
            return false;
        }
        if (user.getLastName()==null){
            return false;
        }
        return true;
    }


}
