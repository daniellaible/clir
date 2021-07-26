package de.dalai.clir.cli.command.help;

public class SubHelp {

  public void printSubHelp(String command) {
    if (command.equalsIgnoreCase("h") || command.equalsIgnoreCase("help")) {
      System.out.println(genHelpHelp());
    } else if (command.equalsIgnoreCase("q") || command.equalsIgnoreCase("quit")) {
      System.out.println(genQuitHelp());
    } else if (command.equalsIgnoreCase("a") || command.equalsIgnoreCase("add")) {
      System.out.println(genAddHelp());
    } else if (command.equalsIgnoreCase("c") || command.equalsIgnoreCase("collection")) {
      System.out.println(genCollectionHelp());
    } else if (command.equalsIgnoreCase("t") || command.equalsIgnoreCase("task")) {
      System.out.println(genTaskHelp());
    }else{

    }
  }

  private String genTaskHelp() {
    return """
        Help page for:  task|t
            t | task print all open tasks for a collection if a collection is selected
                t | task <collectionname> prints all open task for a specific collection
        """;
  }

  private String genAddHelp(){
    return """
        Help page for:  add|a
            a | add  add a new task to a topic
                a | add  <task> (in quotes) adds a new task to the current collection
                a | add  <task> <collection> adds a new task to the selected collection
        """;
  }

  private String genCollectionHelp(){
    return """
        Help page for:  collection|c
            c | collection  prints all available collections
        """;
  }

  private String genQuitHelp(){
    return """
        Help page for:  quit|q
            q | quit  ends the program without saving changes
        """;
  }

  private String genHelpHelp(){
    return """
        Help page for: help|h
            h | help  <command> prints the help message of command
        """;
  }
}
