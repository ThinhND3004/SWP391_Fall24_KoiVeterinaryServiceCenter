package com.example.swp391_fall24_be.apis.auth;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.accounts.AccountRoleEnum;
import com.example.swp391_fall24_be.apis.accounts.AccountsRepository;
import com.example.swp391_fall24_be.apis.accounts.dtos.AccountDto;
import com.example.swp391_fall24_be.apis.auth.dto.AccountLoginDto;
import com.example.swp391_fall24_be.apis.images.ImageEntity;
import com.example.swp391_fall24_be.apis.images.ImagesRepository;
import com.example.swp391_fall24_be.core.ErrorEnum;
import com.example.swp391_fall24_be.core.ErrorReport;
import com.example.swp391_fall24_be.core.ProjectException;
import com.example.swp391_fall24_be.apis.auth.dto.AccountLoginResponseDto;
import com.example.swp391_fall24_be.core.ResponseDto;
import com.example.swp391_fall24_be.security.JwtProvider;
import com.example.swp391_fall24_be.utils.CryptoUtils;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.example.swp391_fall24_be.apis.accounts.AccountRoleEnum.CUSTOMER;

@Service
public class AuthenticationService {

    @Autowired
    private AccountsRepository repository;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private CryptoUtils cryptoService;

    @Autowired
    private ImagesRepository imagesRepository;

//    public AuthenticationService(AccountsRepository repository, JwtProvider jwtProvider, CryptoUtils cryptoService) {
//        this.repository = repository;
//        this.jwtProvider = jwtProvider;
//        this.cryptoService = cryptoService;
//    }

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
        dto.setDisable(account.getIsDisable());

        dto.setCreateAt(account.getCreateAt());
        return dto;
    }

    @Value("${google.clientId}")
    private String googleClientId;

//    public AccountLoginResponseDto loginWithGoogle(String credential) throws ProjectException {
//        var verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
//                .setAudience(Collections.singletonList(googleClientId))
//                .build();
//        try {
//            var idToken = verifier.verify(credential);
//            if (idToken != null) {
//                var payload = idToken.getPayload();
//                var email = payload.getEmail();
//                var account = repository.findByEmail(email);
//                var avatar = payload.getOrDefault("picture", "").toString();
//                if (account.isEmpty()) {
//                    var user = new AccountEntity();
//                    user.setEmail(email);
//                    var image = new ImageEntity();
//                    image.setLocalPath(avatar);
//                    user.setAvatar(image);
//                    var newAccount = repository.save(user);
//                    return new AccountLoginResponseDto(jwtProvider.signToken(newAccount));
//                }
//                return new AccountLoginResponseDto(jwtProvider.signToken(account.get()));
//                }
//        } catch (Exception e) {
//            throw new ProjectException(new ErrorReport("loginWithGoogle", ErrorEnum.ValidationError, "Invalid google credential"));
//        }
//        return null;
//    }

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

    public AccountLoginResponseDto loginWithGoogle(String credential) {
        AccountEntity account = null;
        try {
            String googleApiUrl = "https://www.googleapis.com/oauth2/v3/userinfo?access_token=" + credential;
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map> response = restTemplate.getForEntity(googleApiUrl, Map.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                Map<String, Object> userInfo = response.getBody();

                List<AccountEntity> accountEntities = repository.findAll();
                boolean isExistingAccount = false;

                for (AccountEntity existingAccount : accountEntities) {
                    if (existingAccount.getEmail() != null && existingAccount.getEmail().equalsIgnoreCase((String) userInfo.get("email"))) {
                        account = existingAccount;
                        isExistingAccount = true;
                        break;
                    }
                }

                if (!isExistingAccount) {
                    account = new AccountEntity();
                    account.setEmail((String) userInfo.get("email"));

                    System.out.println("KIEM TRA EMAIL: " + account.getEmail());
                    System.out.println("KIEM TRA ID: " + account.getId());
                    account.setFirstName((String) userInfo.get("given_name"));
                    account.setLastName((String) userInfo.get("family_name"));
                    account.setRole(AccountRoleEnum.CUSTOMER);
                    account.setIsDisable(false);
                    account.setPhone(generateRandomPhoneNumber());
                    account.setUpdateAt(LocalDateTime.now());
                    account.setCreateAt(LocalDateTime.now());

                    account = repository.save(account);
                }

                // Generate and return the token
                return new AccountLoginResponseDto(jwtProvider.signToken(account));

            } else {
                throw new RuntimeException("Invalid Google Credential");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to validate Google token: " + e.getMessage());
        }
    }

    public String generateRandomPhoneNumber() {
        Random random = new Random();
        StringBuilder phoneNumber = new StringBuilder("0");

        for (int i = 0; i < 9; i++) {
            int digit = random.nextInt(10);
            phoneNumber.append(digit);
        }

        return phoneNumber.toString();
    }

}
