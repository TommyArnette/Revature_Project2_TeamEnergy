package com.revature.controller;

import com.revature.models.JsonResponse;
import com.revature.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * This entire class is used to control the uploading of images to profiles and posts.
 */
@RestController("uploadFileController")
@RequestMapping(value="api")
@CrossOrigin(value = "http://localhost:4200/", allowCredentials = "true")
public class UploadFileController {

    @Autowired
    S3Service s3Services;

    /**
     * Used to upload a file.
     * EXPAND INFO
     *
     * @param file  file is passed to method for upload
     * @return      returns JsonResponse
     */
    @PostMapping("file/upload")
    public JsonResponse uploadFile(@RequestParam("file") MultipartFile file){
        String keyName =file.getOriginalFilename();
        s3Services.uploadFile(file);
        return new JsonResponse(true, "file uploaded", keyName);
    }

    /**
     * Downloads the file associated with profile and post
     * EXPAND INFO
     *
     * @param keyname   passes URL of image to the method
     * @return          returns JsonResponse
     */
    @GetMapping("file/{keyname}")
    public JsonResponse downloadFile(@PathVariable String keyname) {
        return new JsonResponse(true, "file name fetched", s3Services.getURL(keyname));
    }
}
