package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.*;

public interface Command {

  String execute(Directory currentDir, String argument);
}
