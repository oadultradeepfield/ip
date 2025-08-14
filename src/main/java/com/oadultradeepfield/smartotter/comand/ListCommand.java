package com.oadultradeepfield.smartotter.comand;

import com.oadultradeepfield.smartotter.task.Task;
import com.oadultradeepfield.smartotter.util.CustomIO;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/** A command that lists all current tasks with their status. */
public class ListCommand implements Command {
  /**
   * Creates a {@code ListCommand} from user input. This command ignores any provided input.
   *
   * @param input unused input string
   * @return a new {@code ListCommand} instance
   * @throws IllegalArgumentException never thrown in this implementation
   */
  @SuppressWarnings("unused")
  public static Command fromInput(String input) throws IllegalArgumentException {
    return new ListCommand();
  }

  /** {@inheritDoc} */
  @Override
  public void execute(CommandContext context) {
    List<Task> tasks = context.tasks();
    String result =
        IntStream.range(0, tasks.size())
            .mapToObj(i -> (i + 1) + ". " + tasks.get(i).toString())
            .collect(Collectors.joining("\n"));
    CustomIO.printPretty(
        result.isEmpty() ? "Congratulations! You don't have any tasks at the moment." : result);
  }
}
