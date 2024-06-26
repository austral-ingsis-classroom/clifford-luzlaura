package edu.austral.ingsis.clifford;

public interface FileSystem {
  FileSystemNode getRoot();

  FileSystemNode getCurrentNode();

  void setCurrentNode(FileSystemNode node);
}
