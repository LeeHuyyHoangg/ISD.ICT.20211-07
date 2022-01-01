package org.hust.common.exception;


/**
 * This exception is throws when receive error when making payment transaction
 * with unknown reasons.
 * 
 */
public class UnrecognizedException extends RuntimeException {

  public UnrecognizedException() {
    super("ERROR: Something went wrowng!");
  }
  
}
