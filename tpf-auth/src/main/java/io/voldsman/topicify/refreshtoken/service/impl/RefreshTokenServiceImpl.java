package io.voldsman.topicify.refreshtoken.service.impl;

import io.voldsman.topicify.common.constants.Defaults;
import io.voldsman.topicify.common.exception.NotFoundException;
import io.voldsman.topicify.common.utils.StringUtils;
import io.voldsman.topicify.refreshtoken.model.RefreshToken;
import io.voldsman.topicify.refreshtoken.payload.GenerateRefreshTokenDto;
import io.voldsman.topicify.refreshtoken.payload.RefreshTokenDto;
import io.voldsman.topicify.refreshtoken.repository.RefreshTokenRepository;
import io.voldsman.topicify.refreshtoken.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public String generateRefreshToken(final GenerateRefreshTokenDto generateRefreshTokenDto) {
        final String refreshTokenString = StringUtils.generateRandomString(Defaults.REFRESH_TOKEN_LENGTH);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiresAt;
        if (generateRefreshTokenDto.isRememberMe()) {
            expiresAt = now.plusDays(Defaults.REMEMBER_REFRESH_TOKEN_EXP_DAYS);
        } else {
            expiresAt = now.plusDays(Defaults.DEFAULT_REFRESH_TOKEN_EXP_DAYS);
        }

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUserId(generateRefreshTokenDto.getUserId());
        refreshToken.setRefreshToken(refreshTokenString);
        refreshToken.setDeviceFingerprint(generateRefreshTokenDto.getDeviceFingerprint());
        refreshToken.setCreatedAt(now);
        refreshToken.setExpiresAt(expiresAt);
        RefreshToken persistedRefreshToken = refreshTokenRepository.save(refreshToken);
        return persistedRefreshToken.getRefreshToken();
    }

    @Override
    public RefreshTokenDto getRefreshToken(final String refreshTokenString) {
        Optional<RefreshToken> refreshTokenOptional = refreshTokenRepository.findByRefreshToken(refreshTokenString);
        if (refreshTokenOptional.isEmpty()) {
            throw new NotFoundException("Refresh token not found");
        }
        RefreshToken refreshToken = refreshTokenOptional.get();
        return mapToRefreshTokenDto(refreshToken);
    }

    @Override
    public void deleteAllByUserId(final UUID userId) {
        List<RefreshToken> refreshTokens = refreshTokenRepository.findByUserId(userId);
        if (!refreshTokens.isEmpty()) {
            refreshTokenRepository.deleteAll(refreshTokens);
        }
    }

    private RefreshTokenDto mapToRefreshTokenDto(RefreshToken refreshToken) {
        RefreshTokenDto refreshTokenDto = new RefreshTokenDto();
        refreshTokenDto.setUserId(refreshToken.getUserId());
        refreshTokenDto.setDeviceFingerprint(refreshToken.getDeviceFingerprint());
        refreshTokenDto.setExpiresAt(refreshToken.getExpiresAt());
        return refreshTokenDto;
    }
}
