package de.dalai.clir.task.boundary;

import de.dalai.clir.task.controller.TaskManager;
import de.dalai.clir.task.entity.Task;
import de.dalai.clir.tool.TimeTool;
import de.dalai.clir.tool.UuidCreator;

public class TaskResource {

  public void newTask(String collection, String text){
    Task task = Task.builder()
        .creationDate(TimeTool.getLocalDateTime())
        .description(text)
        .finished(false)
        .uuid(UuidCreator.createUuid())
        .build();

    TaskManager manager = new TaskManager();
    manager.addTask(collection, task);
  }

}
