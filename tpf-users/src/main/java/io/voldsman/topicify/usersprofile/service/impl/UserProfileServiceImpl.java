package io.voldsman.topicify.usersprofile.service.impl;

import io.voldsman.topicify.common.constants.Defaults;
import io.voldsman.topicify.common.exception.NotFoundException;
import io.voldsman.topicify.usersprofile.module.UserProfile;
import io.voldsman.topicify.usersprofile.module.UserProfileLink;
import io.voldsman.topicify.usersprofile.payload.UpdateBioRequest;
import io.voldsman.topicify.usersprofile.payload.UpdateImageRequest;
import io.voldsman.topicify.usersprofile.payload.UpdateLinksRequest;
import io.voldsman.topicify.usersprofile.repository.UserProfileRepository;
import io.voldsman.topicify.usersprofile.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Override
    public void createDefaultProfile(final UUID userId) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(userId);
        userProfile.setBio("");
        userProfile.setLinks(new ArrayList<>());
        userProfile.setAvatarImage(Defaults.DEFAULT_AVATAR_IMAGE);
        userProfile.setCoverImage(Defaults.DEFAULT_COVER_IMAGE);
        userProfile.setUpdatedAt(LocalDateTime.now());
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

    @Override
    @Transactional
    public void updateProfileLinks(UUID userId, UpdateLinksRequest updateLinksRequest) {
        UserProfile userProfile = findByUserId(userId);

        List<UserProfileLink> userProfileLinks = updateLinksRequest.getLinks().stream().map(l -> {
            UserProfileLink userProfileLink = new UserProfileLink();
            userProfileLink.setName(l.getName());
            userProfileLink.setUrl(l.getUrl());
            return userProfileLink;
        }).toList();

        userProfile.setLinks(userProfileLinks);
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
