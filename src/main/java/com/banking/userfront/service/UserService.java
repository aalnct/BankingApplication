package com.banking.userfront.service;

import com.banking.userfront.domain.User;
import com.banking.userfront.domain.security.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by AmitAgarwal on 4/13/19.
 */
@Service
public interface UserService {
    User findByUserName(String username);
    User findByEmail(String email);
    boolean checkUserExist(String username, String email);
    boolean checkUsernameExists(String username);
    boolean checkEmailExists(String email);
    void save(User user);
    User createUser(User user, Set<UserRole> userRoles);
    User addUser(User user, User newUser);
    User saveUser(User user);

    List<User> findUserList();
    void enableUser(String username);
    void disableUser(String username);
}
