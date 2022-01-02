package org.hust.exception;

public class HaveNotRentBikeException extends Exception{
    @Override
    public String getMessage(){
        return ("User have not yet rent a bike");
    }
}
