package io.voldsman.topicify.auth.service.impl;

import io.voldsman.topicify.accesstoken.service.AccessTokenService;
import io.voldsman.topicify.auth.payload.AuthResponse;
import io.voldsman.topicify.auth.service.RefreshService;
import io.voldsman.topicify.common.exception.ForbiddenException;
import io.voldsman.topicify.refreshtoken.payload.RefreshTokenDto;
import io.voldsman.topicify.refreshtoken.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshServiceImpl implements RefreshService {

    private final RefreshTokenService refreshTokenService;
    private final AccessTokenService accessTokenService;

    @Override
    public AuthResponse refresh(String deviceFingerprint, String refreshTokenString) {
        RefreshTokenDto refreshToken = refreshTokenService.getRefreshToken(refreshTokenString);
        if (!refreshToken.getDeviceFingerprint().equals(deviceFingerprint)) {
            throw new ForbiddenException("Refresh token not valid");
        }

        LocalDateTime now = LocalDateTime.now();
        if (refreshToken.getExpiresAt().isBefore(now)) {
            throw new ForbiddenException("Refresh token expired");
        }

        final UUID userId = refreshToken.getUserId();

        // Clear access tokens
        accessTokenService.deleteAllByUserId(userId);

        final String accessTokenString = accessTokenService.generateAccessToken(userId);
        return new AuthResponse(userId, accessTokenString, refreshTokenString);
    }
}
