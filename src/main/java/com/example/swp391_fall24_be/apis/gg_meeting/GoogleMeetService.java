package com.example.swp391_fall24_be.apis.gg_meeting;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface GoogleMeetService {
    String createGoogleMeet(String accessToken) throws IOException, GeneralSecurityException;
}
