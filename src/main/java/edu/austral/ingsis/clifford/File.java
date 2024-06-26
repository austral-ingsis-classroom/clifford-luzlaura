package edu.austral.ingsis.clifford;

public class File implements FileSystemNode {

  String name;
  Directory parent;

  public File(String name) {
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
}
