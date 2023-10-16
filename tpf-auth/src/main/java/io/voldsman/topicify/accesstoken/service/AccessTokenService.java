package io.voldsman.topicify.accesstoken.service;

import io.voldsman.topicify.accesstoken.payload.AccessTokenDto;

public interface AccessTokenService {

    String generateAccessToken(final String userId);

    AccessTokenDto getAccessTokenDetails(final String accessToken);

    void deleteAllByUserId(final String userId);
}
