package io.voldsman.topicify.events.publisher;

import io.voldsman.topicify.common.event.CreateProfileEvent;
import io.voldsman.topicify.common.event.payload.CreateProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public void publishCreateProfileEvent(final CreateProfile createProfile) {
        CreateProfileEvent createProfileEvent = new CreateProfileEvent(this, createProfile);
        eventPublisher.publishEvent(createProfileEvent);
    }
}
