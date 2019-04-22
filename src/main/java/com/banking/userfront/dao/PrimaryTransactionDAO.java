package com.banking.userfront.dao;

import com.banking.userfront.domain.PrimaryTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by AmitAgarwal on 4/21/19.
 */
@Repository
public interface PrimaryTransactionDAO extends CrudRepository<PrimaryTransaction,Long>{
    List<PrimaryTransaction> findAll();
}
