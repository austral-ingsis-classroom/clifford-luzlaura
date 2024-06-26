package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.*;
import java.util.*;

public class LsCommand implements Command {
  @Override
  public String execute(Directory currentDir, String argument) {
    List<FileSystemNode> children = currentDir.getChildren();

    if (argument != null && !argument.isEmpty()) {
      String[] args = argument.split("=");
      if (args.length == 2 && args[0].equals("--ord")) {
        children = sortChildren(children, args[1]);
      }
    }

    StringBuilder result = new StringBuilder();
    for (int i = 0; i < children.size(); i++) {
      result.append(children.get(i).getName());
      if (i < children.size() - 1) {
        result.append(" ");
      }
    }
    return result.toString();
  }

  private List<FileSystemNode> sortChildren(List<FileSystemNode> children, String argument) {
    if (argument.equals("asc")) {
      children.sort(Comparator.comparing(FileSystemNode::getName));
    } else if (argument.equals("desc")) {
      children.sort(Comparator.comparing(FileSystemNode::getName).reversed());
    }
    return children;
  }
}
