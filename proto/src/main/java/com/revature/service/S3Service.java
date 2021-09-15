package com.revature.service;

import java.io.IOException;
import java.net.URL;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.revature.util.S3Util;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service method to utilize the Amazon AWS S3 functionality.
 *
 * Variable information is passed to this service through the S3Util class (awsID, awsKey, region, bucketName).
 *
 * This class is related to uploading images to User profiles and User posts.
 * */
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

    /**
     * Used to upload an image file.
     * May be applied to a User profile image or a Post image.
     *
     * @param file  file that is passed to the method for upload
     */
    public void uploadFile(MultipartFile file){
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            s3Client.putObject(bucketName, file.getOriginalFilename(), file.getInputStream(),metadata);
        }catch (IOException e){
        }
    }

    /**
     * Returns the String associated with the PostImage that was selected for upload.
     * The keyName is the URL for the specific image.
     *
     * @param keyName   URL for the image being uploaded.
     * @return          returns the String equivalent to the PostImage URL
     */
    public String getURL(String keyName){
        URL url = s3Client.getUrl(bucketName,keyName);
        return url.toString();
    }
}