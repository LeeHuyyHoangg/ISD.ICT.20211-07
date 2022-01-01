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
    		"0623,true",
            "1125,true",
            "1325,false",
            "1a25,false",
            "112b,false",
            "113265,false",
            "11246,false",
            "626,false",
            ",false"
    })
    public void validateExpiredDate(String expiredDate, boolean expected) {
        assertEquals(expected, transactionController.validateExpiredDate(expiredDate));
    }
}
