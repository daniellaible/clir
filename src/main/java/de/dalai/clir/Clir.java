package de.dalai.clir;

import de.dalai.clir.tool.StorageHelper;
import java.io.File;

public class Clir {

  public static File clirDir;

  public static void main(String[] args){
    Clir checker = new Clir();
    checker.run();
  }

  private void run(){
    StorageHelper storage = new StorageHelper();
    clirDir = storage.getClirFolder();
  }
}
