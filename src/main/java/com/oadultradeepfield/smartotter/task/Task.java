package com.oadultradeepfield.smartotter.task;

/**
 * Represents a task with a name and completion status.
 */
public class Task {
    private final String taskName;
    private boolean isDone;

    /**
     * Creates a new task with the given name, initially not done.
     *
     * @param taskName the name of the task
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Returns the name of this task.
     *
     * @return the task name
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Sets the completion status of this task.
     *
     * @param isDone {@code true} if the task is done, {@code false} otherwise
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the task,
     * showing its completion status in the format:
     * <pre>[X] TaskName</pre> or <pre>[ ] TaskName</pre>.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }
}
