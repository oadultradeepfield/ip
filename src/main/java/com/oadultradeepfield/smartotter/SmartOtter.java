package com.oadultradeepfield.smartotter;

import com.oadultradeepfield.smartotter.command.ByeCommand;
import com.oadultradeepfield.smartotter.command.CommandContext;
import com.oadultradeepfield.smartotter.command.CommandParser;
import com.oadultradeepfield.smartotter.command.Executable;
import com.oadultradeepfield.smartotter.util.FileManager;

/** Main class for the SmartOtter interactive console application. */
public class SmartOtter {
  private final SmartOtterUI ui;
  private final FileManager fileManager;
  private final CommandParser parser;
  private final CommandContext context;

  public SmartOtter(String filePath) {
    this.ui = new SmartOtterUI();
    this.fileManager = new FileManager(filePath);
    this.parser = new CommandParser();
    this.context = new CommandContext(this.fileManager.readTasksFromFile());

    // Add shutdown hook to save tasks
    Runtime.getRuntime()
        .addShutdownHook(new Thread(() -> this.fileManager.saveTasksToFile(context.tasks())));
  }

  /** Entry point. Runs interactive loop until user exits. */
  public static void main(String[] args) {
    new SmartOtter(SmartOtterConstant.SAVE_PATH).run();
  }

  public void run() {
    ui.showGreeting();

    while (true) {
      try {
        String input = ui.readCommand();
        Executable executable = parser.parse(input);
        executable.execute(context);

        if (executable instanceof ByeCommand) {
          break;
        }
      } catch (Exception e) {
        ui.showError(e.getMessage());
      }
    }
  }
}
