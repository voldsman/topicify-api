package io.voldsman.topicify.usersprofile.controller;

import io.voldsman.topicify.common.constants.Defaults;
import io.voldsman.topicify.common.payload.ApiResponse;
import io.voldsman.topicify.usersprofile.payload.ProfileResponse;
import io.voldsman.topicify.usersprofile.payload.UpdateBioRequest;
import io.voldsman.topicify.usersprofile.payload.UpdateImageRequest;
import io.voldsman.topicify.usersprofile.payload.UpdateLinksRequest;
import io.voldsman.topicify.usersprofile.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users-profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PatchMapping("/bio")
    public ResponseEntity<ApiResponse<Void>> updateBio(@RequestAttribute(Defaults.REQUEST_ATTR_USERID_PARAM) String userId,
                                                       @Valid @RequestBody UpdateBioRequest updateBioRequest) {
        userProfileService.updateProfileBio(userId, updateBioRequest);
        ApiResponse<Void> apiResponse = new ApiResponse<>("Profile bio updated", true, null);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PatchMapping("/image")
    public ResponseEntity<ApiResponse<Void>> updateImage(@RequestAttribute(Defaults.REQUEST_ATTR_USERID_PARAM) String userId,
                                                         @Valid @RequestBody UpdateImageRequest updateImageRequest) {
        userProfileService.updateProfileImage(userId, updateImageRequest);
        ApiResponse<Void> apiResponse = new ApiResponse<>("Profile image updated", true, null);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PatchMapping("/links")
    public ResponseEntity<ApiResponse<Void>> updateLinks(@RequestAttribute(Defaults.REQUEST_ATTR_USERID_PARAM) String userId,
                                                         @Valid @RequestBody UpdateLinksRequest updateLinksRequest) {
        userProfileService.updateProfileLinks(userId, updateLinksRequest);
        ApiResponse<Void> apiResponse = new ApiResponse<>("Profile links updated", true, null);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<ProfileResponse>> getMyProfile(@RequestAttribute(Defaults.REQUEST_ATTR_USERID_PARAM) String tokenUserId) {
        ProfileResponse myProfile = userProfileService.getMyProfile(tokenUserId);
        ApiResponse<ProfileResponse> apiResponse = new ApiResponse<>("My profile", true, myProfile);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{profileUserId}")
    public ResponseEntity<ApiResponse<ProfileResponse>> previewUserProfile(@RequestAttribute(Defaults.REQUEST_ATTR_USERID_PARAM) String tokenUserId,
                                                                           @PathVariable String profileUserId) {
        ProfileResponse previewUserProfile = userProfileService.previewUserProfile(tokenUserId, profileUserId);
        ApiResponse<ProfileResponse> apiResponse = new ApiResponse<>("Preview profile", true, previewUserProfile);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
