package org.hust.common.exception;

public class InvalidIdFormatException extends Exception {
    @Override
    public String getMessage() {
        return "ID contain special characters";
    }
}
