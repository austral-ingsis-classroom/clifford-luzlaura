package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.*;

public class PwdCommand implements Command {

  @Override
  public String execute(Directory currentDir, String argument) {
    return getPath(currentDir);
  }

  private String getPath(FileSystemNode node) {
    if (node.getParent() == null) {
      return node.getName();
    }
    String parentPath = getPath(node.getParent());
    if (!parentPath.equals("/")) {
      parentPath += "/";
    }
    return parentPath + node.getName();
  }
}
