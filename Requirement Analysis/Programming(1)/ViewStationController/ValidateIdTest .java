import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.PlaceOrderController;

class ValidateIdTest {

	private ViewStationController viewStationController;

	@BeforeEach
	void setUp() throws Exception {
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

	public void test(String id, boolean expected) {
		assertEquals(expected, viewStationController.validateId(id));
	}

}
