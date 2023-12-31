package io.voldsman.topicify.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Validations {

    public final static int MIN_USERNAME_LENGTH = 4;
    public final static int MAX_USERNAME_LENGTH = 20;

    public final static String USERNAME_PATTERN = "^(?=.*[a-zA-Z])[a-zA-Z0-9]*[_\\.]?[a-zA-Z0-9]*$";

    public final static int MIN_EMAIL_LENGTH = 10;
    public final static int MAX_EMAIL_LENGTH = 120;

    public final static String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";

    public final static int MIN_PASSWORD_LENGTH = 6;
    public final static int MAX_PASSWORD_LENGTH = 50;

    // profile
    public final static int MAX_PROFILE_BIO_SYMBOLS_LENGTH = 500;
    public final static int MAX_PROFILE_LINKS = 3;
    public final static int MAX_PROFILE_LINK_NAME_LENGTH = 30;
    public final static int MAX_PROFILE_LINK_URL_LENGTH = 200;
    public final static String PROFILE_URL_REGEX = "^https://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    public final static String IMAGE_VALIDATION_REGEX = "\\b[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}\\.(png|jpg|jpeg)\\b";

    public final static String UUID_VALIDATION_REGEX = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}";

    // topics
    public final static int MAX_ACTIVE_TOPICS = 10;
    public final static int MIN_TOPIC_NAME_LENGTH = 5;
    public final static int MAX_TOPIC_NAME_LENGTH = 90;
    public final static int MAX_TOPIC_DESCRIPTION_LENGTH = 150;

    // posts
    public final static int MAX_POST_BODY_LENGTH = 500;
}
