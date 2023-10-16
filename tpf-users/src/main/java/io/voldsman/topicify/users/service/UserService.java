package io.voldsman.topicify.users.service;

import io.voldsman.topicify.users.payload.UserDto;

public interface UserService {

    UserDto getUserByUsername(final String username);

    UserDto getUserByUserId(final String userId);
}
