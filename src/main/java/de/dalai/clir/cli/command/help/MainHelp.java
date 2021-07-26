package de.dalai.clir.cli.command.help;

public class MainHelp {

  public void printMainHelp() {
    System.out.println(genHelpOutput());
  }

  private String genHelpOutput(){
    return """
        Availabale commands are:
            a | add - adds a Task to a collection
            c | collection - prints the content of the selected collection
            h | help - prints help message
            q | quit - ends clir without saving
            t | task - prints all tasks of a collection
        """;
  }

}
