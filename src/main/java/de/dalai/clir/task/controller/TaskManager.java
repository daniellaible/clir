package de.dalai.clir.task.controller;

import de.dalai.clir.task.entity.Task;
import de.dalai.clir.task.entity.TaskStorage;

public class TaskManager {

  public void addTask(String collection, Task task){
    TaskStorage storage = TaskStorage.getInstance();
    storage.addTask(collection, task);
  }

}
