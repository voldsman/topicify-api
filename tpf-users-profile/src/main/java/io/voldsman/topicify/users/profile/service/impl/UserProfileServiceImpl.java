package io.voldsman.topicify.users.profile.service.impl;

import io.voldsman.topicify.common.constants.Defaults;
import io.voldsman.topicify.users.profile.module.UserProfile;
import io.voldsman.topicify.users.profile.repository.UserProfileRepository;
import io.voldsman.topicify.users.profile.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Override
    public void createDefaultProfile(final UUID userId, final String username, final LocalDateTime time) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(userId);
        userProfile.setUsername(username);
        userProfile.setBio("");
        userProfile.setAvatarImage(Defaults.DEFAULT_AVATAR_IMAGE);
        userProfile.setCoverImage(Defaults.DEFAULT_COVER_IMAGE);
        userProfile.setUpdatedAt(time);
        userProfileRepository.save(userProfile);
    }
}
