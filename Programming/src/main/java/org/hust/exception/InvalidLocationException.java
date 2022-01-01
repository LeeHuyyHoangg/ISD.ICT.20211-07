package org.hust.exception;

public class InvalidLocationException extends Exception{
    @Override
    public String getMessage(){
        return "Nothing found in that location";
    }
}
