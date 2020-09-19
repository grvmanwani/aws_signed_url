package com.gaurav.awss3uploaddemo.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.gaurav.awss3uploaddemo.enums.MediaType;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AWSUploadServiceImpl implements AWSUploadService{

  @Value("${aws.s3.accessKey}")
  private String accessKey;

  @Value("${aws.s3.secretKey}")
  private String secretKey;

  @Value("${aws.s3.clientRegion}")
  private String clientRegion;

  private AmazonS3 s3Client;

  @Value("${aws.s3.media.bucket.prefix}")
  private String mediaBucketPrefix;

  @Value("${aws.s3.url.expirationTime}")
  private long expirationTime;

  private static final String FILE_SEPARATOR = "/";

  @PostConstruct
  private void initializeAWSClient() {
    AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
    s3Client = AmazonS3ClientBuilder.standard()
        .withCredentials(new AWSStaticCredentialsProvider(credentials)).enableAccelerateMode().withRegion(clientRegion)
        .build();

  }


  @Override
  public String generatePreSignedURL(String fileName, int mediaType, String userId) {
    final String mediaKey = String.join(FILE_SEPARATOR, userId, MediaType.valueOf(mediaType).getDescription(), fileName);
    final Date urlExpiryTime = new Date();
    urlExpiryTime.setTime(urlExpiryTime.getTime() + expirationTime);
    GeneratePresignedUrlRequest generatePresignedUrlRequest=new GeneratePresignedUrlRequest(getMediaBucketName(mediaType), mediaKey, HttpMethod.PUT)
        .withExpiration(urlExpiryTime);


    try {
      return s3Client.generatePresignedUrl(generatePresignedUrlRequest).toString();
    } catch (SdkClientException ex) {

    }
    return null;
  }

  private String getMediaBucketName(final int mediaType) {

    final StringBuilder mediaBucketName = new StringBuilder(mediaType == 1 ? "source-" : "");
    mediaBucketName.append(mediaBucketPrefix);
    mediaBucketName.append("IN");
    mediaBucketName.append(mediaType);
    return mediaBucketName.toString();

  }
}
