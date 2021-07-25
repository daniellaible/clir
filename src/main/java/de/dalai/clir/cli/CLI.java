package de.dalai.clir.cli;

import de.dalai.clir.Clir;
import de.dalai.clir.cli.help.MainHelp;
import de.dalai.clir.cli.help.SubHelp;
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
    boolean continu = true;
    Scanner scanner = new Scanner(System.in);
    while (continu) {
      String line = scanner.nextLine();
      Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(line);
      List<String> commands = new ArrayList<>();
      while(m.find()){
        commands.add(m.group(1));
      }
     if (commands.get(0).equalsIgnoreCase("q") || commands.get(0).equalsIgnoreCase("quit")) {
        continu = false;

      }else if(commands.get(0).equalsIgnoreCase("h") || commands.get(0).equalsIgnoreCase("help")){
        if(commands.size() == 1) {
          MainHelp helper = new MainHelp();
          helper.printMainHelp();
        }else if(commands.size() == 2){
          SubHelp subHelper = new SubHelp();
          subHelper.printSubHelp(commands.get(1));
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
           System.out.println("""
               There is no collection selected
               please select the current collection
               or use     task <collection>
               to display all available collections use    c | collection
               """);
         }
       }
     }else {
       System.out.println("""
           Sorry, this command I don't know
           Please press h to get a list of all commands
           
           
           """);
     }
    }
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
