package io.voldsman.topicify.users.controller;

import io.voldsman.topicify.common.constants.Routes;
import io.voldsman.topicify.common.payload.ApiResponse;
import io.voldsman.topicify.users.payload.CreateUserRequest;
import io.voldsman.topicify.users.payload.CreateUserResponse;
import io.voldsman.topicify.users.service.CreateUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.CREATE_USER_ROUTE)
@RequiredArgsConstructor
public class CreateUserController {

    private final CreateUserService createUserService;

    @PostMapping
    public ResponseEntity<ApiResponse<CreateUserResponse>> create(@Valid @RequestBody CreateUserRequest createUserRequest) {
        CreateUserResponse createUserResponse = createUserService.create(createUserRequest);

        ApiResponse<CreateUserResponse> apiResponse = new ApiResponse<>("User successfully created", true, createUserResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
}
