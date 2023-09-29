package io.voldsman.topicify.usersprofile.service.impl;

import io.voldsman.topicify.common.constants.Defaults;
import io.voldsman.topicify.common.exception.NotFoundException;
import io.voldsman.topicify.usersprofile.module.UserProfile;
import io.voldsman.topicify.usersprofile.payload.UpdateBioRequest;
import io.voldsman.topicify.usersprofile.payload.UpdateImageRequest;
import io.voldsman.topicify.usersprofile.repository.UserProfileRepository;
import io.voldsman.topicify.usersprofile.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public void updateProfileBio(final UUID userId, final UpdateBioRequest updateBioRequest) {
        UserProfile userProfile = findByUserId(userId);
        userProfile.setBio(updateBioRequest.getBio());
        userProfile.setUpdatedAt(LocalDateTime.now());
        userProfileRepository.save(userProfile);
    }

    @Override
    @Transactional
    public void updateProfileImage(UUID userId, UpdateImageRequest updateImageRequest) {
        UserProfile userProfile = findByUserId(userId);

        final String image = updateImageRequest.getImage();
        UpdateImageRequest.ImageType imageType = updateImageRequest.getType();
        if (imageType.equals(UpdateImageRequest.ImageType.AVATAR)) {
            userProfile.setAvatarImage(image);
        } else {
            userProfile.setCoverImage(image);
        }
        userProfile.setUpdatedAt(LocalDateTime.now());
        userProfileRepository.save(userProfile);
    }

    private UserProfile findByUserId(final UUID userId) {
        return userProfileRepository.findByUserId(userId)
                .orElseThrow(
                        () -> new NotFoundException("User profile not found by provided id")
                );
    }
}
