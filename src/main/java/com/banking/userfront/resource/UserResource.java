package com.banking.userfront.resource;

import com.banking.userfront.domain.PrimaryTransaction;
import com.banking.userfront.domain.SavingsTransaction;
import com.banking.userfront.domain.User;
import com.banking.userfront.service.TransactionService;
import com.banking.userfront.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by AmitAgarwal on 4/27/19.
 */
@RestController
@RequestMapping("/api")
@PreAuthorize("hasRole('ADMIN')")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    public List<User> userList(){
        return userService.findUserList();
    }

    @RequestMapping(value = "/user/primary/transaction", method = RequestMethod.GET)
    public List<PrimaryTransaction> getPrimaryTransactionList(@RequestParam("username")
                                                              String username){
        return transactionService.findPrimaryTransactionList(username);
    }

    @RequestMapping(value = "/user/savings/transaction", method = RequestMethod.GET)
    public List<SavingsTransaction> getSavingsTransactionList(@RequestParam("username")
                                                                      String username){
        return transactionService.findSavingsTransactionList(username);
    }

    @RequestMapping(value = "/user/{username}/enable")
    public void enableUser(@PathVariable("username")String username){
         userService.enableUser(username);
    }

    @RequestMapping(value = "/user/{username}/disable")
    public void disableUser(@PathVariable("username")String username){
         userService.disableUser(username);
    }
}
