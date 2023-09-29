package io.voldsman.topicify.usersprofile.service;

import io.voldsman.topicify.usersprofile.payload.UpdateBioRequest;
import io.voldsman.topicify.usersprofile.payload.UpdateImageRequest;

import java.util.UUID;

public interface UserProfileService {

    void createDefaultProfile(final UUID userId);

    void updateProfileBio(final UUID userId, final UpdateBioRequest updateBioRequest);

    void updateProfileImage(final UUID userId, final UpdateImageRequest updateImageRequest);
}
