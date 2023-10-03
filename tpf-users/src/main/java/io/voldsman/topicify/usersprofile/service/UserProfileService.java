package io.voldsman.topicify.usersprofile.service;

import io.voldsman.topicify.usersprofile.payload.ProfileResponse;
import io.voldsman.topicify.usersprofile.payload.UpdateBioRequest;
import io.voldsman.topicify.usersprofile.payload.UpdateImageRequest;
import io.voldsman.topicify.usersprofile.payload.UpdateLinksRequest;

import java.util.UUID;

public interface UserProfileService {

    void createDefaultProfile(final UUID userId);

    void updateProfileBio(final UUID userId, final UpdateBioRequest updateBioRequest);

    void updateProfileImage(final UUID userId, final UpdateImageRequest updateImageRequest);

    void updateProfileLinks(final UUID userId, final UpdateLinksRequest updateLinksRequest);

    ProfileResponse getMyProfile(final UUID userId);
    ProfileResponse previewUserProfile(final UUID tokenUserId, final UUID profileUserId);
}
