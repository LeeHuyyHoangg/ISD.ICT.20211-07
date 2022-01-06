package org.hust.common.exception;

public class InvalidLocationException extends Exception {
    @Override
    public String getMessage() {
        return "Nothing found in that location";
    }
}
