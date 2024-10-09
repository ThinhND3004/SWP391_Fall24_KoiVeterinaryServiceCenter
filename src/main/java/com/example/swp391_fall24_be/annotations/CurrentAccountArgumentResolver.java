package com.example.swp391_fall24_be.annotations;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class CurrentAccountArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return  parameter.getParameterAnnotation(CurrentAccount.class) != null
                && AccountEntity.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public AccountEntity resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth != null && auth.isAuthenticated()){
            return (AccountEntity) auth.getPrincipal();
        }

        throw new RuntimeException("User is not authenticated");
    }
}
