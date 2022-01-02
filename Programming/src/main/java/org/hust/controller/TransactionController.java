package org.hust.controller;
import org.hust.common.exception.InvalidFormatException;
import org.hust.common.exception.PaymentException;
import org.hust.common.exception.UnrecognizedException;
import org.hust.entity.payment.CreditCard;
import org.hust.entity.payment.PaymentTransaction;
import org.hust.subsystem.InterbankInterface;
import org.hust.subsystem.InterbankSubsystem;

/**
 * This class is used to control the pay order process.
 *
 */
public class TransactionController extends BaseController {
	
  private int transactionAmount;
  private String transactionContents;
  
  private InterbankInterface interbank = new InterbankSubsystem();

  public TransactionController() {

  }

  /**
   * Make payment transaction.

   * @param cardCode     - the credit card's code
   * @param cardOwner    - the credit card's owner
   * @param cardCvv      - the credit card's security code
   * @param expiredDate  - the credit card's expired date
   * @return payment transaction if success
   * @throws PaymentException      - if transaction fail with known reasons
   * @throws UnrecognizedException - if transaction fail with unknown reasons
   */
  public PaymentTransaction makeTransaction(String cardCode, String cardOwner,
      String cardCvv, String expiredDate)
      throws PaymentException, UnrecognizedException {
    CreditCard card = new CreditCard(cardCode, cardOwner, cardCvv, expiredDate);
    return interbank.makeTransaction(card, transactionAmount, transactionContents);
  }
  
  /**
   * Check the format of the credit card info user have input.

   * @param cardCode     - the credit card's code
   * @param cardOwner    - the credit card's owner
   * @param cardCvv      - the credit card's security code
   * @param expiredDate  - the credit card's expired date
   * @throws InvalidFormatException - if the credit card info is in an invalid format
   */
  public void checkTransactionInfo(String cardCode, String cardOwner,
      String cardCvv, String expiredDate) throws InvalidFormatException {
    if (!validateCvvCode(cardCvv)) {
      throw new InvalidFormatException(
          "Invalid card cvv format! Card cvv must contains exactly 3 digits");
    }
    if (!validateExpiredDate(expiredDate)) {
      throw new InvalidFormatException(
          "Invalid expired date format!");
    }
  }
  
  /**
   * Validate the cvv code format of a credit card.

   * @param cardCvv - the credit card's security code
   * @return true  - if the info is in correct format
   *         false - if the info is in an invalid format
   */
  public boolean validateCvvCode(String cardCvv) {
    if (cardCvv == null) {
      return false;
    }
    cardCvv = cardCvv.trim();
    if (cardCvv.length() != 3) {
      return false;
    }
    try {
      Integer.parseInt(cardCvv);
    } catch (Exception e) {
      return false;
    }
    return true;
  }
  
  /**
   * Validate the expired date of a credit card.

   * @param expiredDate - the credit card's expired date
   * @return true  - if the info is in correct format
   *         false - if the info is in an invalid format
   */
  public boolean validateExpiredDate(String expiredDate) {
    if (expiredDate == null) {
      return false;
    }
    expiredDate = expiredDate.trim();
    if (expiredDate.length() != 4) {
      return false;
    }
    try {
      int expiredMonth = Integer.parseInt(expiredDate) / 100;
      if (expiredMonth < 1 || expiredMonth > 12) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
    return true;
  }
  
  public void setTransactionAmount(int amount) {
	  this.transactionAmount = amount;
  }
  
  public void setTransactionContents(String contents) {
	  this.transactionContents = contents;
  }

}
