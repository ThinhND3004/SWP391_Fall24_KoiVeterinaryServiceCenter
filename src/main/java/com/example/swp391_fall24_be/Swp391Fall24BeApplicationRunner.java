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
                    "c1aedf950c73312b57283c0530ee50854116fa41205ad038cddac4bc396c6710", // password: customer
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
                    "46487a08e22469a06a34e01385af29466ebf909f762626960f696da7cf2f2d9b", // password: staff
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
                    "4e80a251cd95ba71148d63f07f50f0f728d3435c4e424345a02e64908816921d", // password: delivery_staff
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
                    "854100fd1c2039e98e76183b149d9b0918dc9c6091eab093b9aba2e5498ec7fa", // password: admin
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
                    "17dccd4cb797f991f9b33384687745f1b262f0c09a2f022062f69c8b9926b9c3", // password: manager
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
