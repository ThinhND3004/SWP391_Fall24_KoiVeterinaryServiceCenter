package com.example.swp391_fall24_be.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.accounts.AccountsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Component
public class JwtProvider {
    @Value("${secrets.JWT_SECRET}")
    private String jwtSecret;

    @Value("${secrets.JWT_EXPIRE}")
    private int jwtExpireTime;

    @Value("${secrets.JWT_TIMEZONE}")
    private String jwtTimezone;

    private final AccountsRepository userRepository;

    public JwtProvider(AccountsRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String signToken(AccountEntity account) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            ZonedDateTime now = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of(jwtTimezone));
            return JWT.create()
                    .withSubject(account.getId())
                    .withIssuedAt(now.toInstant())
                    .withExpiresAt(now.toInstant().plus(jwtExpireTime, ChronoUnit.SECONDS))
                    .sign(algorithm);
        } catch (Exception e) {
//            throw new ProjectException(new ErrorReport("signToken", ErrorType.ValidationError, "Cannot sign token"));
        }
        return null;
    }

    public AccountEntity verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            DecodedJWT payload = JWT.require(algorithm)
                    .build()
                    .verify(token);
            String accountId = payload.getSubject();
            Optional<AccountEntity> findAccountResult = userRepository.findById(accountId);
            return findAccountResult.orElse(null);
        } catch (Exception e) {
//            throw new ProjectException(new ErrorReport("verifyToken", ErrorType.ValidationError, "Invalid token"));
        }
        return null;
    }
}
