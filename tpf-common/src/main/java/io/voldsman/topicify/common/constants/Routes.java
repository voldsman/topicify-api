package io.voldsman.topicify.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Routes {

    public static final String AUTH_ROUTE = "/api/auth";
    public static final String REFRESH_ROUTE = "/api/refresh";
    public static final String CREATE_USER_ROUTE = "/api/users/create";
}
