package com.oadultradeepfield.smartotter.comand;

import com.oadultradeepfield.smartotter.task.Task;
import com.oadultradeepfield.smartotter.utility.CustomIO;

public class AddCommand implements Command {
    private final String taskName;

    public AddCommand(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void execute(CommandContext context) {
        context.tasks().add(new Task(taskName));
        CustomIO.printPretty("added: " + taskName);
    }
}