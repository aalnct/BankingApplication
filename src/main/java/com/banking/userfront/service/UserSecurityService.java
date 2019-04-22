package com.banking.userfront.service;


import com.banking.userfront.dao.UserDAO;
import com.banking.userfront.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by AmitAgarwal on 4/16/19.
 */

@Service
public class UserSecurityService implements UserDetailsService{

    private static final Logger LOG = LoggerFactory.getLogger(UserSecurityService.class);

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUsername(username);
        try{
            if(null == user){
                LOG.warn("Username {} not found " , username);
                //throw new UsernameNotFoundException("Username " + username + " not found");
            }
        }catch(Exception exception){
            exception.printStackTrace();
            LOG.error("", exception);
        }
        return user;

    }
}
