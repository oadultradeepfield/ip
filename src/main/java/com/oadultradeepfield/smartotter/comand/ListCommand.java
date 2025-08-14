package com.oadultradeepfield.smartotter.comand;

import com.oadultradeepfield.smartotter.task.Task;
import com.oadultradeepfield.smartotter.utility.CustomIO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListCommand implements Command {
    @SuppressWarnings("unused")
    public static Command fromInput(String input) throws IllegalArgumentException {
        return new ListCommand();
    }

    @Override
    public void execute(CommandContext context) {
        List<Task> tasks = context.tasks();
        String result = IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + ". " + tasks.get(i).toString())
                .collect(Collectors.joining("\n"));
        CustomIO.printPretty(result.isEmpty() ? "Congratulations! You don't any tasks at the moment." : result);
    }
}