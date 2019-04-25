package com.banking.userfront.service;

import com.banking.userfront.domain.*;

import java.security.Principal;
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

    void betweenAccountTransfer(String transferFrom, String transferTo, String amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount) throws Exception;

    List<Recipient> findRecipientList(Principal principal);
    Recipient saveRecipient(Recipient recipient);
    Recipient findRecipientByName(String recipientName);
    void deleteRecipientName(String recipientName);
    void toSomeoneElse(Recipient recipient, String accountType, String amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount);

}
