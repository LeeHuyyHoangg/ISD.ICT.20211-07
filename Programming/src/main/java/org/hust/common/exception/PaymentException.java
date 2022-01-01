package org.hust.common.exception;


/**
 * This exception is throws when receive error when making payment transaction
 * with known reasons.
 * 
 */
public class PaymentException extends Exception {

  public PaymentException(String message) {
    super(message);
  }

}
