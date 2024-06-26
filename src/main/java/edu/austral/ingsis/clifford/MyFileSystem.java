package edu.austral.ingsis.clifford;

public class MyFileSystem implements FileSystem {

  private final Directory root;
  private FileSystemNode currentNode;

  public MyFileSystem(Directory root1, Directory root) {
    this.root = root;
    root = new Directory("/");
    currentNode = root;
  }

  @Override
  public FileSystemNode getRoot() {
    return root;
  }

  @Override
  public FileSystemNode getCurrentNode() {
    return currentNode;
  }

  @Override
  public void setCurrentNode(FileSystemNode node) {
    currentNode = node;
  }
}
