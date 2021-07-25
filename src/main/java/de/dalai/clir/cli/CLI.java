package de.dalai.clir.cli;

import de.dalai.clir.Clir;
import de.dalai.clir.task.controller.CollectionManager;
import de.dalai.clir.task.controller.TaskManager;
import de.dalai.clir.task.entity.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CLI {

  private Clir clir;

  public CLI(Clir clir) {
    this.clir = clir;
    writeInitMessage();
    startMainloop();
  }

  private void startMainloop() {
    boolean conti = true;
    Scanner scanner = new Scanner(System.in);
    while (conti) {
      String line = scanner.nextLine();
      Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(line);
      List<String> commands = new ArrayList<>();
      while(m.find()){
        commands.add(m.group(1));
      }
     if (commands.get(0).equalsIgnoreCase("q") || commands.get(0).equalsIgnoreCase("quit")) {
        conti = false;

      }else if(commands.get(0).equalsIgnoreCase("h") || commands.get(0).equalsIgnoreCase("help")){
        if(commands.size() == 1) {
          printMainHelp();
        }else if(commands.size() == 2){
          printSubHelp(commands.get(1));
        }
      }else if(commands.get(0).equalsIgnoreCase("a") || commands.get(0).equalsIgnoreCase("add")) {
       if (commands.size() == 2) {
         String collection;
         if(Clir.curCollection != null){
           String task = commands.get(1);
           collection = Clir.curCollection;
           collection = collection.replace("\"", "");
           TaskManager manager = new TaskManager();
           Task newTask = Task.builder().description(task).build();
           int id = manager.addTask(collection, newTask);
           Task savedTask = manager.getTask(id, collection);
           System.out.println("Added task to collection: " + collection);
           System.out.println(savedTask.blockOutput());
         }else {
           System.out.println("There is no collection selected either: ");
           System.out.println("  - you select the current collection  ");
           System.out.println("  - or you add this task using     add <task> <collection> ");
         }
       } else if (commands.size() == 3) {
         String task = commands.get(1);
         String collection = commands.get(2);
         Clir.curCollection = collection;
         collection = collection.replace("\"", "");
         TaskManager manager = new TaskManager();
         Task newTask = Task.builder().description(task).build();
         int id = manager.addTask(collection, newTask);
         Task savedTask = manager.getTask(id, collection);
         System.out.println("Added task to collection: " + collection);
         System.out.println(savedTask.blockOutput());

       }
     }
     else if(commands.get(0).equalsIgnoreCase("c") || commands.get(0).equalsIgnoreCase("collection")){
       displayCollections();
     }
     else if(commands.get(0).equalsIgnoreCase("t") || commands.get(0).equalsIgnoreCase("task")){
       if(commands.size() == 1){
         if(Clir.curCollection != null){
             TaskManager taskManager = new TaskManager();
           final List<Task> allOpenTasks = taskManager.getAllOpenTasks(Clir.curCollection);
           for(Task task : allOpenTasks){
             System.out.println(task.listOutputOpen());
           }
         }else{
           System.out.println("There is no collection selected");
           System.out.println("please select the current collection");
           System.out.println("or use     task <collection>");
           System.out.println("to display all available collections use    c | collection");
         }
       }
     }
    }
  }

  private void printSubHelp(String command) {
    if(command.equalsIgnoreCase("h") || command.equalsIgnoreCase("help")){
      System.out.println("Help page for: help|h");
      System.out.println("    h | help  <command> prints the help message of command");
    }
    else if(command.equalsIgnoreCase("q") || command.equalsIgnoreCase("quit")){
      System.out.println("Help page for:  quit|q");
      System.out.println("    q | quit  ends the program without saving changes");
    }
    else if(command.equalsIgnoreCase("a") || command.equalsIgnoreCase("add")){
      System.out.println("Help page for:  add|a");
      System.out.println("    a | add  add a new task to a topic");
      System.out.println("        a | add  <task> (in quotes) adds a new task to the current collection");
      System.out.println("        a | add  <task> <collection> adds a new task to the selected collection");
    }
    else if(command.equalsIgnoreCase("c") || command.equalsIgnoreCase("collection")){
      System.out.println("Help page for:  collection|c");
      System.out.println("    c | collection  prints all available collections");
    }
  }

  private void printMainHelp() {
    System.out.println("Availabale commands are: ");
    System.out.println("    a | add - adds a Task to a collection");
    System.out.println("    c | collection - prints the content of the selected collection");
    System.out.println("    h | help - prints help message");
    System.out.println("    q | quit - ends clir without saving");
    System.out.println("    t | task - prints all tasks of a collection");
  }

  private void writeInitMessage() {
    System.out.println("Welcome to CLI Reminder version: " + Clir.properties.getProperty("version"));
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
