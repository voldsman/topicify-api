package io.voldsman.topicify.images.service.impl;

import io.voldsman.topicify.common.constants.Defaults;
import io.voldsman.topicify.common.exception.BadRequestException;
import io.voldsman.topicify.common.exception.InternalException;
import io.voldsman.topicify.images.payload.ImageResponse;
import io.voldsman.topicify.images.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class FileSystemImageService implements ImageService {

    private final Path uploadDirectory;

    public FileSystemImageService() {
        this.uploadDirectory = Paths.get(Defaults.DEFAULT_IMAGES_FS_UPLOAD_FOLDER);
        createFolder();
    }

    @Override
    public List<ImageResponse> upload(List<MultipartFile> multipartFiles) {
        if (multipartFiles.isEmpty()) {
            throw new BadRequestException("Must send files");
        }

        List<ImageResponse> imageResponses = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            if (multipartFile.isEmpty()) {
                throw new BadRequestException("File is empty");
            }

            String originalFilename = multipartFile.getOriginalFilename();
            if (Objects.isNull(originalFilename)) {
                throw new BadRequestException("Original file name is null");
            }

            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueImageName = UUID.randomUUID() + fileExtension;

            try {
                multipartFile.transferTo(uploadDirectory.resolve(uniqueImageName));

                ImageResponse imageResponse = new ImageResponse();
                imageResponse.setImage(uniqueImageName);
                imageResponses.add(imageResponse);
            } catch (Exception e) {
                throw new InternalException("Exception occurred when saving file");
            }
        }

        return imageResponses;
    }

    private void createFolder() {
        if (!Files.exists(uploadDirectory)) {
            try {
                Files.createDirectories(uploadDirectory);
                log.info("Folder created: {}", uploadDirectory.toAbsolutePath());
            } catch (Exception e) {
                throw new InternalException("Exception occurred when creating folder");
            }
        } else {
            log.info("Folder already created: {}", uploadDirectory.toAbsolutePath());
        }
    }
}
