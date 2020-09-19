package com.gaurav.awss3uploaddemo.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ImageType {
  SMALL(2, "SMALL"),
  MEDIUM(3, "MEDIUM"),
  LARGE(4, "LARGE"),
  DEFAULT(5, "DEFAULT");

  private static final Map<Integer, ImageType> IMAGE_TYPE_MAP =
      Arrays.stream(ImageType.values())
          .collect(Collectors.toMap(s -> s.getId(), Function.identity()));

  private final int id;
  private final String name;

  ImageType(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public static ImageType valueOf(int statusCode) {
    if (IMAGE_TYPE_MAP.containsKey(statusCode)) {
      return IMAGE_TYPE_MAP.get(statusCode);
    }
    throw new IllegalArgumentException("record not found");
  }
}
