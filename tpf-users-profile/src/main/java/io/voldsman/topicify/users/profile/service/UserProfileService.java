package io.voldsman.topicify.users.profile.service;

import io.voldsman.topicify.users.profile.payload.UpdateBioRequest;
import io.voldsman.topicify.users.profile.payload.UpdateImageRequest;

import java.time.LocalDateTime;
import java.util.UUID;

public interface UserProfileService {

    void createDefaultProfile(final UUID userId, final String username, final LocalDateTime time);

    void updateProfileBio(final UUID userId, final UpdateBioRequest updateBioRequest);

    void updateProfileImage(final UUID userId, final UpdateImageRequest updateImageRequest);
}
