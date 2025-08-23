package com.oadultradeepfield.smartotter.command;

import com.oadultradeepfield.smartotter.task.Task;
import com.oadultradeepfield.smartotter.util.CustomIO;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/** A command that lists all tasks scheduled for today. */
public class TodayCommand implements Executable {

  /**
   * Creates a {@code TodayCommand} from user input. This command ignores any provided input.
   *
   * @param input unused input string
   * @return a new {@code TodayCommand} instance
   */
  @SuppressWarnings("unused")
  public static Executable fromInput(String input) {
    return new TodayCommand();
  }

  /** {@inheritDoc} */
  @Override
  public void execute(CommandContext context) {
    List<Task> tasks = context.listToday();
    String result =
        IntStream.range(0, tasks.size())
            .mapToObj(i -> (i + 1) + ". " + tasks.get(i))
            .collect(Collectors.joining("\n"));

    CustomIO.printPretty(
        result.isEmpty()
            ? "Congratulations! You don't have any tasks scheduled for today."
            : result);
  }
}
