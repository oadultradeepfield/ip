package com.oadultradeepfield.smartotter;

import com.oadultradeepfield.smartotter.util.CustomIO;

/**
 * Exception class for SmartOtter errors.
 */
public class SmartOtterException extends Exception {
    /**
     * Creates a new SmartOtterException with the given message.
     *
     * @param message error message
     */
    public SmartOtterException(String message) {
        super(message);
    }

    /**
     * Returns the formatted error message.
     *
     * @return formatted message string
     */
    @Override
    public String getMessage() {
        return CustomIO.formatError(super.getMessage());
    }
}
