package de.dalai.clir;

import de.dalai.clir.cli.CLI;
import de.dalai.clir.tool.PropertiesImporter;
import de.dalai.clir.tool.StorageHelper;
import java.io.File;
import java.util.Properties;

public class Clir {

  public static File clirDir;
  public static Properties properties;
  public static String curCollection;

  public static void main(String[] args){
    Clir checker = new Clir();
    checker.run();
  }

  private void run(){
    importClirDir();
    importProperties();
    startCli();
  }

  private void importClirDir(){
    StorageHelper storage = new StorageHelper();
    clirDir = storage.getClirFolder();
  }

  private void importProperties(){
    PropertiesImporter propsImporter = new PropertiesImporter();
    properties = propsImporter.importProperties();
  }

  private void startCli(){
    CLI cli = new CLI(this);
  }
}
