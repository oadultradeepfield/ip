package com.oadultradeepfield.smartotter.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.oadultradeepfield.smartotter.SmartOtterException;

class TaskParserTest {
    @Test
    void parse_validTodoWithoutDates_returnsToDoTask() throws Exception {
        String line = "T | 0 | Read book";
        Optional<Task> opt = TaskParser.parse(line);
        assertTrue(opt.isPresent());
        Task t = opt.get();
        assertInstanceOf(ToDoTask.class, t);
        assertEquals("Read book", t.getTaskName());
        assertFalse(t.isDone);
    }

    @Test
    void parse_validDeadlineWithDate_returnsDeadlineTask() throws Exception {
        // Input format matches DateParser expectations (e.g. "d/M/yyyy HHmm")
        String line = "D | 1 | Finish report | 2/12/2019 1800";
        Optional<Task> opt = TaskParser.parse(line);

        assertTrue(opt.isPresent());
        Task t = opt.get();
        assertInstanceOf(DeadlineTask.class, t);
        assertEquals("Finish report", t.getTaskName());
        assertTrue(t.isDone);
    }

    @Test
    void parse_validEventWithDates_returnsEventTask() throws Exception {
        String line = "E | 0 | Team meeting | 2/12/2019 0900 | 2/12/2019 1700";
        Optional<Task> opt = TaskParser.parse(line);

        assertTrue(opt.isPresent());
        Task t = opt.get();
        assertInstanceOf(EventTask.class, t);
        assertEquals("Team meeting", t.getTaskName());
        assertFalse(t.isDone);
    }

    @Test
    void parse_invalidParts_throwsException() {
        assertThrows(SmartOtterException.class,
                () -> TaskParser.parse("OnlyTwoParts | 0"));
    }

    @Test
    void parse_invalidStatusNumber_throwsException() {
        assertThrows(SmartOtterException.class,
                () -> TaskParser.parse("T | notNumber | Name"));
    }

    @Test
    void parse_invalidStatusValue_throwsException() {
        assertThrows(SmartOtterException.class,
                () -> TaskParser.parse("T | 2 | Name"));
    }

    @Test
    void parse_invalidDateFormat_throwsException() {
        assertThrows(SmartOtterException.class,
                () -> TaskParser.parse("D | 0 | Name | bad-date"));
    }

    @Test
    void parse_eventEndBeforeStart_throwsException() {
        assertThrows(SmartOtterException.class,
                () -> TaskParser.parse("E | 0 | Name | 2/12/2019 1800 | 2/12/2019 1200"));
    }
}
