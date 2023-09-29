package io.voldsman.topicify.refreshtoken.service;

import io.voldsman.topicify.refreshtoken.payload.GenerateRefreshTokenDto;
import io.voldsman.topicify.refreshtoken.payload.RefreshTokenDto;

import java.util.UUID;

public interface RefreshTokenService {

    String generateRefreshToken(final GenerateRefreshTokenDto generateRefreshTokenDto);

    RefreshTokenDto getRefreshToken(final String refreshTokenString);

    void deleteAllByUserId(final UUID userId);
}
