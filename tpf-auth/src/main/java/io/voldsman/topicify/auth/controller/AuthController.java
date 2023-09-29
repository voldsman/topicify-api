package io.voldsman.topicify.auth.controller;

import io.voldsman.topicify.auth.payload.AuthRequest;
import io.voldsman.topicify.auth.payload.AuthResponse;
import io.voldsman.topicify.auth.service.AuthService;
import io.voldsman.topicify.common.constants.Defaults;
import io.voldsman.topicify.common.constants.Routes;
import io.voldsman.topicify.common.payload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Routes.AUTH_ROUTE)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<ApiResponse<AuthResponse>> auth(
            @RequestHeader(Defaults.DEVICE_FINGERPRINT_HEADER) String deviceFP,
            @Valid @RequestBody AuthRequest authRequest) {
        AuthResponse authResponse = authService.auth(deviceFP, authRequest);

        var response = new ApiResponse<>("Access token generated", true, authResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
