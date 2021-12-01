package RentBikeController;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.RentBikeController;

class ValidateBarcodeTest {
	
	RentBikeController classUnderTest;

	@BeforeEach
	void setUp() throws Exception {
		classUnderTest = new RentBikeController();
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
	
	public void test(String barcode, boolean expected) {
		assertEquals(expected, classUnderTest.validateBarcode(barcode));
	}

}
