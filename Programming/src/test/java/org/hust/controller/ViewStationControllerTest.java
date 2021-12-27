package org.hust.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewStationControllerTest {
    private ViewStationController viewStationController;

    @BeforeEach
    void setUp() {
        viewStationController = new ViewStationController();
    }

    @ParameterizedTest
    @CsvSource({
            "jdhcbudy,true",
            "asdf154,true",
            "51864,true",
            "asfa15 54,false",
            "@a1das85!,false",
            "321564!,false",
            "          ,false",
            ",false"
    })
    public void validateIdTest(String id, boolean expected) {
        assertEquals(expected, viewStationController.validateId(id));
    }

    @ParameterizedTest
    @CsvSource({
            "jdhcbudy,true",
            "156 Dai Co Viet,true",
            "156 Dai Co Viet Hai Ba Trung Ha Noi,true",
            "@DongNai!,false",
            "tp. Ho Chi Minh,true",
            "          ,false",
            ",false"
    })
    public void validateLocationTest(String location, boolean expected) {
        assertEquals(expected, viewStationController.validateLocation(location));
    }
}
