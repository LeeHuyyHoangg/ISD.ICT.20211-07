package org.hust.common.exception;

public class HaveNotRentBikeException extends Exception {
    public HaveNotRentBikeException() {
    }

    public HaveNotRentBikeException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return ("You haven't rented any bike! Don't hesitate to rent one!");
    }
}
