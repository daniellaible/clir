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
  private Map<String, List<Task>> taskStorage;

  private TaskStorage(){
    taskStorage = new HashMap<>();
    gson = new Gson();
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

  public int addTask(String name, Task task){
    List<Task> collection = getCollection(name);
    task.setId(collection.size());
    collection.add(task);
    return task.getId();
  }

  public List<String> getCollections(){
    List<String> collections = new ArrayList<>();
    for(String collection : taskStorage.keySet()){
      collections.add(collection);
    }
    return collections;
  }

  public List<Task> getTasksFromCollection(final String name){
    return getCollection(name);
  }

  public List<Task> createCollection(final String name){
    return taskStorage.put(name, new ArrayList<>());
  }

  private List<Task> getCollection(final String name){
    List<Task>collection = taskStorage.get(name);
    if(collection == null){
      taskStorage.put(name, new ArrayList<>());
      collection = taskStorage.get(name);
    }
    return collection;
  }
}
