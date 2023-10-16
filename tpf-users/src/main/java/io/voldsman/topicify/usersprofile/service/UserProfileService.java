package io.voldsman.topicify.usersprofile.service;

import io.voldsman.topicify.usersprofile.payload.ProfileResponse;
import io.voldsman.topicify.usersprofile.payload.UpdateBioRequest;
import io.voldsman.topicify.usersprofile.payload.UpdateImageRequest;
import io.voldsman.topicify.usersprofile.payload.UpdateLinksRequest;

public interface UserProfileService {

    void createDefaultProfile(final String userId);

    void updateProfileBio(final String userId, final UpdateBioRequest updateBioRequest);

    void updateProfileImage(final String userId, final UpdateImageRequest updateImageRequest);

    void updateProfileLinks(final String userId, final UpdateLinksRequest updateLinksRequest);

    ProfileResponse getMyProfile(final String userId);

    ProfileResponse previewUserProfile(final String tokenUserId, final String profileUserId);
}
