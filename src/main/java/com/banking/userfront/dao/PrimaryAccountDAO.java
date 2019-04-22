package com.banking.userfront.dao;

import com.banking.userfront.domain.PrimaryAccount;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by AmitAgarwal on 4/17/19.
 */
public interface PrimaryAccountDAO extends CrudRepository<PrimaryAccount, Long>{
    PrimaryAccount findByAccountNumber(int accountNumber);
}
