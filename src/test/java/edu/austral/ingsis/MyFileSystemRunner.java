package edu.austral.ingsis;

import edu.austral.ingsis.clifford.*;
import edu.austral.ingsis.clifford.commands.*;
import java.util.*;

public class MyFileSystemRunner implements FileSystemRunner {
  private final FileSystem fileSystem;
  private final Map<String, Command> commandMap;

  public MyFileSystemRunner(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
    this.commandMap = createCommands(fileSystem);
  }

  @Override
  public List<String> executeCommands(List<String> commands) {
    List<String> results = new ArrayList<>();
    for (String command : commands) {
      String[] parts = command.split(" ", 2); // Split command and argument
      String cmd = parts[0];
      String arg = parts.length > 1 ? parts[1] : null;

      Command commandInstance = commandMap.get(cmd);
      if (commandInstance == null) {
        results.add("Unknown command: " + cmd);
      } else {
        Directory currentDir = (Directory) fileSystem.getCurrentNode();
        results.add(commandInstance.execute(currentDir, arg));
      }
    }
    return results;
  }

  public HashMap<String, Command> createCommands(FileSystem fileSystem) {
    HashMap<String, Command> commands = new HashMap<>();
    commands.put("ls", new LsCommand());
    commands.put("cd", new CdCommand(fileSystem));
    commands.put("touch", new TouchCommand());
    commands.put("mkdir", new MkdirCommand());
    commands.put("rm", new RmCommand());
    commands.put("pwd", new PwdCommand());
    return commands;
  }
}
