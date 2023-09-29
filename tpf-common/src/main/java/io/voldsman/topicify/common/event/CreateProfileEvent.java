package io.voldsman.topicify.common.event;

import io.voldsman.topicify.common.event.payload.CreateProfile;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CreateProfileEvent extends ApplicationEvent {

    private final CreateProfile createProfile;

    public CreateProfileEvent(Object source, final CreateProfile createProfile) {
        super(source);
        this.createProfile = createProfile;
    }
}
