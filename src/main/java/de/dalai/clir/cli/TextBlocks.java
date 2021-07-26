package de.dalai.clir.cli;

public class TextBlocks {

  public static final String NO_COLLECTION_SELECTED_TASK = """
               There is no collection selected
               please select the current collection
               or use     task <collection>
               to display all available collections use    c | collection
               """;

  public static final String NO_COLLECTION_SELECTED_ADD = """ 
          There is no collection selected, either:
          - you select the current collection
          - or you add this task using     add <task> <collection> 
          """;

  public static final String UNKOWN_COMMAND = """
           Sorry, this command I don't know
           Please press h to get a list of all commands
           """;
  
}
