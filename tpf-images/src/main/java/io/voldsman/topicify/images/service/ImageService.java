package io.voldsman.topicify.images.service;

import io.voldsman.topicify.images.payload.ImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    List<ImageResponse> upload(List<MultipartFile> multipartFiles);
}
