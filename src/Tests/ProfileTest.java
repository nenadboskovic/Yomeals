package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BasicTest {

	@Test (priority = 1)
	public void editProfileTest() throws InterruptedException {
		driver.get(baseUrl + "/guest-user/login-form");
		Thread.sleep(500);
		locationPopupPage.closePopup();
		loginPage.login(email, password);
		Assert.assertTrue(notificationSistemPage.getMessageText().contains("Login Successfull"), "Login ERROR");
		driver.get(baseUrl + "/member/profile");
		profilePage.ChangePersonalInfo("Will", "Smith", "Brenica bb", "+1111111111", "18000", "India", "Delhi", "New Delhi");
		Thread.sleep(500);
		Assert.assertTrue(notificationSistemPage.getMessageText().contains("Setup Successful"), "Setup ERROR");
		authPage.logout();
		Assert.assertTrue(notificationSistemPage.getMessageText().contains("Logout Successfull!"), "Logout ERROR");

	
	}
	
//	@Test (priority = 2)
	public void changeProfileImageTest() {
		this.driver.get("http://demo.yo-meals.com/");
	
	}

}
