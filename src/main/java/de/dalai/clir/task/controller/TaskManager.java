package de.dalai.clir.task.controller;

import de.dalai.clir.task.entity.Task;
import de.dalai.clir.task.entity.TaskStorage;
import de.dalai.clir.tool.TimeTool;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

  public int addTask(final String collection,final Task task){
    try {
      TaskStorage storage = TaskStorage.getInstance();
      task.setFinished(false);
      task.setCreationDate(TimeTool.getLocalDateTime());
      return storage.addTask(collection, task);
    }catch(Exception ex){
      throw new RuntimeException("Something went horribly wrong saving the task");
    }
  }

  public void getTask(final String collection, final String taskName){
    //TODO
  }

  public List<Task> getAllOpenTasks(final String collection){
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

  public List<Task> getClosedTasks(final String collection){
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

  public List<Task> getAllTasks(final String collection){
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

  public Task getTask(final int id){
    TaskStorage storage = TaskStorage.getInstance();
    final List<String> collections = storage.getCollections();
    for(String collection : collections){
      Task result = getTask(id, collection);
      if( null != result){
        return result;
      }
    }
    return null;
  }

  public Task getTask(final int id, final String collection){
    final List<Task> tasks = getTasks(collection);
    if(id > tasks.size()-1){
      throw new IllegalArgumentException();
    }
    return tasks.get(id);
  }

  public boolean reOpenTask(final int id, final String collection){
    Task task = getTask(id, collection);
    if(null != task){
      task.setFinished(false);
      return true;
    }
    return false;
  }

  public boolean reOpenTask(final int id){
    final Task task = getTask(id);
    if(null != task){
      task.setFinished(false);
      return true;
    }
    return false;
  }

  public boolean deleteTask(final String uuid){
    throw new UnsupportedOperationException();
  }

  public boolean deleteTask(final String collection,final  String uuid){
    throw new UnsupportedOperationException();
  }

  public boolean finishTask(final int id){
    final Task task = getTask(id);
    if(null != task){
      task.setFinished(true);
      return true;
    }
    return false;
  }

  public boolean finishTask(final int id, final String collection){
    final Task task = getTask(id, collection);
    if(null != task){
      task.setFinished(true);
      return true;
    }
    return false;
  }

  private List<Task> getTasks(final String collection) {
    TaskStorage storage = TaskStorage.getInstance();
    final List<Task> taskCollection = storage.getTasksFromCollection(collection);
    return taskCollection;
  }
}
