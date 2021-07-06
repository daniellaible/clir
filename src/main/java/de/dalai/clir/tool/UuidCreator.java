package de.dalai.clir.tool;

import java.util.UUID;

public class UuidCreator {

  public static String createUuid(){
    return UUID.randomUUID().toString().replace("-", "");
  }
}
