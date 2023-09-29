package io.voldsman.topicify.users.profile.service;

import java.time.LocalDateTime;
import java.util.UUID;

public interface UserProfileService {

    void createDefaultProfile(final UUID userId, final String username, final LocalDateTime time);
}
