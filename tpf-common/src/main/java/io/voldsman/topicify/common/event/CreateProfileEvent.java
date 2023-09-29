package io.voldsman.topicify.events.publisher.event;

import org.springframework.context.ApplicationEvent;

import java.util.UUID;

public class CreateProfileEvent extends ApplicationEvent {

    private UUID userId;
    private String username;

    public CreateProfileEvent(Object source) {
        super(source);
    }
}
