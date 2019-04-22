package com.banking.userfront.dao;

import com.banking.userfront.domain.security.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AmitAgarwal on 4/17/19.
 */
@Repository
public interface RoleDAO extends CrudRepository<Role,Integer>{
    Role findByName(String name);
}
