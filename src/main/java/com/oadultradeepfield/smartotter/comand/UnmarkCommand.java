package com.oadultradeepfield.smartotter.comand;

import com.oadultradeepfield.smartotter.task.Task;
import com.oadultradeepfield.smartotter.utility.CustomIO;

import java.util.List;

/**
 * Marks a specified task as not done.
 */
public class UnmarkCommand implements Command {
    private final int taskNumber;

    /**
     * Creates an {@code UnmarkCommand} for the given task number.
     *
     * @param taskNumber the 1-based index of the task to unmark
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Parses input to create an {@code UnmarkCommand}.
     *
     * @param input string representing the 1-based task number
     * @return a new {@code UnmarkCommand} instance
     * @throws IllegalArgumentException if the input is not a valid integer
     */
    public static Command fromInput(String input) throws IllegalArgumentException {
        try {
            int num = Integer.parseInt(input);
            return new UnmarkCommand(num);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid task number: " + input);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(CommandContext context) {
        List<Task> tasks = context.tasks();
        if (taskNumber >= 1 && taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);
            task.setDone(false);
            CustomIO.printPretty("Understand! I have marked '" + task.getTaskName() + "' as not done.");
        } else {
            CustomIO.printError("There is no task with number" + taskNumber);
        }
    }
}
