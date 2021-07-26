package de.dalai.clir.cli.command.help;

import java.util.List;

public class Help {

  public Help(List<String> commands){
    helpFunctionality(commands);
  }

  private void helpFunctionality(List<String> commands) {
    if(commands.size() == 1) {
      MainHelp helper = new MainHelp();
      helper.printMainHelp();
    }else if(commands.size() == 2){
      SubHelp subHelper = new SubHelp();
      subHelper.printSubHelp(commands.get(1));
    }
  }
}
