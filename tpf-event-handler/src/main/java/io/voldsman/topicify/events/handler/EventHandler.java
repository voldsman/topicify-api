package io.voldsman.topicify.events.handler;

import io.voldsman.topicify.common.event.CreateProfileEvent;
import io.voldsman.topicify.common.event.payload.CreateProfile;
import io.voldsman.topicify.users.profile.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EventHandler {

    private final UserProfileService userProfileService;

    @EventListener
    public void handleCreateProfileEvent(CreateProfileEvent createProfileEvent) {
        CreateProfile createProfile = createProfileEvent.getCreateProfile();
        userProfileService.createDefaultProfile(createProfile.userId(), createProfile.username(), createProfile.time());
    }
}
