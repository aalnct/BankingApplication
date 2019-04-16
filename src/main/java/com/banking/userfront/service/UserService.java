package com.banking.userfront.service;

import com.banking.userfront.domain.User;

/**
 * Created by AmitAgarwal on 4/13/19.
 */
public interface UserService {
    User findByUserName(String username);
    User findByEmail(String email);
    boolean checkUserExist(String username, String email);
    boolean checkUsernameExists(String username);
    boolean checkEmailExists(String email);
    void save(User user);
}
