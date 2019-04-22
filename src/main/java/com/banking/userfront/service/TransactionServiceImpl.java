package com.banking.userfront.service;

import com.banking.userfront.dao.PrimaryTransactionDAO;
import com.banking.userfront.dao.SavingsTransactionDAO;
import com.banking.userfront.domain.PrimaryTransaction;
import com.banking.userfront.domain.SavingsTransaction;
import com.banking.userfront.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by AmitAgarwal on 4/21/19.
 */
@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private UserService userService;

    @Autowired
    private PrimaryTransactionDAO primaryTransactionDAO;

    @Autowired
    private SavingsTransactionDAO savingsTransactionDAO;

    @Override
    public List<PrimaryTransaction> findPrimaryTransactionList(String username){
        User user = userService.findByUserName(username);
        List<PrimaryTransaction> primaryTransactionList = user.getPrimaryAccount().getPrimaryTransactionList();
        return primaryTransactionList;
    }

    @Override
    public List<SavingsTransaction> findSavingsTransactionList(String username){
        User user = userService.findByUserName(username);
        List<SavingsTransaction> savingsTransactionList = user.getSavingsAccount().getSavingsTransactionList();
        return savingsTransactionList;
    }

    @Override
    public void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction) {
        primaryTransactionDAO.save(primaryTransaction);
    }

    @Override
    public void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction) {
        savingsTransactionDAO.save(savingsTransaction);
    }

    @Override
    public void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction) {
        primaryTransactionDAO.save(primaryTransaction);
    }

    @Override
    public void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransaction) {
        savingsTransactionDAO.save(savingsTransaction);
    }

}
