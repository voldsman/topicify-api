package io.voldsman.topicify.auth.interceptor;

import io.voldsman.topicify.accesstoken.payload.AccessTokenDto;
import io.voldsman.topicify.accesstoken.service.AccessTokenService;
import io.voldsman.topicify.common.constants.Defaults;
import io.voldsman.topicify.common.constants.Routes;
import io.voldsman.topicify.common.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final AccessTokenService accessTokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String requestURI = request.getRequestURI();

        if (Routes.PUBLIC_ROUTES.contains(requestURI)) {
            log.info("<> Public endpoint, uri: {}", requestURI);
            return true;
        }

        log.info("<> Secured endpoint, uri: {}", requestURI);
        final String accessToken = request.getHeader(Defaults.AUTHORIZATION_HEADER);
        if (Objects.isNull(accessToken)) {
            throw new UnauthorizedException("Access token doesn't present in request");
        }

        final AccessTokenDto accessTokenDetails = accessTokenService.getAccessTokenDetails(accessToken);
        if (Objects.isNull(accessTokenDetails)) {
            throw new UnauthorizedException("Access token has expired");
        }

        log.info("<> Secured endpoint, token valid, proceed");
        final UUID userId = accessTokenDetails.getUserId();
        Objects.requireNonNull(RequestContextHolder.getRequestAttributes())
                .setAttribute(Defaults.REQUEST_ATTR_USERID_PARAM, userId, RequestAttributes.SCOPE_REQUEST);
        return true;
    }
}
