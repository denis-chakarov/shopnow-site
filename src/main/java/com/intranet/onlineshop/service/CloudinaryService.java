package com.intranet.onlineshop.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Class used for declaring the business logic methods regarding the cloud service provider
 */
public interface CloudinaryService {

    /**
     * upload a message to the cloud
     * @param multipartFile file representing the pitcture
     * @return returns the url of the uploaded picture
     * @throws IOException when the file does not exist
     */
    String uploadImage(MultipartFile multipartFile) throws IOException;
}
