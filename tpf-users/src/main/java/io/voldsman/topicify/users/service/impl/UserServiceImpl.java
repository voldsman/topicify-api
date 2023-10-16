package io.voldsman.topicify.users.service.impl;

import io.voldsman.topicify.common.exception.NotFoundException;
import io.voldsman.topicify.users.model.User;
import io.voldsman.topicify.users.payload.UserDto;
import io.voldsman.topicify.users.repository.UserRepository;
import io.voldsman.topicify.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto getUserByUsername(final String username) {
        Optional<User> userOptional = userRepository.findByUsernameIgnoreCase(username);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("User not found by username");
        }

        User user = userOptional.get();
        return mapToUserDto(user);
    }

    @Override
    public UserDto getUserByUserId(final String userId) {
        Optional<User> userOptional = userRepository.findByUserIdAndIsDeletedFalseAndIsBlockedFalse(userId);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("User not found by userId");
        }

        User user = userOptional.get();
        return mapToUserDto(user);
    }

    private UserDto mapToUserDto(final User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setBlocked(user.isBlocked());
        userDto.setDeleted(user.isDeleted());
        return userDto;
    }
}
