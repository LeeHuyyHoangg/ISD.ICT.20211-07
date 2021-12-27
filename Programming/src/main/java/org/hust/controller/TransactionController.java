package org.hust.controller;

public class TransactionController extends BaseController {
    public boolean validateCvvCode(String cvvCode) {
        return true;
    }

    public boolean validateExpiredDate(String expiredMonth, String expiredYear) {
        return true;
    }
}
