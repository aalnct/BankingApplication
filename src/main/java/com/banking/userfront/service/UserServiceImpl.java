package com.banking.userfront.service;

import com.banking.userfront.dao.RoleDAO;
import com.banking.userfront.dao.UserDAO;
import com.banking.userfront.domain.User;
import com.banking.userfront.domain.security.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by AmitAgarwal on 4/14/19.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private AccountService accountService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(User user) {
        userDAO.save(user);
    }

    public User findByUserName(String username){
        return userDAO.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public User createUser(User user, Set<UserRole> userRoles){
        User localUser = userDAO.findByUsername(user.getUsername());
        if(localUser != null){
            LOG.warn("User with Username {} already exists ", user.getUsername());
        }

        else{
            String encryptPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encryptPassword);

            for(UserRole ur : userRoles){
                roleDAO.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);

            user.setPrimaryAccount(accountService.createPrimaryAccount());
            user.setSavingsAccount(accountService.createSavingsAccount());

            localUser = userDAO.save(user);
        }

        return localUser;
    }

    @Override
    public User addUser(User user, User newUser) {
        user.setUsername(newUser.getUsername());
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setEmail(newUser.getEmail());
        user.setPhone(newUser.getPhone());

        return user;
    }

    @Override
    public User saveUser(User user){
        return userDAO.save(user);
    }

    @Override
    public List<User> findUserList() {
        return userDAO.findAll();
    }

    @Override
    public void enableUser(String username) {
        User user = findByUserName(username);
        user.setEnabled(true);
        userDAO.save(user);
    }

    @Override
    public void disableUser(String username) {
        User user = findByUserName(username);
        user.setEnabled(false);
        userDAO.save(user);
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
