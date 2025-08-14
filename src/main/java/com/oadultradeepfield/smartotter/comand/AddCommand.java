package com.oadultradeepfield.smartotter.comand;

import com.oadultradeepfield.smartotter.task.Task;
import com.oadultradeepfield.smartotter.utility.CustomIO;

/**
 * A command that adds a new task to the task list.
 */
public class AddCommand implements Command {
    private final String taskName;

    /**
     * Creates an {@code AddCommand} with the specified task name.
     *
     * @param taskName the name of the task to add
     */
    public AddCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(CommandContext context) {
        context.tasks().add(new Task(taskName));
        CustomIO.printPretty("added: " + taskName);
    }
}
