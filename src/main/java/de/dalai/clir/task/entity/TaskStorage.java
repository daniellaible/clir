package de.dalai.clir.task.entity;

import java.util.HashMap;
import java.util.Map;

public class TaskStorage {

  private static TaskStorage instance;

  private Map<String, Map<String,Task>> taskStorage;

  private TaskStorage(){
    taskStorage = new HashMap<String, Map<String, Task>>();
  }

  public static TaskStorage getInstance(){
    if(instance == null){
      instance = new TaskStorage();
    }
    return instance;
  }

  public void addTask(String collectionName, Task task){
    final Map<String,Task> taskCollection = taskStorage.get(collectionName);
    if(taskCollection == null){
      Map<String, Task> newCollection = new HashMap<>();
      newCollection.put(task.getUuid(), task);
      taskStorage.put(collectionName, newCollection);
    }else{
      taskCollection.put(task.getUuid(), task);
      taskStorage.put(collectionName,taskCollection);
    }
  }



}
