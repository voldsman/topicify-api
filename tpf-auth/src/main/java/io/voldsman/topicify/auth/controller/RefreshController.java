package io.voldsman.topicify.auth.controller;

import io.voldsman.topicify.auth.payload.AuthResponse;
import io.voldsman.topicify.auth.service.RefreshService;
import io.voldsman.topicify.common.constants.Defaults;
import io.voldsman.topicify.common.constants.Routes;
import io.voldsman.topicify.common.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.REFRESH_ROUTE)
@RequiredArgsConstructor
public class RefreshController {

    private final RefreshService refreshService;

    @PostMapping
    public ResponseEntity<ApiResponse<AuthResponse>> refresh(
            @RequestHeader(Defaults.DEVICE_FINGERPRINT_HEADER) String deviceFP,
            @RequestHeader(Defaults.REFRESH_TOKEN_HEADER) String refreshToken) {
        AuthResponse authResponse = refreshService.refresh(deviceFP, refreshToken);

        var response = new ApiResponse<>("Access token refreshed", true, authResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
