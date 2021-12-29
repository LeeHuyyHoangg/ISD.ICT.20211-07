package org.hust.common.exception;


/**
 * This exception is throws when receive response from interbank with the error
 * code correspond to not enough transaction info.
 * 
 */
public class NotEnoughTransactionInfoException extends PaymentException {

  public NotEnoughTransactionInfoException() {
    super("ERROR: Not Enough Transcation Information");
  }

}
