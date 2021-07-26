package de.dalai.clir.cli.command.task;

import de.dalai.clir.Clir;
import de.dalai.clir.cli.TextBlocks;
import de.dalai.clir.task.controller.TaskManager;
import java.util.List;

public class TaskCommand {

  public TaskCommand(final List<String> commands) {
    taskFunctionality(commands);
  }

  private void taskFunctionality(final List<String> commands) {
    if (commands.size() == 1) {
      if (Clir.curCollection != null) {
        TaskManager taskManager = new TaskManager();
        final List<de.dalai.clir.task.entity.Task> allOpenTasks = taskManager.getAllOpenTasks(Clir.curCollection);
        for (de.dalai.clir.task.entity.Task task : allOpenTasks) {
          System.out.println(task.listOutputOpen());
        }
      } else {
        System.out.println(TextBlocks.NO_COLLECTION_SELECTED_TASK);
      }
    }
  }
}

