package com.gaurav.awss3uploaddemo.service;

public interface AWSUploadService {

  public String generatePreSignedURL(String fileName, int mediaType, String userId);

}
