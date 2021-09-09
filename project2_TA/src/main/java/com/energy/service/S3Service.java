package com.energy.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.energy.util.S3Util;
import org.springframework.stereotype.Service;

import java.io.File;


public class S3Service {
    // create an S3Util.java in the util package with public static variables for the variables below. See discord for the values
    private String awsID = S3Util.awsID;
    private String awsKey = S3Util.awsKey;
    private String region = S3Util.region;
    private String bucketName = S3Util.bucketName;

    BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsID, awsKey);

    AmazonS3 s3Client = AmazonS3ClientBuilder
            .standard()
            .withRegion(Regions.fromName(region))
            .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
            .build();

    public void uploadFile(File file, String path){
        s3Client.putObject(bucketName, path + file.getName(), file);
    }
}
