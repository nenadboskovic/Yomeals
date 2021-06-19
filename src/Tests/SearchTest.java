package Tests;

import org.testng.annotations.Test;

public class SearchTest extends BasicTest {
	
	@Test (priority = 1)
//	public void searchResultTest() {
//		this.driver.get("http://demo.yo-meals.com/");
//	
//	}
	
	public void searchResultTest() throws InterruptedException {
		this.driver.get(baseUrl + "/guest-user/login-form");
		Thread.sleep(500);
		locationPopupPage.closePopup();
		loginPage.login(email, password);
	}

}
