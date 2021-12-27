package org.hust.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RentBikeControllerTest {
    private RentBikeController rentBikeController;

    @BeforeEach
    void setUp() {
        rentBikeController = new RentBikeController();
    }

    @ParameterizedTest
    @CsvSource({
            "12345678,true",
            "123456789,false",
            "1234567,false",
            "12ab5678,false",
            "12ab567,false",
            "12ab56789,false",
            ",false"
    })
    public void validateBarcode(String barcode, boolean expected) {
        assertEquals(expected, rentBikeController.validateBarcode(barcode));
    }
}