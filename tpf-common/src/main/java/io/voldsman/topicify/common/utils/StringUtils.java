package io.voldsman.topicify.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtils {

    public static boolean checkStringsEqual(final String str1, final String str2) {
        return str1.equals(str2);
    }

    public static String generateRandomString(final int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }

        return RandomStringUtils.randomAlphanumeric(length);
    }
}
