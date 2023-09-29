package io.voldsman.topicify.core.aspect;

import io.voldsman.topicify.common.constants.Defaults;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
        // Method is empty as this is just a Pointcut
    }

    @Pointcut("within(io.voldsman.topicify..*)")
    public void applicationPackagePointcut() {
        // Method is empty as this is just a Pointcut
    }

    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Exception in {}.{}() with message = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), e.getMessage());
    }

    @Around("applicationPackagePointcut() && springBeanPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodArgs = Arrays.toString(joinPoint.getArgs());
        String maskedLogMessage = maskLogMessage(methodArgs);

        log.info("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), maskedLogMessage);
        try {
            Object result = joinPoint.proceed();
            log.info("Exit: {}.{}()", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        }
    }

    private String maskLogMessage(final String methodArgs) {
        String patternStr = "(" + String.join("|", Defaults.logMaskedFields) + ")=[^,\\]]+";
        Pattern pattern = Pattern.compile(patternStr);

        Matcher matcher = pattern.matcher(methodArgs);
        StringBuilder maskedInput = new StringBuilder();

        while (matcher.find()) {
            String field = matcher.group(1);
            String maskedValue = "*****";
            matcher.appendReplacement(maskedInput, field + "=" + maskedValue);
        }

        matcher.appendTail(maskedInput);
        return maskedInput.toString();
    }
}