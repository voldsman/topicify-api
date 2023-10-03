package io.voldsman.topicify.users.service;

import io.voldsman.topicify.users.payload.UserDto;

import java.util.UUID;

public interface UserService {

    UserDto getUserByUsername(final String username);

    UserDto getUserByUserId(final UUID userId);
}
