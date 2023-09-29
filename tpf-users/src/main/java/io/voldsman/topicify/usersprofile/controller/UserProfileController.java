package io.voldsman.topicify.usersprofile.controller;

import io.voldsman.topicify.common.constants.Defaults;
import io.voldsman.topicify.common.payload.ApiResponse;
import io.voldsman.topicify.usersprofile.payload.UpdateBioRequest;
import io.voldsman.topicify.usersprofile.payload.UpdateImageRequest;
import io.voldsman.topicify.usersprofile.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users-profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping
    public String test(@RequestAttribute(Defaults.REQUEST_ATTR_USERID_PARAM) UUID userId) {
        return "Users working..." + userId;
    }

    @PatchMapping("/bio")
    public ResponseEntity<ApiResponse<Void>> updateBio(@RequestAttribute(Defaults.REQUEST_ATTR_USERID_PARAM) UUID userId,
                                                       @Valid @RequestBody UpdateBioRequest updateBioRequest) {
        userProfileService.updateProfileBio(userId, updateBioRequest);
        ApiResponse<Void> apiResponse = new ApiResponse<>("Profile bio updated", true, null);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PatchMapping("/image")
    public ResponseEntity<ApiResponse<Void>> updateImage(@RequestAttribute(Defaults.REQUEST_ATTR_USERID_PARAM) UUID userId,
                                                         @Valid @RequestBody UpdateImageRequest updateImageRequest) {
        userProfileService.updateProfileImage(userId, updateImageRequest);
        ApiResponse<Void> apiResponse = new ApiResponse<>("Profile image updated", true, null);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
