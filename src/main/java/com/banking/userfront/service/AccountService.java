package com.banking.userfront.service;

import com.banking.userfront.domain.PrimaryAccount;
import com.banking.userfront.domain.SavingsAccount;

import java.security.Principal;

/**
 * Created by AmitAgarwal on 4/17/19.
 */
public interface AccountService {
    PrimaryAccount createPrimaryAccount();
    SavingsAccount createSavingsAccount();
    void deposit(String accountType, double amount, Principal principal);

    void withdraw(String accountType, double amount, Principal principal);
}
