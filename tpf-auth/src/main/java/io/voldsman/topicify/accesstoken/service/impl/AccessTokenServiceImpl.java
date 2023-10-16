package io.voldsman.topicify.accesstoken.service.impl;

import io.voldsman.topicify.accesstoken.model.AccessToken;
import io.voldsman.topicify.accesstoken.payload.AccessTokenDto;
import io.voldsman.topicify.accesstoken.repository.AccessTokenRepository;
import io.voldsman.topicify.accesstoken.service.AccessTokenService;
import io.voldsman.topicify.common.constants.Defaults;
import io.voldsman.topicify.common.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccessTokenServiceImpl implements AccessTokenService {

    private final AccessTokenRepository accessTokenRepository;

    @Override
    public String generateAccessToken(final String userId) {
        final var accessTokenString = StringUtils.generateRandomString(Defaults.ACCESS_TOKEN_LENGTH);

        AccessToken accessToken = new AccessToken();
        accessToken.setUserId(userId);
        accessToken.setAccessToken(accessTokenString);
        accessToken.setCreatedAt(LocalDateTime.now());
        AccessToken persistedAccessToken = accessTokenRepository.save(accessToken);
        return persistedAccessToken.getAccessToken();
    }

    @Override
    public AccessTokenDto getAccessTokenDetails(final String accessTokenString) {
        Optional<AccessToken> accessTokenOptional = accessTokenRepository.findByAccessToken(accessTokenString);
        if (accessTokenOptional.isPresent()) {
            AccessToken accessToken = accessTokenOptional.get();

            AccessTokenDto accessTokenDto = new AccessTokenDto();
            accessTokenDto.setUserId(accessToken.getUserId());
            accessTokenDto.setCreatedAt(accessToken.getCreatedAt());
            return accessTokenDto;
        }
        return null;
    }

    @Override
    public void deleteAllByUserId(final String userId) {
        List<AccessToken> accessTokens = accessTokenRepository.findByUserId(userId);
        if (!accessTokens.isEmpty()) {
            accessTokenRepository.deleteAll(accessTokens);
        }
    }
}
