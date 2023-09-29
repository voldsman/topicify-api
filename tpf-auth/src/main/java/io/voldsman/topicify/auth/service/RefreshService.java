package io.voldsman.topicify.auth.service;

import io.voldsman.topicify.auth.payload.AuthResponse;

public interface RefreshService {

    AuthResponse refresh(final String deviceFingerprint, final String refreshTokenString);
}
