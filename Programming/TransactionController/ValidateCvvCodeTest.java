package TransactionController;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.TransactionController;

class ValidateCvvCodeTest {

	TransactionController classUnderTest;

	@BeforeEach
	void setUp() throws Exception {
		classUnderTest = new TransactionController();
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
	
	public void test(String cvvCode, boolean expected) {
		assertEquals(expected, classUnderTest.validateCvvCode(cvvCode));
	}

}
