package TransactionController;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ValidateCvvCodeTest.class, ValidateExpiredDateTest.class})
public class AllTests {

}
