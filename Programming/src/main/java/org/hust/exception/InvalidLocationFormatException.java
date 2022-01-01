package org.hust.exception;

public class InvalidLocationFormatException extends Exception{
    @Override
    public String getMessage(){
        return "String include invalid special characters";
    }
}
