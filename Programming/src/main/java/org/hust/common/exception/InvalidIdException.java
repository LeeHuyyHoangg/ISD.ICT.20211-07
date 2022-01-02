package org.hust.common.exception;

public class InvalidIdException extends Exception{
    @Override
    public String getMessage(){
        return ("ID not found");
    }
}
