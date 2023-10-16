package io.voldsman.topicify.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Defaults {
    public static final String DEFAULT_AVATAR_IMAGE = "default_avatar.png";
    public static final String DEFAULT_COVER_IMAGE = "default_cover.png";

    public static final String[] logMaskedFields = {
            "password",
            "passwordConfirmation",
            "email"
    };

    public static final int ACCESS_TOKEN_LENGTH = 60;
    public static final int REFRESH_TOKEN_LENGTH = 90;
    public static final int DEFAULT_REFRESH_TOKEN_EXP_DAYS = 1;
    public static final int REMEMBER_REFRESH_TOKEN_EXP_DAYS = 7;
    public static final int ACCESS_TOKEN_EXP = 30 * 60; // 30min
    public static final String REFRESH_TOKEN_HEADER = "x-refresh";
    public static final String DEVICE_FINGERPRINT_HEADER = "x-device-fp";
    public static final String AUTHORIZATION_HEADER = "authorization";
    public static final String REQUEST_ATTR_USERID_PARAM = "userId";

    public static final String DEFAULT_IMAGES_FS_UPLOAD_FOLDER = "img_uploads";
}
