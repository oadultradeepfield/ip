package com.oadultradeepfield.smartotter.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

class DateParserTest {
    @Test
    void parse_shouldReturnDateTime_whenInputHasTime() {
        Optional<LocalDateTime> result = DateParser.parse("2/12/2025 1830");
        assertTrue(result.isPresent());
        LocalDateTime dt = result.get();
        assertEquals(2025, dt.getYear());
        assertEquals(2, dt.getMonthValue());
        assertEquals(12, dt.getDayOfMonth());
        assertEquals(18, dt.getHour());
        assertEquals(30, dt.getMinute());
    }

    @Test
    void parse_shouldReturnStartOfDay_whenInputHasOnlyDate() {
        Optional<LocalDateTime> result = DateParser.parse("2/12/2025");
        assertTrue(result.isPresent());
        LocalDateTime dt = result.get();
        assertEquals(2025, dt.getYear());
        assertEquals(2, dt.getMonthValue());
        assertEquals(12, dt.getDayOfMonth());
        assertEquals(0, dt.getHour());
        assertEquals(0, dt.getMinute());
    }

    @Test
    void parse_shouldReturnEmpty_whenInputIsInvalid() {
        Optional<LocalDateTime> result = DateParser.parse("invalid date");
        assertTrue(result.isEmpty());
    }

    @Test
    void format_shouldReturnReadableDate_whenTimeIsZero() {
        LocalDateTime dt = LocalDateTime.of(2025, 2, 12, 0, 0);
        String formatted = DateParser.format(dt);
        assertEquals("Feb 12, 2025", formatted);
    }

    @Test
    void format_shouldReturnReadableDateTime_whenTimeIsNonZero() {
        LocalDateTime dt = LocalDateTime.of(2025, 2, 12, 18, 30);
        String formatted = DateParser.format(dt);
        assertEquals("Feb 12, 2025 18:30", formatted);
    }

    @Test
    void formatForLine_shouldReturnInputStyleDate_whenTimeIsZero() {
        LocalDateTime dt = LocalDateTime.of(2025, 2, 12, 0, 0);
        String formatted = DateParser.formatForLine(dt);
        assertEquals("2/12/2025", formatted);
    }

    @Test
    void formatForLine_shouldReturnInputStyleDateTime_whenTimeIsNonZero() {
        LocalDateTime dt = LocalDateTime.of(2025, 2, 12, 18, 30);
        String formatted = DateParser.formatForLine(dt);
        assertEquals("2/12/2025 1830", formatted);
    }
}
