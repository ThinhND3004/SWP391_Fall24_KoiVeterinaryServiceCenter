package com.example.swp391_fall24_be.utils;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtils {
    public static AccountEntity getCurrentAccount(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth != null && auth.isAuthenticated()){
            return (AccountEntity) auth.getPrincipal();
        }

        throw new RuntimeException("User is not authenticated");
    }
}
