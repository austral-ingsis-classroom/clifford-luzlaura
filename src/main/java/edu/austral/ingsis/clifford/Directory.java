package edu.austral.ingsis.clifford;

import java.util.*;

public class Directory implements FileSystemNode {

  String name;
  Directory parent;
  List<FileSystemNode> children = new ArrayList<>();

  public Directory(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Directory getParent() {
    return parent;
  }

  @Override
  public void setParent(Directory parent) {
    this.parent = parent;
  }

  public void addChild(FileSystemNode node) {
    children.add(node);
    node.setParent(this);
  }

  public void removeChild(FileSystemNode node) {
    children.remove(node);
    node.setParent(null);
  }

  public List<FileSystemNode> getChildren() {
    return children;
  }

  public FileSystemNode getChildByName(String name) {
    for (FileSystemNode child : children) {
      if (child.getName().equals(name)) {
        return child;
      }
    }
    return null;
  }

  public String getPath() {
    if (parent == null) {
      return "/";
    } else {
      return parent.getPath() + name + "/";
    }
  }
}
