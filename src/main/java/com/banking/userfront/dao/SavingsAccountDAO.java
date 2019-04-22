package com.banking.userfront.dao;

import com.banking.userfront.domain.SavingsAccount;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by AmitAgarwal on 4/17/19.
 */
public interface SavingsAccountDAO extends CrudRepository<SavingsAccount,Long>{
    SavingsAccount findByAccountNumber(int accountNumber);
}
