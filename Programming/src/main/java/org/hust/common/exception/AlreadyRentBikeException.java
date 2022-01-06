package org.hust.common.exception;

public class AlreadyRentBikeException extends Exception {

    public AlreadyRentBikeException(String msg) {
        super("ERROR: " + msg + "!");
    }

}