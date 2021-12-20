import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.PlaceOrderController;

class ValidateStationTest {

	private ViewStationController viewStationController;

	@BeforeEach
	void setUp() throws Exception {
		viewStationController = new ViewStationController();
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

	public void test(String location, boolean expected) {
		assertEquals(expected, viewStationController.validateLocation(location));
	}

}
