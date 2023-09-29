package io.voldsman.topicify.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PasswordUtils {

    public static String generatePasswordHash(final String plainText) {
        return BCrypt.hashpw(plainText, BCrypt.gensalt());
    }

    public static boolean checkPasswords(final String plainPassword, final String hashPassword) {
        return BCrypt.checkpw(plainPassword, hashPassword);
    }
}
