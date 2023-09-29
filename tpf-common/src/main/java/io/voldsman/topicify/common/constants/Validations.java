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

}