package org.hust.common.exception;

public class HaveNotRentBikeException extends Exception{
    @Override
    public String getMessage(){
        return ("User have not yet rent a bike");
    }
}
