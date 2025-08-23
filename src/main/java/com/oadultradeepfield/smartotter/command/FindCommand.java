package com.oadultradeepfield.smartotter.command;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.oadultradeepfield.smartotter.task.Task;
import com.oadultradeepfield.smartotter.util.CustomIO;

/**
 * A command that finds tasks containing a given keyword (case-insensitive).
 */
public record FindCommand(String keyword) implements Executable {
    /**
     * Creates a {@code FindCommand} from user input.
     *
     * @param input the search keyword
     * @return a new {@code FindCommand} instance
     */
    public static Executable fromInput(String input) {
        return new FindCommand(input.trim());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(CommandContext context) {
        List<Task> tasks = context.findTasks(keyword);
        String result =
                IntStream.range(0, tasks.size())
                        .mapToObj(i -> (i + 1) + ". " + tasks.get(i))
                        .collect(Collectors.joining("\n"));

        CustomIO.printPretty(
                result.isEmpty()
                        ? "Hmm... I couldn't find any task containing \"%s\".".formatted(keyword)
                        : "Here are the matching tasks I found:\n" + result);
    }
}
