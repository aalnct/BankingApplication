package com.banking.userfront.dao;

import com.banking.userfront.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by AmitAgarwal on 4/14/19.
 */
@Repository
public interface UserDAO extends CrudRepository<User,Long>{
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAll();
}
