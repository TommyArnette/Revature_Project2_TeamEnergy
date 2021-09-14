package com.revature.controller;

import com.revature.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController("uploadFileController")
@RequestMapping(value="api")
public class UploadFileController {

    @Autowired
    S3Service s3Services;

    @PostMapping("file/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file){
        String keyName =file.getOriginalFilename();
        s3Services.uploadFile(file);
        return keyName;
    }

    @GetMapping("file/{keyname}")
    public String downloadFile(@PathVariable String keyname) {
        return s3Services.getURL(keyname);
    }
}
