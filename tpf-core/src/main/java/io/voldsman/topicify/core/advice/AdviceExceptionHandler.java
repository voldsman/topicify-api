package io.voldsman.topicify.core.advice;

import io.voldsman.topicify.common.exception.*;
import io.voldsman.topicify.common.payload.ApiError;
import io.voldsman.topicify.common.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class AdviceExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<ApiError>> handleNotFoundException(NotFoundException ex, WebRequest request) {
        ApiError apiResponse = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getDescription(false));

        ApiResponse<ApiError> errorResponse = new ApiResponse<>("Record not found", false, apiResponse);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ApiResponse<ApiError>> handleAlreadyExistsException(AlreadyExistsException ex, WebRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                request.getDescription(false));

        ApiResponse<ApiError> errorResponse = new ApiResponse<>("Record already exists", false, apiError);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<ApiError>> handleBadRequestException(BadRequestException ex, WebRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false));

        ApiResponse<ApiError> errorResponse = new ApiResponse<>("Bad request error", false, apiError);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiResponse<ApiError>> handleForbiddenException(ForbiddenException ex, WebRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.FORBIDDEN.value(),
                ex.getMessage(),
                request.getDescription(false));

        ApiResponse<ApiError> errorResponse = new ApiResponse<>("Access to resource forbidden", false, apiError);
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<ApiError>> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.UNAUTHORIZED.value(),
                ex.getMessage(),
                request.getDescription(false));

        ApiResponse<ApiError> errorResponse = new ApiResponse<>("Unauthorized", false, apiError);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, Object>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false));

        Map<String, String> errors = new HashMap<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            String field = fieldError.getField();
            String defaultMessage = fieldError.getDefaultMessage();
            errors.put(field, defaultMessage);
        }

        Map<String, Object> mapError = new HashMap<>();
        mapError.put("error", apiError);
        mapError.put("validations", errors);

        ApiResponse<Map<String, Object>> errorResponse = new ApiResponse<>("Validation error", false, mapError);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalException.class)
    public ResponseEntity<ApiResponse<ApiError>> handleInternalException(InternalException ex, WebRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                request.getDescription(false));

        ApiResponse<ApiError> errorResponse = new ApiResponse<>("Internal error", false, apiError);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<ApiError>> handleException(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                request.getDescription(false));

        ApiResponse<ApiError> errorResponse = new ApiResponse<>("Internal error", false, apiError);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
