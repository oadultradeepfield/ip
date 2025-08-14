package com.oadultradeepfield.smartotter.comand;

import com.oadultradeepfield.smartotter.task.Task;

import java.util.List;

public record CommandContext(List<Task> tasks) {
}
