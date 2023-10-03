package io.voldsman.topicify.usersprofile.service.impl;

import io.voldsman.topicify.common.constants.Defaults;
import io.voldsman.topicify.common.exception.BadRequestException;
import io.voldsman.topicify.common.exception.NotFoundException;
import io.voldsman.topicify.users.payload.UserDto;
import io.voldsman.topicify.users.service.UserService;
import io.voldsman.topicify.usersprofile.module.UserProfile;
import io.voldsman.topicify.usersprofile.module.UserProfileLink;
import io.voldsman.topicify.usersprofile.payload.ProfileResponse;
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

    private final UserService userService;

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

    @Override
    public ProfileResponse getMyProfile(final UUID userId) {
        UserDto userDto = userService.getUserByUserId(userId);
        UserProfile userProfile = findByUserId(userId);

        ProfileResponse profileResponse = buildProfileResponse(userDto, userProfile);
        profileResponse.setResourceOwner(true);
        return profileResponse;
    }

    @Override
    public ProfileResponse previewUserProfile(UUID tokenUserId, UUID profileUserId) {
        if (tokenUserId.equals(profileUserId)) {
            throw new BadRequestException("Please user MyProfile endpoint");
        }
        UserDto userDto = userService.getUserByUserId(profileUserId);
        UserProfile userProfile = findByUserId(profileUserId);

        ProfileResponse profileResponse = buildProfileResponse(userDto, userProfile);
        profileResponse.setResourceOwner(false);
        return profileResponse;
    }

    private UserProfile findByUserId(final UUID userId) {
        return userProfileRepository.findByUserId(userId)
                .orElseThrow(
                        () -> new NotFoundException("User profile not found by provided id")
                );
    }

    private ProfileResponse buildProfileResponse(final UserDto userDto, final UserProfile userProfile) {
        ProfileResponse profileResponse = new ProfileResponse();
        profileResponse.setUserId(userDto.getUserId().toString());
        profileResponse.setUsername(userDto.getUsername());
        profileResponse.setAvatarImage(userProfile.getAvatarImage());
        profileResponse.setCoverImage(userProfile.getCoverImage());
        profileResponse.setBio(userProfile.getBio());
        profileResponse.setLinks(userProfile.getLinks()
                .stream()
                .map(l -> {
                    ProfileResponse.Link link = new ProfileResponse.Link();
                    link.setName(l.getName());
                    link.setUrl(l.getUrl());
                    return link;
                }).toList());
        return profileResponse;
    }
}
