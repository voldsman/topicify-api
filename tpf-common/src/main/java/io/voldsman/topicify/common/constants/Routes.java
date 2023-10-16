package io.voldsman.topicify.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Routes {

    public static final String AUTH_ROUTE = "/api/auth";
    public static final String REFRESH_ROUTE = "/api/refresh";
    public static final String CREATE_USER_ROUTE = "/api/users/create";

    public static final String CREATE_TOPIC_ROUTE = "/api/topics";
    public static final String CREATE_POST_ROUTE = "/api/posts";

    public static final List<String> PUBLIC_ROUTES = List.of(
            AUTH_ROUTE,
            REFRESH_ROUTE,
            CREATE_USER_ROUTE,

            // TODO: Temp added Swagger links, need to refactor it based on active profile
            "/docs/swagger",
            "/docs/api-docs",
            "/docs/api-docs/swagger-config",
            "/docs/swagger-ui/index.html",
            "/docs/swagger-ui/favicon-16x16.png",
            "/docs/swagger-ui/favicon-32x32.png",
            "/docs/swagger-ui/swagger-initializer.js",
            "/docs/swagger-ui/swagger-ui-standalone-preset.js",
            "/docs/swagger-ui/swagger-ui-bundle.js",
            "/docs/swagger-ui/index.css",
            "/docs/swagger-ui/swagger-ui.css"
    );
}
