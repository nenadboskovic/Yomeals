package Tests;

import org.testng.annotations.Test;

public class ProfileTest extends BasicTest {

	@Test (priority = 1)
	public void editProfileTest() {
		this.driver.get("http://demo.yo-meals.com/");
	
	}
	
	@Test (priority = 2)
	public void changeProfileImageTest() {
		this.driver.get("http://demo.yo-meals.com/");
	
	}

}
