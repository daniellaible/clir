package de.dalai.clir.task.controller;

import de.dalai.clir.task.entity.TaskStorage;
import java.util.List;

public class CollectionManager {

  private  TaskStorage storage;

  public CollectionManager(){
    storage = TaskStorage.getInstance();
  }

  public List<String> getAllCollections(){
    return storage.getCollections();
  }

}
