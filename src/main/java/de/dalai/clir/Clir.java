package de.dalai.clir;

import de.dalai.clir.storage.Storage;
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
