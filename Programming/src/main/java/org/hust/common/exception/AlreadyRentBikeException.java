package org.hust.common.exception;

public class AlreadyRentBikeException extends Exception {

  public AlreadyRentBikeException() {
    super("ERROR: You are already renting a bike!");
  }

}