package org.hust.common.exception;


/**
 * This exception is throws when user input an invalid barcode.
 * 
 */
public class InvalidBarcodeException extends Exception {

  public InvalidBarcodeException() {
    super("ERROR: Invalid barcode!");
  }
  
}
