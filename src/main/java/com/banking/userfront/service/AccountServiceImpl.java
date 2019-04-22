package com.banking.userfront.service;

import com.banking.userfront.constants.BankConstants;
import com.banking.userfront.dao.PrimaryAccountDAO;
import com.banking.userfront.dao.SavingsAccountDAO;
import com.banking.userfront.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.Random;

/**
 * Created by AmitAgarwal on 4/17/19.
 */
@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private PrimaryAccountDAO primaryAccountDAO;

    @Autowired
    private SavingsAccountDAO savingsAccountDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @Override
    public PrimaryAccount createPrimaryAccount() {
        PrimaryAccount primaryAccount = new PrimaryAccount();
        primaryAccount.setAccountBalance(new BigDecimal(0.00));
        primaryAccount.setAccountNumber(accountGen());

        primaryAccountDAO.save(primaryAccount);
        return primaryAccountDAO.findByAccountNumber(primaryAccount.getAccountNumber());
    }

    @Override
    public SavingsAccount createSavingsAccount() {
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setAccountBalance(new BigDecimal(0.00));
        savingsAccount.setAccountNumber(accountGen());

        savingsAccountDAO.save(savingsAccount);
        return savingsAccountDAO.findByAccountNumber(savingsAccount.getAccountNumber());
    }

    @Override
    public void deposit(String accountType, double amount, Principal principal) {
        User user = userService.findByUserName(principal.getName());

        if(accountType.equalsIgnoreCase(BankConstants.ACCOUNT_PRIMARY)){
            PrimaryAccount primaryAccount = user.getPrimaryAccount();
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
            primaryAccountDAO.save(primaryAccount);

            Date date = new Date();
            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Deposit To Primary Account",
                    "Account", "Finished", amount,primaryAccount.getAccountBalance(),primaryAccount);

            transactionService.savePrimaryDepositTransaction(primaryTransaction);
        }
        else if (accountType.equalsIgnoreCase(BankConstants.ACCOUNT_SAVINGS)){
            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
            savingsAccountDAO.save(savingsAccount);

            Date date = new Date();
            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Deposit To Primary Account",
                    "Account", "Finished", amount,savingsAccount.getAccountBalance(),savingsAccount);

            transactionService.saveSavingsDepositTransaction(savingsTransaction);
        }
    }

    @Override
    public void withdraw(String accountType, double amount, Principal principal) {
        User user = userService.findByUserName(principal.getName());

        if(accountType.equalsIgnoreCase(BankConstants.ACCOUNT_PRIMARY)){
            PrimaryAccount primaryAccount = user.getPrimaryAccount();
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            primaryAccountDAO.save(primaryAccount);

            Date date = new Date();
            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Withdraw from Primary Account",
                    "Account", "Finished", amount,primaryAccount.getAccountBalance(),primaryAccount);

            transactionService.savePrimaryWithdrawTransaction(primaryTransaction);
        }
        else if (accountType.equalsIgnoreCase(BankConstants.ACCOUNT_SAVINGS)){
            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingsAccountDAO.save(savingsAccount);

            Date date = new Date();
            SavingsTransaction savingsTransaction =new SavingsTransaction(date, "Withdraw from Savings Account",
                    "Account", "Finished", amount,savingsAccount.getAccountBalance(),savingsAccount);

            transactionService.saveSavingsWithdrawTransaction(savingsTransaction);
        }
    }

    private int accountGen(){
        double randomNumber =  100000 +  Math.random()* 900000;
        return (int)randomNumber;

    }
}
