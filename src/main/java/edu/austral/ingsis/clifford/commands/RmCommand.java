package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.*;

public class RmCommand implements Command {

  @Override
  public String execute(Directory currentDir, String argument) {
    boolean isRecursive = false;
    String targetName = argument;

    if (argument != null && argument.startsWith("--recursive ")) {
      isRecursive = true;
      targetName = argument.substring("--recursive ".length());
    }
    FileSystemNode node = currentDir.getChildByName(targetName);

    if (node == null) {
      return "no such file";
    }
    if (node instanceof File) {
      currentDir.removeChild(node);
      return "'" + node.getName() + "' removed";
    } else if (node instanceof Directory) {
      return checkRecursive(currentDir, targetName, node, isRecursive);
    } else {
      return "unknown node type";
    }
  }

  private String checkRecursive(
      Directory currentDir, String argument, FileSystemNode node, Boolean isRecursive) {
    if (isRecursive) {
      currentDir.removeChild(node);
      return "'" + argument + "' removed";
    } else {
      return "cannot remove '" + argument + "', is a directory";
    }
  }
}
