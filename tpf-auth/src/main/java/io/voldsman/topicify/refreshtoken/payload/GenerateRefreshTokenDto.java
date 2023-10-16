package io.voldsman.topicify.refreshtoken.payload;

import lombok.Data;

@Data
public class GenerateRefreshTokenDto {

    private String userId;
    private String deviceFingerprint;
    private boolean isRememberMe;
}
