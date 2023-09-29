package io.voldsman.topicify.refreshtoken.payload;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RefreshTokenDto {

    private UUID userId;
    private String deviceFingerprint;
    private LocalDateTime expiresAt;
}
