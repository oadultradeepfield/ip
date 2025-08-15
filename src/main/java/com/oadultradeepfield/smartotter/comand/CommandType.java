package com.oadultradeepfield.smartotter.comand;

import java.util.Arrays;

public enum CommandType {
  TODO("todo", AddTodoCommand::fromInput),
  DEADLINE("deadline", AddDeadlineTaskCommand::fromInput),
  EVENT("event", AddEventTaskCommand::fromInput),
  LIST("list", ListCommand::fromInput),
  BYE("bye", ByeCommand::fromInput),
  MARK("mark", MarkCommand::fromInput),
  UNMARK("unmark", UnmarkCommand::fromInput),
  DELETE("delete", DeleteCommand::fromInput);

  private final String keyword;
  private final CommandFactory factory;

  CommandType(String keyword, CommandFactory factory) {
    this.keyword = keyword;
    this.factory = factory;
  }

  public static CommandType fromKeyword(String keyword) {
    return Arrays.stream(values())
        .filter(cmd -> cmd.keyword.equalsIgnoreCase(keyword))
        .findFirst()
        .orElse(null);
  }

  public Executable create(String input) {
    return factory.create(input);
  }

  @FunctionalInterface
  private interface CommandFactory {
    Executable create(String input) throws IllegalArgumentException;
  }
}
