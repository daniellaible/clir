package de.dalai.clir.storage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Storage {

  public File getClirFolder(){
    String pathStr = System.getProperty("user.home") + File.separator + ".clir";
    if(!Files.isDirectory(Paths.get(pathStr))){
      new File(pathStr).mkdirs();
    }
    return new File(pathStr);
  }
}
