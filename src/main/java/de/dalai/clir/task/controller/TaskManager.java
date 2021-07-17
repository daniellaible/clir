package de.dalai.clir.task.controller;

import de.dalai.clir.task.entity.Task;
import de.dalai.clir.task.entity.TaskStorage;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

  public void addTask(String collection, Task task){
    try {
      TaskStorage storage = TaskStorage.getInstance();
      storage.addTask(collection, task);
    }catch(Exception ex){
      throw new RuntimeException("Something went horribly wrong saving the task");
    }
  }

  public List<Task> getAllOpenTasks(String collection){
    final List<Task> taskCollection = getTasks(collection);
    List<Task> openTaskCollection = new ArrayList<>();
    for(Task task : taskCollection){
      if(!task.isFinished()){
        openTaskCollection.add(task);
      }
    }
    return openTaskCollection;
  }

  public List<Task> getAllOpenTasks(){
    TaskStorage storage = TaskStorage.getInstance();
    final List<String> allCollections = storage.getCollections();
    List<Task> tasks = new ArrayList<>();
    for(String collection : allCollections){
      final List<Task> tasksFromCollection = getTasks(collection);
      for(Task task : tasksFromCollection){
        if(!task.isFinished()){
          tasks.add(task);
        }
      }
    }
    return tasks;
  }

  public List<Task> getClosedTasks(String collection){
    List<Task> result = new ArrayList<>();
    final List<Task> tasks = getTasks(collection);
    for(Task task : tasks){
      if(task.isFinished()){
        result.add(task);
      }
    }
    return result;
  }

  public List getClosedTasks(){
    TaskStorage storage = TaskStorage.getInstance();
    List<Task> result = new ArrayList<>();
    final List<String> collections = storage.getCollections();
    for(String collection : collections){
      final List<Task> tasks = getTasks(collection);
      for(Task task: tasks){
        if(task.isFinished()){
          result.add(task);
        }
      }
    }
    return result;
  }

  public List<Task> getAllTasks(String collection){
    return getTasks(collection);
  }

  public List<Task> getAllTasks(){
    List<Task> result = new ArrayList<>();
    TaskStorage storage = TaskStorage.getInstance();
    final List<String> collections = storage.getCollections();
    for(String collection : collections){
      result.addAll(storage.getTasksFromCollection(collection));
    }
    return result;
  }

  public boolean getTask(String uuid){
    throw new UnsupportedOperationException();
  }

  public boolean getTask(String uuid, String collection){
    throw new UnsupportedOperationException();
  }

  public boolean reOpenTask(String uuid, String collection){
    throw new UnsupportedOperationException();
  }

  public boolean reOpenTask(String uuid){
    throw new UnsupportedOperationException();
  }

  public boolean deleteTask(String uuid){
    throw new UnsupportedOperationException();
  }

  public boolean deleteTask(String collection, String uuid){
    throw new UnsupportedOperationException();
  }

  public boolean finishTask(String uuid){
    throw new UnsupportedOperationException();
  }

  public boolean finishTask(String uuid, String collection){
    throw new UnsupportedOperationException();
  }

  private List<Task> getTasks(String collection) {
    TaskStorage storage = TaskStorage.getInstance();
    final List<Task> taskCollection = storage.getTasksFromCollection(collection);
    return taskCollection;
  }
}
