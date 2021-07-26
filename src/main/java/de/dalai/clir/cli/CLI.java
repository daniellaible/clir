package de.dalai.clir.cli;

import de.dalai.clir.Clir;
import de.dalai.clir.cli.command.add.Add;
import de.dalai.clir.cli.command.collection.Collection;
import de.dalai.clir.cli.command.help.Help;
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
      final List<String> commands = retrieveCommands(line);
      if (commands.get(0).equalsIgnoreCase("q") || commands.get(0).equalsIgnoreCase("quit")) {
        continu = false;
     }
     else if(commands.get(0).equalsIgnoreCase("h") || commands.get(0).equalsIgnoreCase("help")){
       new Help(commands);
     }
     else if(commands.get(0).equalsIgnoreCase("a") || commands.get(0).equalsIgnoreCase("add")) {
       new Add(commands);
     }
     else if(commands.get(0).equalsIgnoreCase("c") || commands.get(0).equalsIgnoreCase("collection")){
       new Collection();
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
           System.out.println(TextBlocks.NO_COLLECTION_SELECTED_TASK);
         }
       }
     }else {
       System.out.println(TextBlocks.UNKOWN_COMMAND);
     }
    }
  }

  private List<String> retrieveCommands(final String line){
    List<String> commands = new ArrayList<>();
    Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(line);
    while(m.find()){
      commands.add(m.group(1));
    }
    return commands;
  }

  private void writeInitMessage() {
    System.out.println("Welcome to CLI Reminder version: " + Clir.properties.getProperty("version"));
    new Collection();
  }
}
