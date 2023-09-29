package io.voldsman.topicify.auth.service.impl;

import io.voldsman.topicify.accesstoken.service.AccessTokenService;
import io.voldsman.topicify.auth.payload.AuthRequest;
import io.voldsman.topicify.auth.payload.AuthResponse;
import io.voldsman.topicify.auth.service.AuthService;
import io.voldsman.topicify.common.exception.ForbiddenException;
import io.voldsman.topicify.common.exception.NotFoundException;
import io.voldsman.topicify.common.exception.UnauthorizedException;
import io.voldsman.topicify.common.utils.PasswordUtils;
import io.voldsman.topicify.refreshtoken.payload.GenerateRefreshTokenDto;
import io.voldsman.topicify.refreshtoken.service.RefreshTokenService;
import io.voldsman.topicify.users.payload.UserDto;
import io.voldsman.topicify.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final AccessTokenService accessTokenService;
    private final RefreshTokenService refreshTokenService;

    @Override
    public AuthResponse auth(final String deviceFingerprint, final AuthRequest authRequest) {
        UserDto userDto = userService.getUserByUsername(authRequest.getUsername());
        if (userDto.isDeleted()) {
            throw new NotFoundException("User profile was deleted");
        }

        if (userDto.isBlocked()) {
            throw new ForbiddenException("User profile is blocked, unable to login");
        }

        final var persistedPassword = userDto.getPassword();
        boolean isPasswordsMatch = PasswordUtils.checkPasswords(authRequest.getPassword(), persistedPassword);
        if (!isPasswordsMatch) {
            throw new UnauthorizedException("Bad credentials");
        }

        final var userId = userDto.getUserId();

        // Clear access and refresh tokens
        accessTokenService.deleteAllByUserId(userId);
        refreshTokenService.deleteAllByUserId(userId);

        // Generate access token
        final String accessTokenString = accessTokenService.generateAccessToken(userId);

        // Generate refresh token
        GenerateRefreshTokenDto generateRefreshTokenDto = new GenerateRefreshTokenDto();
        generateRefreshTokenDto.setUserId(userId);
        generateRefreshTokenDto.setDeviceFingerprint(deviceFingerprint);
        generateRefreshTokenDto.setRememberMe(authRequest.getRememberMe());
        final String refreshTokenString = refreshTokenService.generateRefreshToken(generateRefreshTokenDto);
        return new AuthResponse(userId, accessTokenString, refreshTokenString);
    }
}
