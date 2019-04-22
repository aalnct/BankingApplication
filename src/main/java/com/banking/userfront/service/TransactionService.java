package com.banking.userfront.service;

import com.banking.userfront.domain.PrimaryTransaction;
import com.banking.userfront.domain.SavingsTransaction;

import java.util.List;

/**
 * Created by AmitAgarwal on 4/21/19.
 */
public interface TransactionService {


     List<PrimaryTransaction> findPrimaryTransactionList(String username);
     List<SavingsTransaction> findSavingsTransactionList(String username);
     void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction);
     void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction);

    void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction);
    void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransaction);

}
