package io.voldsman.topicify.accesstoken.service;

import io.voldsman.topicify.accesstoken.payload.AccessTokenDto;

import java.util.UUID;

public interface AccessTokenService {

    String generateAccessToken(final UUID userId);

    AccessTokenDto getAccessTokenDetails(final String accessToken);

    void deleteAllByUserId(final UUID userId);
}
