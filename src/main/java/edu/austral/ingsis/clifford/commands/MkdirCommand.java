package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.*;

public class MkdirCommand implements Command {
  @Override
  public String execute(Directory currentDir, String argument) {
    if (!isNameValid(argument) || dirExists(currentDir, argument)) {
      return "cannot create directory";
    }
    Directory newDir = new Directory(argument);
    currentDir.addChild(newDir);
    return "'" + argument + "' directory created";
  }

  private boolean dirExists(Directory currentDir, String argument) {
    FileSystemNode node = currentDir.getChildByName(argument);
    return node != null && node instanceof Directory;
  }

  private boolean isNameValid(String argument) {
    return argument != null && !argument.contains("/") && !argument.contains(" ");
  }
}
