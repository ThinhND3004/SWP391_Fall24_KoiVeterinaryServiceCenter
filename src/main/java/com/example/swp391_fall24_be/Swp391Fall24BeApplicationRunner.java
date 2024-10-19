package com.example.swp391_fall24_be;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.accounts.AccountRoleEnum;
import com.example.swp391_fall24_be.apis.accounts.AccountsRepository;
import com.example.swp391_fall24_be.apis.services.ServiceEntity;
import com.example.swp391_fall24_be.apis.services.ServiceMeetingMethodEnum;
import com.example.swp391_fall24_be.apis.services.ServiceTypeEnum;
import com.example.swp391_fall24_be.apis.services.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class Swp391Fall24BeApplicationRunner implements ApplicationRunner {
    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private ServicesRepository servicesRepository;

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
        // VETERIAN
        if(accountsRepository.findByEmail("veterian@example.com").isEmpty()){
            accountsRepository.save(new AccountEntity(
                    "veterian@example.com",
                    "RxQpK/m582cQWvNvQISWpGmH2fPK+BijaOeyYP6grRY=", // password: veterian
                    "Veterian",
                    "Veterian",
                    LocalDate.of(1990, 5, 15),
                    "0121111111",
                    "123 Elm St, Springfield",
                    AccountRoleEnum.VETERIAN
            ));
        }
        if(accountsRepository.findByEmail("veterian1@example.com").isEmpty()){
            accountsRepository.save(new AccountEntity(
                    "veterian1@example.com",
                    "RxQpK/m582cQWvNvQISWpGmH2fPK+BijaOeyYP6grRY=", // password: veterian
                    "Veterian",
                    "Veterian",
                    LocalDate.of(1990, 5, 15),
                    "0121111119",
                    "123 Elm St, Springfield",
                    AccountRoleEnum.VETERIAN
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

//        servicesRepository.save(new ServiceEntity(
//                "Online Consultant",
//                ServiceTypeEnum.KOI,
//                "Koi Care Clinic is a unique facility that specializes in providing comprehensive care for koi fish. Our team of experienced professionals offers a wide range of services, including routine check-ups, disease diagnosis and treatment, pond maintenance, and water quality testing.",
//                "Koi Care Clinic is a unique facility that specializes in providing comprehensive care for koi fish. Our team of experienced professionals offers a wide range of services, including routine check-ups, disease diagnosis and treatment, pond maintenance, and water quality testing.",
//                ServiceMeetingMethodEnum.ONLINE,
//                200.00f,
//                0.0f,
//                LocalTime.of(45 / 60,  45 % 60),
//                "Long Thanh My Ward, Thu Duc, 71216, Vietnam",
//                false
//        ));
//
//        servicesRepository.save(new ServiceEntity(
//                "Pond Quality",
//                ServiceTypeEnum.POND,
//                "Koi Care Clinic is a unique facility that specializes in providing comprehensive care for koi fish. Our team of experienced professionals offers a wide range of services, including routine check-ups, disease diagnosis and treatment, pond maintenance, and water quality testing.",
//                "Koi Care Clinic is a unique facility that specializes in providing comprehensive care for koi fish. Our team of experienced professionals offers a wide range of services, including routine check-ups, disease diagnosis and treatment, pond maintenance, and water quality testing.",
//                ServiceMeetingMethodEnum.OFFLINE_HOME,
//                500.00f,
//                1.0f,
//                LocalTime.of(120 / 60,  120 % 60),
//                "Long Thanh My Ward, Thu Duc, 71216, Vietnam",
//                false
//        ));
//
//        servicesRepository.save(new ServiceEntity(
//                "Koi Treatment at home",
//                ServiceTypeEnum.KOI,
//                "Koi Care Clinic is a unique facility that specializes in providing comprehensive care for koi fish. Our team of experienced professionals offers a wide range of services, including routine check-ups, disease diagnosis and treatment, pond maintenance, and water quality testing.",
//                "Koi Care Clinic is a unique facility that specializes in providing comprehensive care for koi fish. Our team of experienced professionals offers a wide range of services, including routine check-ups, disease diagnosis and treatment, pond maintenance, and water quality testing.",
//                ServiceMeetingMethodEnum.OFFLINE_HOME,
//                600.00f,
//                1.0f,
//                LocalTime.of(120 / 60,  120 % 60),
//                "Long Thanh My Ward, Thu Duc, 71216, Vietnam",
//                false
//        ));
//
//        servicesRepository.save(new ServiceEntity(
//                "Koi Treatment at center",
//                ServiceTypeEnum.KOI,
//                "Koi Care Clinic is a unique facility that specializes in providing comprehensive care for koi fish. Our team of experienced professionals offers a wide range of services, including routine check-ups, disease diagnosis and treatment, pond maintenance, and water quality testing.",
//                "Koi Care Clinic is a unique facility that specializes in providing comprehensive care for koi fish. Our team of experienced professionals offers a wide range of services, including routine check-ups, disease diagnosis and treatment, pond maintenance, and water quality testing.",
//                ServiceMeetingMethodEnum.OFFLINE_CENTER,
//                600.00f,
//                0.0f,
//                LocalTime.of(120 / 60,  120 % 60),
//                "Long Thanh My Ward, Thu Duc, 71216, Vietnam",
//                false
//        ));
    }
}
