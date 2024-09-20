package com.example.swp391_fall24_be.apis.auth;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.accounts.AccountsRepository;
import com.example.swp391_fall24_be.apis.accounts.dtos.AccountDto;
import com.example.swp391_fall24_be.apis.auth.dto.AccountLoginDto;
import com.example.swp391_fall24_be.core.ErrorEnum;
import com.example.swp391_fall24_be.core.ErrorReport;
import com.example.swp391_fall24_be.core.ProjectException;
import com.example.swp391_fall24_be.apis.auth.dto.AccountLoginResponseDto;
import com.example.swp391_fall24_be.security.JwtProvider;
import com.example.swp391_fall24_be.utils.CryptoUtils;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthenticationService {
    private final AccountsRepository repository;
    private final JwtProvider jwtProvider;
    private final CryptoUtils cryptoService;

    public AuthenticationService(AccountsRepository repository, JwtProvider jwtProvider, CryptoUtils cryptoService) {
        this.repository = repository;
        this.jwtProvider = jwtProvider;
        this.cryptoService = cryptoService;
    }

    public AccountDto verifyAccessToken(String accessToken) throws ProjectException {
        try {
            var account = this.jwtProvider.verifyToken(accessToken.replace("Bearer ", ""));
            if (account == null) {
                throw new ProjectException(new ErrorReport("verifyAccessToken", ErrorEnum.ValidationError, "Invalid access token"));
            } else {
                return getAccountDto(account);
            }
        } catch (Exception e) {
            throw new ProjectException(new ErrorReport("verifyAccessToken", ErrorEnum.ValidationError, "Invalid access token"));
        }
    }

    private static AccountDto getAccountDto(AccountEntity account) {
        var dto = new AccountDto();
        dto.setEmail(account.getEmail());
        dto.setLastName(account.getLastName());
        dto.setFirstName(account.getFirstName());
        dto.setDob(account.getDob());
        dto.setAddress(account.getAddress());
        dto.setPhone(account.getPhone());
        dto.setRole(account.getRole());
        dto.setDisable(account.isDisable());

        dto.setCreateAt(account.getCreateAt());
        return dto;
    }

    @Value("${google.clientId}")
    private String googleClientId;

    public AccountLoginResponseDto loginWithGoogle(String credential) throws ProjectException {
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
                    var user = new AccountEntity();
                    user.setEmail(email);
                    var newAccount = repository.save(user);
                    return new AccountLoginResponseDto(jwtProvider.signToken(newAccount));
                }
                return new AccountLoginResponseDto(jwtProvider.signToken(account.get()));
                }
        } catch (Exception e) {
            throw new ProjectException(new ErrorReport("loginWithGoogle", ErrorEnum.ValidationError, "Invalid google credential"));
        }
        return null;
    }

    public AccountLoginResponseDto loginWithUsernameAndPassword(AccountLoginDto dto) throws ProjectException {
        var optionalUser = repository.findByEmail(dto.getEmail());
        if (optionalUser.isEmpty()) {
            throw new ProjectException(new ErrorReport("loginWithUsernameAndPassword", ErrorEnum.EntityNotFound, "Account not found"));
        }
        var account = optionalUser.get();
        if (!cryptoService.verify(dto.getPassword(), account.getPassword())) {
            throw new ProjectException(new ErrorReport("loginWithUsernameAndPassword", ErrorEnum.ValidationError, "Password is incorrect"));
        }
        return new AccountLoginResponseDto(jwtProvider.signToken(account));
    }
}
