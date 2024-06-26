package edu.austral.ingsis.clifford;

public interface FileSystemNode {

  String getName();

  Directory getParent();

  void setParent(Directory parent);
}
