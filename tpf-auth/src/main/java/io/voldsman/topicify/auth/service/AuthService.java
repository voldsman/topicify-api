package io.voldsman.topicify.auth.service;

import io.voldsman.topicify.auth.payload.AuthRequest;
import io.voldsman.topicify.auth.payload.AuthResponse;

public interface AuthService {

    AuthResponse auth(final String deviceFingerprint, final AuthRequest authRequest);
}
