package Tests;

import org.testng.annotations.Test;

public class SearchTest extends BasicTest {
	
	@Test (priority = 1)
	public void searchResultTest() {
		this.driver.get("http://demo.yo-meals.com/");
	
	}

}
