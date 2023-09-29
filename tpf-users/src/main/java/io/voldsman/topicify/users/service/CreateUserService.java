package io.voldsman.topicify.users.service;

import io.voldsman.topicify.users.payload.CreateUserRequest;
import io.voldsman.topicify.users.payload.CreateUserResponse;

public interface CreateUserService {

    CreateUserResponse create(final CreateUserRequest createUserRequest);
}
