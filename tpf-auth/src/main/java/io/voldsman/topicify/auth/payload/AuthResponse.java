package io.voldsman.topicify.auth.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class AuthResponse {

    private String userId;
    private String accessToken;
    private String refreshToken;

    public AuthResponse(final UUID userId, final String accessToken, final String refreshToken) {
        this.userId = userId.toString();
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
