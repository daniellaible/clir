package de.dalai.checker;

import de.dalai.checker.storage.Storage;
import java.io.File;

public class Clir {

  public File clirDir;

  public static void main(String[] args){
    Clir checker = new Clir();
    checker.run();
  }

  private void run(){
    Storage storage = new Storage();
    clirDir = storage.getClirFolder();
  }

}
