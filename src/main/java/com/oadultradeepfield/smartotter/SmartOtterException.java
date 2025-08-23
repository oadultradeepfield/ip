package com.oadultradeepfield.smartotter;

import com.oadultradeepfield.smartotter.util.CustomIO;

public class SmartOtterException extends Exception {
    public SmartOtterException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return CustomIO.formatError(super.getMessage());
    }
}
