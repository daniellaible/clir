package de.dalai.clir.cli.command.collection;

import de.dalai.clir.task.controller.CollectionManager;
import java.util.List;

public class Collection {

  public Collection(){
    displayCollections();
  }

  private void displayCollections() {
    System.out.println("Available collections are:");
    CollectionManager collectionmanager = new CollectionManager();
    final List<String> allCollections = collectionmanager.getAllCollections();
    if (allCollections.size() == 0) {
      System.out.println("    No available collections");
    } else {
      for (String name : allCollections) {
        System.out.println("    " + name);
      }
    }
  }

}
