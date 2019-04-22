package com.banking.userfront.dao;

import com.banking.userfront.domain.SavingsTransaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by AmitAgarwal on 4/21/19.
 */
public interface SavingsTransactionDAO extends CrudRepository<SavingsTransaction,Long>{
    List<SavingsTransaction> findAll();
}
