package com.gaurav.awss3uploaddemo.controller;

import com.gaurav.awss3uploaddemo.enums.MediaType;
import com.gaurav.awss3uploaddemo.service.AWSUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MediaUploadController {

  @Autowired
  private AWSUploadService awsUploadService;

  @GetMapping(path = "/{country_code}/aws/v1/generateSignedUrl", produces = "application/json")
  public String generateSignedUrl() {
    String response = awsUploadService
        .generatePreSignedURL("demoFile", MediaType.VIDEO.getMediaId(),"1234");

    return response;
  }
}
