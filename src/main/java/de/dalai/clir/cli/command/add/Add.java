package de.dalai.clir.cli.command.add;

import de.dalai.clir.Clir;
import de.dalai.clir.cli.TextBlocks;
import de.dalai.clir.task.controller.TaskManager;
import de.dalai.clir.task.entity.Task;
import java.util.List;

public class Add {

  public Add(List<String> commands) {
    addFunctionality(commands);
  }

  private void addFunctionality(List<String> commands) {
    if (commands.size() == 2) {
      if (Clir.curCollection != null) {
        withoutCollection(commands);
      } else {
        System.out.println(TextBlocks.NO_COLLECTION_SELECTED_ADD);
      }
    } else if (commands.size() == 3) {
      withCollection(commands);
    }
  }

  private void withCollection(List<String> commands) {
    String task = commands.get(1);
    String collection = commands.get(2);
    Clir.curCollection = collection;
    collection = collection.replace("\"", "");
    Task savedTask = saveTask(task, collection);
    printConfirmation(collection, savedTask);
  }

  private void withoutCollection(List<String> commands) {
    String collection;
    String task = commands.get(1);
    collection = Clir.curCollection;
    collection = collection.replace("\"", "");
    Task savedTask = saveTask(task, collection);
    printConfirmation(collection, savedTask);
  }

  private Task saveTask(String task, String collection) {
    TaskManager manager = new TaskManager();
    Task newTask = Task.builder().description(task).build();
    int id = manager.addTask(collection, newTask);
    return manager.getTask(id, collection);
  }

  private void printConfirmation(String collection, Task task) {
    System.out.println("Added task to collection: " + collection);
    System.out.println(task.blockOutput());
  }

}
