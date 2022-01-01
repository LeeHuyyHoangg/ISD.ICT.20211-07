package org.hust.common.exception;

public class InvalidLocationFormatException extends Exception{
    @Override
    public String getMessage(){
        return "String include invalid special characters";
    }
}
