package de.dalai.clir.cli.command.help;

public class SubHelp {

  private static final String TASK_MESSAGE = """
      Help page for:  task|t
          t | task print all open tasks for a collection if a collection is selected
              t | task <collectionname> prints all open task for a specific collection
      """;
  private static final String ADD_HELP = """
      Help page for:  add|a
          a | add  add a new task to a topic
              a | add  <task> (in quotes) adds a new task to the current collection
              a | add  <task> <collection> adds a new task to the selected collection
      """;
  private static final String COLLECTION_HELP = """
      Help page for:  collection|c
          c | collection  prints all available collections
      """;
  private static final String QUIT_HELP = """
      Help page for:  quit|q
          q | quit  ends the program without saving changes
      """;
  private static final String HELP_HELP = """
      Help page for: help|h
          h | help  <command> prints the help message of command
      """;

  public void printSubHelp(String command) {
    if (command.equalsIgnoreCase("h") || command.equalsIgnoreCase("help")) {
      System.out.println(HELP_HELP);
    } else if (command.equalsIgnoreCase("q") || command.equalsIgnoreCase("quit")) {
      System.out.println(QUIT_HELP);
    } else if (command.equalsIgnoreCase("a") || command.equalsIgnoreCase("add")) {
      System.out.println(ADD_HELP);
    } else if (command.equalsIgnoreCase("c") || command.equalsIgnoreCase("collection")) {
      System.out.println(COLLECTION_HELP);
    } else if (command.equalsIgnoreCase("t") || command.equalsIgnoreCase("task")) {
      System.out.println(TASK_MESSAGE);
    }else{

    }
  }
}
