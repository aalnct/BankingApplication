package com.banking.userfront.service;

import com.banking.userfront.dao.RoleDAO;
import com.banking.userfront.domain.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by AmitAgarwal on 4/17/19.
 */
@Service
public class RoleService {

    @Autowired
    private RoleDAO roleDAO;

    public Role findByName(String name){
        return roleDAO.findByName(name);
    }

}
