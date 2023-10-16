package io.voldsman.topicify.refreshtoken.payload;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RefreshTokenDto {

    private String userId;
    private String deviceFingerprint;
    private LocalDateTime expiresAt;
}
