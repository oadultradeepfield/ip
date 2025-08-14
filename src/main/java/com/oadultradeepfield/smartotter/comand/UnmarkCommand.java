package com.oadultradeepfield.smartotter.comand;

import com.oadultradeepfield.smartotter.task.Task;
import com.oadultradeepfield.smartotter.utility.CustomIO;

import java.util.List;

public class UnmarkCommand implements Command {
    private final int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public static Command fromInput(String input) throws IllegalArgumentException {
        try {
            int num = Integer.parseInt(input);
            return new UnmarkCommand(num);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid task number: " + input);
        }
    }

    @Override
    public void execute(CommandContext context) {
        List<Task> tasks = context.tasks();
        if (taskNumber >= 1 && taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);
            task.setDone(false);
            CustomIO.printPretty("Great work! I have marked " + task.getTaskName() + " as done.");
        } else {
            CustomIO.printError("Invalid task number");
        }
    }
}
