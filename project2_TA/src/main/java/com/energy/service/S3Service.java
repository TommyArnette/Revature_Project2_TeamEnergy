package com.energy.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.energy.util.S3Util;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;

@Service("s3Service")
public class S3Service {
    private String awsID = S3Util.awsID;
    private String awsKey = S3Util.awsKey;
    private String region = S3Util.region;
    private String bucketName = S3Util.bucketName;


    BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsID,awsKey);

    AmazonS3 s3Client = AmazonS3ClientBuilder
            .standard()
            .withRegion(Regions.fromName(region))
            .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
            .build();

    public void uploadFile(MultipartFile file){
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            s3Client.putObject(bucketName, file.getOriginalFilename(), file.getInputStream(),metadata);
        }catch (IOException e){
        }
    }

    public String getURL(String keyName){
        URL url = s3Client.getUrl(bucketName,keyName);
        return url.toString();
    }
}
