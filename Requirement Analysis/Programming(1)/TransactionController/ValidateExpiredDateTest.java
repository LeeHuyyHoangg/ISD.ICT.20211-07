package TransactionController;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.TransactionController;

class ValidateExpiredDateTest {

	TransactionController classUnderTest;

	@BeforeEach
	void setUp() throws Exception {
		classUnderTest = new TransactionController();
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
	
	public void test(String expiredMonth, String expiredYear, boolean expected) {
		assertEquals(expected, classUnderTest.validateExpiredDate(expiredMonth, expiredYear));
	}
}
