package de.dalai.clir.task.entity;

import com.google.gson.Gson;
import de.dalai.clir.Clir;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskStorage {

  private static TaskStorage instance;

  private Gson gson;
  private Map<String, Map<String,Task>> taskStorage;

  private TaskStorage(){
    this.gson = new Gson();
    taskStorage = new HashMap<>();
  }

  public static TaskStorage getInstance(){
    if(instance == null){
      instance = new TaskStorage();
    }
    return instance;
  }

  public void cleanTaskStorage(){
    if(null != taskStorage){
      taskStorage = new HashMap<>();
    }
  }

  public void serializeCollection(String collection) throws IOException {
    List<Task> tasks= getTasksFromCollection(collection);
    Collections.sort(tasks, Comparator.comparing(Task::isFinished).thenComparing(Task::getCreationDate));
    String stringified = gson.toJson(tasks);
    Path path = new File(Clir.clirDir, collection + ".json").toPath();
    Files.writeString(path, stringified);
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

  public List<String> getCollections(){
    List<String> collections = new ArrayList<>();
    for(String collection : taskStorage.keySet()){
      collections.add(collection);
    }
    return collections;
  }

  public List<Task> getTasksFromCollection(String collectionName){
    List<Task> result = new ArrayList<>();
    final Map<String, Task> stringTaskMap = taskStorage.get(collectionName);
    if(null != stringTaskMap) {
      for (String uuid : stringTaskMap.keySet()) {
        result.add(stringTaskMap.get(uuid));
      }
    }
    return result;
  }
}
