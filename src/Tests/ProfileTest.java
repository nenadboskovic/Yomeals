package Tests;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BasicTest {

	@Test (priority = 1)
	public void editProfileTest() throws InterruptedException {
		driver.get(baseUrl + "/guest-user/login-form");
		locationPopupPage.closePopup();
		loginPage.login(email, password);
		Assert.assertTrue(notificationSistemPage.getMessageText().contains("Login Successfull"), "Login ERROR");
		
		driver.get(baseUrl + "/member/profile");
		profilePage.ChangePersonalInfo("Will", "Smith", "Brenica bb", "+111111111", "18000", "India", "Delhi", "New Delhi");
		Thread.sleep(500);
		Assert.assertTrue(notificationSistemPage.getMessageText().contains("Setup Successful"), "Setup ERROR");
		
		authPage.logout();
		Assert.assertTrue(notificationSistemPage.getMessageText().contains("Logout Successfull!"), "Logout ERROR");

	}
	
	@Test (priority = 2)
	public void changeProfileImageTest() throws InterruptedException, IOException {
		this.driver.get(baseUrl + "/guest-user/login-form");
		locationPopupPage.closePopup();
		loginPage.login(email, password);
		Assert.assertTrue(notificationSistemPage.getMessageText().contains("Login Successfull"), "Login ERROR");
		
		driver.get(baseUrl + "/member/profile");
		String imgPath = new File("img/Pires.png").getCanonicalPath();
		profilePage.UploadProfilePic(imgPath);
		Thread.sleep(500);
		Assert.assertTrue(notificationSistemPage.getMessageText().contains("Profile Image Uploaded Successfully"), "Profile picture upload ERROR");
		
		notificationSistemPage.waitTillMssgGone();
		profilePage.RemoveProfilePic();
		Assert.assertTrue(notificationSistemPage.getMessageText().contains("Profile Image Deleted Successfully"), "Profile picture upload ERROR");
		
		notificationSistemPage.waitTillMssgGone();
		authPage.logout();
		Assert.assertTrue(notificationSistemPage.getMessageText().contains("Logout Successfull!"), "Logout ERROR");
		
	}

}
