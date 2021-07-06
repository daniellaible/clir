package de.dalai.clir.tool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeTool {

  private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

  public static String getLocalDateTime(){
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);
    return now.format(formatter);
  }

}
