package com.gaurav.awss3uploaddemo.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum MediaType {
  IMAGE(1, "IMAGE"),
  VIDEO(2, "VIDEO"),
  DOCUMENT(3, "DOCUMENT"),
  AUDIO(4, "AUDIO");

  private final int mediaId;
  private final String description;
  private static final Map<Integer, MediaType> MEDIA_TYPE_MAP =
      Arrays.stream(MediaType.values())
          .collect(Collectors.toMap(s -> s.getMediaId(), Function.identity()));

  MediaType(int mediaId, String description) {
    this.mediaId = mediaId;
    this.description = description;
  }

  public int getMediaId() {
    return mediaId;
  }

  public String getDescription() {
    return description;
  }

  public static MediaType valueOf(int statusCode) {
    if (MEDIA_TYPE_MAP.containsKey(statusCode)) {
      return MEDIA_TYPE_MAP.get(statusCode);
    }
    throw new IllegalStateException("no record found");

  }
}
