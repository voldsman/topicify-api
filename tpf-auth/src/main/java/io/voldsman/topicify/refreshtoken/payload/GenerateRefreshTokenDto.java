package io.voldsman.topicify.refreshtoken.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class GenerateRefreshTokenDto {

    private UUID userId;
    private String deviceFingerprint;
    private boolean isRememberMe;
}
