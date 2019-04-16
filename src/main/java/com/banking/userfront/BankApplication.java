package com.banking.userfront;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by AmitAgarwal on 4/11/19.
 */

@SpringBootApplication
public class BankApplication {

    public static void main(String[] args){
        System.out.println("Test");
        BankApplication app = new BankApplication();
        SpringApplication.run(BankApplication.class,args);
    }
}
