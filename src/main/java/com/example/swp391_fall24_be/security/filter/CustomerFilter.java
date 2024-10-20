package com.example.swp391_fall24_be.security.filter;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.accounts.AccountRoleEnum;
import com.example.swp391_fall24_be.security.JwtProvider;
import com.example.swp391_fall24_be.utils.Utils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class CustomerFilter extends OncePerRequestFilter {
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String headerAuthor = request.getHeader("Authorization");

        if (headerAuthor != null && !headerAuthor.trim().isEmpty()) {
            String token = headerAuthor.substring(7);
            AccountEntity data = jwtProvider.verifyToken(token);

            if(data != null)
            {
//                AccountRoleEnum role = gson.fromJson(String.valueOf(data), AccountRoleEnum.class);
                AccountRoleEnum roleEnum = data.getRole();

                Logger.getAnonymousLogger().info("CHECK ROLE IN CUSTOMFILTER: " + roleEnum.name());

                List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleEnum.name());
                authorityList.add(simpleGrantedAuthority);

                UsernamePasswordAuthenticationToken authen = new UsernamePasswordAuthenticationToken(data, null, authorityList);

                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(authen);
            }
        }

        filterChain.doFilter(request, response);
    }
}
