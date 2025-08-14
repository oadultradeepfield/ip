package com.oadultradeepfield.smartotter.comand;

import com.oadultradeepfield.smartotter.task.Task;
import java.util.List;

/**
 * Immutable context passed to commands, containing the current task list.
 *
 * @param tasks the list of tasks
 */
public record CommandContext(List<Task> tasks) {}
