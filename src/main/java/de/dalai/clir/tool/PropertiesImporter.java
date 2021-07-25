package de.dalai.clir.tool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesImporter {

  private static final String PROPERTIES = "clir.properties";

  public Properties importProperties()  {
    Properties props = new Properties();
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    try(InputStream resourceStream = loader.getResourceAsStream(PROPERTIES)) {
      props.load(resourceStream);
    } catch (IOException e) {
      System.err.println("Unable to load file");
    }
    return props;
  }


}
