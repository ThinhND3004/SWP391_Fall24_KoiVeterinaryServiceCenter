package com.example.swp391_fall24_be.service;

import com.example.swp391_fall24_be.core.ProjectException;
import com.example.swp391_fall24_be.dto.AccountCreateDto;
import com.example.swp391_fall24_be.dto.AccountLoginResponseDto;
import com.example.swp391_fall24_be.dto.AccountResponseDto;
import com.example.swp391_fall24_be.entities.UserEntity;
import com.example.swp391_fall24_be.repository.UserRepository;
import com.example.swp391_fall24_be.security.JwtProvider;
import com.example.swp391_fall24_be.utils.CryptoUtils;
import com.example.swp391_fall24_be.utils.Utils;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;


@Service
public class AuthService {

    private final UserRepository repository;
    private final JwtProvider jwtProvider;
    private final CryptoUtils cryptoService;

    public AuthService(UserRepository repository, JwtProvider jwtProvider, CryptoUtils cryptoService) {
        this.repository = repository;
        this.jwtProvider = jwtProvider;
        this.cryptoService = cryptoService;
    }

    public AccountResponseDto verifyAccessToken(String accessToken) throws ProjectException {
        try {
            var account = this.jwtProvider.verifyToken(accessToken.replace("Bearer ", ""));
            return Utils.accountResponse(account);
        } catch (Exception e) {
//            throw new ProjectException(new ErrorReport("verifyAccessToken", ErrorType.ValidationError, "Invalid access token"));
        }
        return null;
    }

    @Value("${google.clientId}")
    private String googleClientId;

    public AccountLoginResponseDto loginWithGoogle(String credential) {
        var verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList(googleClientId))
                .build();
        try {
            var idToken = verifier.verify(credential);
            if (idToken != null) {
                var payload = idToken.getPayload();
                var email = payload.getEmail();
                var account = repository.findByEmail(email);
                if (account.isEmpty()) {
                    var user = new UserEntity();
                    user.setEmail(email);
                    var newAccount = repository.save(user);
                    return new AccountLoginResponseDto(jwtProvider.signToken(newAccount));
                }
                return new AccountLoginResponseDto(jwtProvider.signToken(account.get()));
                }
        } catch (GeneralSecurityException | IOException e) {
//            throw new ProjectException(new ErrorReport("loginWithGoogle", ErrorType.ValidationError, "Invalid google credential"));
        }
        return null;
    }

    public AccountLoginResponseDto loginWithUsernameAndPassword(AccountCreateDto dto) throws ProjectException {
        var optionalUser = repository.findByEmail(dto.getEmail());
        if (optionalUser.isEmpty()) {
//            throw new ProjectException(new ErrorReport("loginWithUsernameAndPassword", ErrorType.EntityNotFound, "Account not found"));
        }
        var account = optionalUser.get();
        if (!cryptoService.verify(dto.getPassword(), account.getPassword())) {
//            throw new ProjectException(new ErrorReport("loginWithUsernameAndPassword", ErrorType.ValidationError, "Password is incorrect"));
        }
        return new AccountLoginResponseDto(jwtProvider.signToken(account));
    }
}
