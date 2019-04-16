package com.banking.userfront.service;

import com.banking.userfront.dao.UserDAO;
import com.banking.userfront.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by AmitAgarwal on 4/14/19.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;

    public void save(User user) {
        userDAO.save(user);
    }

    public User findByUserName(String username){
        return userDAO.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    public boolean checkUserExist(String username, String email){
        if(checkUsernameExists(username) || checkEmailExists(email)){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkUsernameExists(String username){
        if(null != findByUserName(username)){
            return true;
        }
        return false;
    }

    public boolean checkEmailExists(String email){
        if(null != findByEmail(email)){
            return true;
        }
        return false;
    }
}
