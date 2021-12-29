package org.hust.entity.payment;


import java.lang.reflect.Field;

import org.hust.utils.Utils;

/**
 * This class represent a payment transaction.
 *
 */
public class PaymentTransaction {

  private CreditCard card;
  private String transactionId;
  private String command;
  private String transactionContent;
  private int amount;
  private String createdAt;

  /**
   * Initialize a PaymentTransaction object using credit card, content, amount.

   * @param card    - the credit card used in the payment transaction
   * @param content - the transaction content
   * @param amount  - the transaction amount
   */
  public PaymentTransaction(CreditCard card, String content, int amount) {
    this.card = card;
    this.transactionContent = content;
    if (amount < 0) {
      this.command = "refund";
      this.amount = -amount;
    } else {
      this.command = "pay";
      this.amount = amount;
    }
    this.createdAt = Utils.getToday();
  }

  /**
   * Initialize a PaymentTransaction object using credit card, 
   * id, content, command, amount, created time.

   * @param card               - the credit card used in the payment transaction
   * @param transactionId      - the trasaction id
   * @param transactionContent - the transaction content
   * @param command            - the transaction command
   * @param amount             - the transaction amount
   * @param createdAt          - the transaction created time
   */
  public PaymentTransaction(CreditCard card, String transactionId, String transactionContent,
      String command, int amount, String createdAt) {
    this.card = card;
    this.transactionId = transactionId;
    this.transactionContent = transactionContent;
    this.command = command;
    this.amount = amount;
    this.createdAt = createdAt;
  }
  
  public void save() {
    System.out.println("Payment transaction saved");
  }

  /**
   * Convert part of transaction info into a JSON format in order to create request's hash code.

   * @return String represent the payment transaction info in JSON format
   */
  public String toDataForHashCode() {
    StringBuilder sb = new StringBuilder();
    sb.append("\"transaction\":{");
    try {
      sb.append("\"command\":\"" + command + "\",");
      for (Field field : card.getClass().getDeclaredFields()) {
        field.setAccessible(true);
        Object value = field.get(card);
        sb.append('"' + field.getName() + '"' + ':' + '"' + value.toString() + '"' + ',');
        field.setAccessible(false);
      }
      sb.append("\"transactionContent\":\"" + transactionContent + "\",");
      sb.append("\"amount\":\"" + amount + "\"");
    } catch (Exception e) {
      e.printStackTrace();
    }
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert all transaction info into a JSON format in order to create request's hash code.

   * @return String represent the payment transaction info in JSON format
   */
  public String toData() {
    StringBuilder sb = new StringBuilder();
    sb.append(toDataForHashCode());
    sb.deleteCharAt(sb.length() - 1);
    sb.append(",\"createdAt\":\"" + createdAt + "\"}");
    return sb.toString();
  }
  
  public String getId() {
    return transactionId;
  }
  
  public int getAmount() {
    return amount;
  }
  
  public String getOwner() {
    return card.getOwner();
  }
  
  public String getTime() {
    return createdAt;
  }
  
  public String getContent() {
    return transactionContent;
  }
}