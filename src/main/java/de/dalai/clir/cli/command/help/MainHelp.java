package de.dalai.clir.cli.command.help;

public class MainHelp {

  private static final String HELP_MSG = """
      Availabale commands are:
          a | add - adds a Task to a collection
          c | collection - prints the content of the selected collection
          h | help - prints help message
          q | quit - ends clir without saving
          t | task - prints all tasks of a collection
      """;

  public void printMainHelp() {
    System.out.println(HELP_MSG);
  }
}
