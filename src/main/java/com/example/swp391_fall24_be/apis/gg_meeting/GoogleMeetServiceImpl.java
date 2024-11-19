package com.example.swp391_fall24_be.apis.gg_meeting;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GoogleMeetServiceImpl implements GoogleMeetService {
    private static final String APPLICATION_NAME = "Meet Generator Client";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String REDIRECT_URI = "http://localhost:8089/meetings/oauth2callback";
    private static final String CREDENTIALS_FILE_PATH = "credentials.json";

    private final MeetingRepository meetingRepository;

    public String getAuthorizationUrl() throws IOException, GeneralSecurityException {
        NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        // Dùng mã client và secret của bạn
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(getClass().getResourceAsStream("/credentials.json")));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setAccessType("offline")
                .build();

        // Tạo URL xác thực cho người dùng
        return flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
    }

    public String getAccessToken(String authCode) throws IOException, GeneralSecurityException {
        NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
                new InputStreamReader(getClass().getResourceAsStream("/credentials.json")));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setAccessType("offline")
                .build();

        // Đảm bảo redirect_uri trong yêu cầu này phải khớp với URI đã đăng ký trong Google Cloud Console
        GoogleTokenResponse response = flow.newTokenRequest(authCode)
                .setRedirectUri("http://localhost:8089/meetings/oauth2callback") // Phải khớp với Google Cloud Console
                .execute();

        return response.getAccessToken();  // Trả về access token
    }

    @Override
    public String createGoogleMeet(String accessToken) throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Credential credential = new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(accessToken);

        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();

        Event event = new Event()
                .setSummary("Meeting Title")
                .setDescription("Meeting Description");

        EventDateTime start = new EventDateTime()
                .setDateTime(new DateTime("2024-11-13T10:00:00+07:00"))
                .setTimeZone("Asia/Ho_Chi_Minh");
        event.setStart(start);

        EventDateTime end = new EventDateTime()
                .setDateTime(new DateTime("2024-11-16T11:00:00+07:00"))
                .setTimeZone("Asia/Ho_Chi_Minh");
        event.setEnd(end);

        // Cấu hình cho Google Meet
        ConferenceData conferenceData = new ConferenceData()
                .setCreateRequest(new CreateConferenceRequest()
                        .setRequestId(UUID.randomUUID().toString())
                        .setConferenceSolutionKey(new ConferenceSolutionKey().setType("hangoutsMeet")));

        // Thiết lập dữ liệu hội nghị vào sự kiện
        event.setConferenceData(conferenceData);

        // Tạo cuộc họp Google Meet
        event = service.events().insert("primary", event).setConferenceDataVersion(1).execute();
        String meetLink = event.getHangoutLink();

        // Kiểm tra kết quả và in ra
        System.out.println("Google Meet link: " + meetLink);

        return meetLink;
    }


}
