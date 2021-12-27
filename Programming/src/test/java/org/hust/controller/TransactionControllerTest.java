package org.hust.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionControllerTest {
    private TransactionController transactionController;

    @BeforeEach
    void setUp() {
        transactionController = new TransactionController();
    }

    @ParameterizedTest
    @CsvSource({
            "933,true",
            "9334,false",
            "93,false",
            "93a,false",
            "9a,false",
            "933a,false",
            ",false"
    })
    public void validateCvvCode(String cvvCode, boolean expected) {
        assertEquals(expected, transactionController.validateCvvCode(cvvCode));
    }

    @ParameterizedTest
    @CsvSource({
            "11,25,true",
            "13,25,false",
            "1a,25,false",
            "11,2b,false",
            "113,265,false",
            "11,246,false",
            "6,26,false",
            ",,false"
    })
    public void validateExpiredDate(String expiredMonth, String expiredYear, boolean expected) {
        assertEquals(expected, transactionController.validateExpiredDate(expiredMonth, expiredYear));
    }
}
