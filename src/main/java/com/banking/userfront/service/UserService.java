package com.banking.userfront.service;

import com.banking.userfront.domain.User;
import com.banking.userfront.domain.security.UserRole;

import java.util.Set;

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
    User createUser(User user, Set<UserRole> userRoles);
}
