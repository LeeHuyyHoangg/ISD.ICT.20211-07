package org.hust.subsystem;
import org.hust.common.exception.PaymentException;
import org.hust.common.exception.UnrecognizedException;
import org.hust.entity.payment.CreditCard;
import org.hust.entity.payment.PaymentTransaction;
import org.hust.subsystem.interbank.InterbankSubsystemControl;

/**
 * This class is used to communicate with the
 * interbank to make transaction.
 * 
 */
public class InterbankSubsystem implements InterbankInterface {

  private InterbankSubsystemControl ctrl;

  public InterbankSubsystem() {
    this.ctrl = new InterbankSubsystemControl();
  }

  public PaymentTransaction makeTransaction(CreditCard card, int amount, String contents) 
      throws PaymentException, UnrecognizedException {
    PaymentTransaction transaction = ctrl.makeTransaction(card, amount, contents);
    return transaction;
  }
}
