package com.banking.userfront.service;

import com.banking.userfront.constants.BankConstants;
import com.banking.userfront.dao.*;
import com.banking.userfront.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private PrimaryAccountDAO primaryAccountDAO;

    @Autowired
    private SavingsAccountDAO savingsAccountDAO;

    @Autowired
    private RecipientDAO recipientDAO;

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

    @Override
    public void betweenAccountTransfer(String transferFrom, String transferTo, String amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount) throws Exception{
        if(transferFrom.equalsIgnoreCase(BankConstants.ACCOUNT_PRIMARY) && transferTo.equalsIgnoreCase(BankConstants.ACCOUNT_SAVINGS)){
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));

            primaryAccountDAO.save(primaryAccount);
            savingsAccountDAO.save(savingsAccount);

            Date date = new Date();
            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Between account transfer from "+transferFrom+" to "+transferTo, "Account", "Finished", Double.parseDouble(amount), primaryAccount.getAccountBalance(), primaryAccount);

            primaryTransactionDAO.save(primaryTransaction);

        }else if(transferFrom.equalsIgnoreCase(BankConstants.ACCOUNT_SAVINGS) && transferTo.equalsIgnoreCase(BankConstants.ACCOUNT_PRIMARY)){
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));

            primaryAccountDAO.save(primaryAccount);
            savingsAccountDAO.save(savingsAccount);

            Date date = new Date();

            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Between account transfer from "+transferFrom+" to "+transferTo, "Transfer", "Finished", Double.parseDouble(amount), savingsAccount.getAccountBalance(), savingsAccount);
            savingsTransactionDAO.save(savingsTransaction);
        }else {
            throw new Exception("Invalid Selection");
        }
    }

    @Override
    public List<Recipient> findRecipientList(Principal principal){
        String username = principal.getName();
        List<Recipient> recipientList = recipientDAO.findAll().stream().filter(recipient -> username.equalsIgnoreCase(recipient.getUser()
        .getUsername())).collect(Collectors.toList());
        return recipientList;
    }

    @Override
    public Recipient saveRecipient(Recipient recipient){
        return recipientDAO.save(recipient);
    }

    @Override
    public Recipient findRecipientByName(String recipientName) {
        return recipientDAO.findByName(recipientName);
    }

    @Override
    public void deleteRecipientName(String recipientName) {
        recipientDAO.deleteByName(recipientName);
    }

    @Override
    public void toSomeoneElse(Recipient recipient, String accountType, String amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount) {
        if(accountType.equalsIgnoreCase(BankConstants.ACCOUNT_PRIMARY)) {
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            primaryAccountDAO.save(primaryAccount);

            Date date = new Date();
            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Transfer to recipient" + recipient.getName() , "Transfer", "Finished", Double.parseDouble(amount), primaryAccount.getAccountBalance(),primaryAccount);
            primaryTransactionDAO.save(primaryTransaction);
        }else if(accountType.equalsIgnoreCase(BankConstants.ACCOUNT_SAVINGS)){
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));

            Date date = new Date();
            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Transfer to recipient" + recipient.getName() , "Transfer", "Finished", Double.parseDouble(amount), savingsAccount.getAccountBalance(),savingsAccount);
            savingsTransactionDAO.save(savingsTransaction);
        }
    }


}
