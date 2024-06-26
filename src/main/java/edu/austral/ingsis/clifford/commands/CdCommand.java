package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.*;

public class CdCommand implements Command {
  private final FileSystem fileSystem;

  public CdCommand(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }

  @Override
  public String execute(Directory currentDir, String argument) {
    FileSystemNode node = getTargetNode(currentDir, argument);
    if (node instanceof Directory) {
      fileSystem.setCurrentNode(node);
      if (((Directory) node).getPath().equals("/")) {
        return "moved to directory '/'";
      } else {
        return "moved to directory '" + node.getName() + "'";
      }
    } else {
      if (argument.equals("/")) {
        return "moved to directory '/'";
      } else if (argument.equals("..")) {
        return "moved to directory '/'";

      } else {
        return "'" + argument + "' directory does not exist";
      }
    }
  }

  FileSystemNode getTargetNode(Directory currentDir, String argument) {
    FileSystemNode node = null;
    if (argument.equals("..")) {
      node = currentDir.getParent();
    } else if (argument.equals(".")) {
      node = currentDir;
    } else if (argument.startsWith("/")) {
      node = findNodeFromRoot(argument, fileSystem.getRoot());
    } else {
      node = findNode(currentDir, argument);
    }
    return node;
  }

  private FileSystemNode findNode(Directory directory, String path) {
    if (path.isEmpty()) {
      return directory;
    }
    if (path.contains("/")) {
      String[] parts = path.split("/", 2);
      FileSystemNode node = directory.getChildByName(parts[0]);
      if (node instanceof Directory) {
        return findNode((Directory) node, parts[1]);
      }
    } else {
      return directory.getChildByName(path);
    }
    return null;
  }

  private FileSystemNode findNodeFromRoot(String path, FileSystemNode root) {
    if (!(root instanceof Directory)) {
      return null;
    }
    Directory rootDir = (Directory) root;
    if (path.equals("/")) {
      return rootDir;
    }
    return findNode(rootDir, path.substring(1));
  }
}
