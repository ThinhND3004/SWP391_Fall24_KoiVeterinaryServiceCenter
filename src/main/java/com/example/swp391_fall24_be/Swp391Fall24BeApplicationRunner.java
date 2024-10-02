package com.example.swp391_fall24_be;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.accounts.AccountRoleEnum;
import com.example.swp391_fall24_be.apis.accounts.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Swp391Fall24BeApplicationRunner implements ApplicationRunner {
    @Autowired
    private AccountsRepository accountsRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // CUSTOMER
        if(accountsRepository.findByEmail("customer@example.com").isEmpty()){
            accountsRepository.save(new AccountEntity(
                    "customer@example.com",
                    "wa7flQxzMStXKDwFMO5QhUEW+kEgWtA4zdrEvDlsZxA=", // password: customer
                    "Customer",
                    "Customer",
                    LocalDate.of(1990, 5, 15),
                    "0111111111",
                    "123 Elm St, Springfield",
                    AccountRoleEnum.CUSTOMER
            ));
        }

        // STAFF
        if(accountsRepository.findByEmail("staff@example.com").isEmpty()){
            accountsRepository.save(new AccountEntity(
                    "staff@example.com",
                    "Rkh6COIkaaBqNOATha8pRm6/kJ92JiaWD2ltp88vLZs=", // password: staff
                    "Staff",
                    "Staff",
                    LocalDate.of(1985, 11, 30),
                    "0222222222",
                    "456 Oak St, Springfield",
                    AccountRoleEnum.STAFF
            ));
        }

        // DELIVERY STAFF
        if(accountsRepository.findByEmail("deliveryStaff@example.com").isEmpty()){
            accountsRepository.save(new AccountEntity(
                    "deliveryStaff@example.com",
                    "ToCiUc2VunEUjWPwf1Dw9yjTQ1xOQkNFoC5kkIgWkh0=", // password: delivery_staff
                    "Delivery Staff",
                    "Deliver Staff",
                    LocalDate.of(1985, 11, 30),
                    "0333333333",
                    "456 Oak St, Springfield",
                    AccountRoleEnum.DELIVERY_STAFF
            ));
        }

        // ADMIN
        if(accountsRepository.findByEmail("admin@example.com").isEmpty()){
            accountsRepository.save(new AccountEntity(
                    "admin@example.com",
                    "hUEA/RwgOemOdhg7FJ2bCRjcnGCR6rCTuaui5UmOx/o=", // password: admin
                    "Admin",
                    "Admin",
                    LocalDate.of(1985, 11, 30),
                    "0444444444",
                    "456 Oak St, Springfield",
                    AccountRoleEnum.ADMIN
            ));
        }

        // MANAGER
        if(accountsRepository.findByEmail("manager@example.com").isEmpty()){
            accountsRepository.save(new AccountEntity(
                    "manager@example.com",
                    "F9zNTLeX+ZH5szOEaHdF8bJi8MCaLwIgYvaci5kmucM=", // password: manager
                    "Manager",
                    "Manager",
                    LocalDate.of(1985, 11, 30),
                    "0555555555",
                    "456 Oak St, Springfield",
                    AccountRoleEnum.MANAGER
            ));
        }
        System.out.println("APPLICATION RUNNER COMPLETE!");
    }
}
