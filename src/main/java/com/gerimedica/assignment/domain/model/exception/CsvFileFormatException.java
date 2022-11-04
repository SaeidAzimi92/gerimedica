package com.gerimedica.assignment.domain.model.exception;

public class CsvFileFormatException extends Exception{
    public CsvFileFormatException() {
        super();
    }

    public CsvFileFormatException(String message) {
        super(message);
    }
}
