package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.*;

public class TouchCommand implements Command {
  @Override
  public String execute(Directory currentDir, String argument) {
    if (!isNameValid(argument) || fileExists(currentDir, argument)) {
      return "cannot create file";
    }
    File newFile = new File(argument);
    currentDir.addChild(newFile);
    return "'" + argument + "' file created";
  }

  private boolean fileExists(Directory currentDir, String argument) {
    return currentDir.getChildByName(argument) != null;
  }

  private boolean isNameValid(String fileName) {
    return fileName != null && !fileName.contains("/") && !fileName.contains(" ");
  }
}
