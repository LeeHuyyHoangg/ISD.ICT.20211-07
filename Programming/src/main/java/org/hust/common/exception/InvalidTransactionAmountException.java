package org.hust.common.exception;


/**
 * This exception is throws when receive response from interbank with the error
 * code correspond to invalid transaction amount.
 */
public class InvalidTransactionAmountException extends PaymentException {

    public InvalidTransactionAmountException() {
        super("ERROR: Invalid Transaction Amount!");
    }

}
