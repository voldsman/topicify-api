package io.voldsman.topicify.images.controller;

import io.voldsman.topicify.common.payload.ApiResponse;
import io.voldsman.topicify.images.payload.ImageResponse;
import io.voldsman.topicify.images.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<List<ImageResponse>>> upload(@RequestParam("files") List<MultipartFile> multipartFiles) {
        List<ImageResponse> responseList = imageService.upload(multipartFiles);
        ApiResponse<List<ImageResponse>> apiResponse = new ApiResponse<>("Images successfully uploaded", true, responseList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
