package io.voldsman.topicify.users.service.impl;

import io.voldsman.topicify.common.exception.AlreadyExistsException;
import io.voldsman.topicify.common.exception.BadRequestException;
import io.voldsman.topicify.common.utils.PasswordUtils;
import io.voldsman.topicify.common.utils.StringUtils;
import io.voldsman.topicify.users.model.User;
import io.voldsman.topicify.users.payload.CreateUserRequest;
import io.voldsman.topicify.users.payload.CreateUserResponse;
import io.voldsman.topicify.users.repository.UserRepository;
import io.voldsman.topicify.users.service.CreateUserService;
import io.voldsman.topicify.usersprofile.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateUserServiceImpl implements CreateUserService {

    private final UserRepository userRepository;

    private final UserProfileService userProfileService;

    @Override
    @Transactional
    public CreateUserResponse create(CreateUserRequest createUserRequest) {
        final var password = createUserRequest.getPassword();
        boolean isPasswordsMatch = StringUtils.checkStringsEqual(password, createUserRequest.getPasswordConfirmation());
        if (!isPasswordsMatch) {
            throw new BadRequestException("Passwords doesn't match");
        }

        final var username = createUserRequest.getUsername();
        boolean existsByUsername = userRepository.existsByUsernameIgnoreCase(username);
        if (existsByUsername) {
            throw new AlreadyExistsException("User already exists by username");
        }

        final var email = createUserRequest.getEmail();
        boolean existsByEmail = userRepository.existsByEmailIgnoreCase(email);
        if (existsByEmail) {
            throw new AlreadyExistsException("User already exists by email");
        }

        final var hashedPassword = PasswordUtils.generatePasswordHash(createUserRequest.getPassword());
        final var now = LocalDateTime.now();

        User user = new User();
        user.setUserId(UUID.randomUUID());
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(hashedPassword);
        user.setBlocked(false);
        user.setDeleted(false);
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        User persistedUser = userRepository.save(user);

        final var userId = persistedUser.getUserId();

        // Create default profile
        userProfileService.createDefaultProfile(userId, username, now);
        return new CreateUserResponse(userId);
    }
}
