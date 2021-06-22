package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MealItemTest extends BasicTest {

//	@Test (priority = 1)
	public void addMealToCartTest() throws InterruptedException {
		driver.get(baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		Thread.sleep(500);
		locationPopupPage.closePopup();
		mealPage.addMeal("2");
		Assert.assertTrue(notificationSistemPage.getMessageText().contains("The Following Errors Occurred:"), "Expected notifcation ERROR");
		Assert.assertTrue(notificationSistemPage.getMessageText().contains("Please Select Location"), "Expected notifcation ERROR");
		notificationSistemPage.waitTillMssgGone();
		locationPopupPage.getLocationForm();
		locationPopupPage.setLocation("City Center - Albany");
		Thread.sleep(500);
		mealPage.addMeal("2");
		Assert.assertTrue(notificationSistemPage.getMessageText().contains("Meal Added To Cart"), "Add meal ERROR");

	}
	
	@Test (priority = 2)
	public void addMealToFavoriteTest() throws InterruptedException {
		driver.get(baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		Thread.sleep(500);
		locationPopupPage.closePopup();
		mealPage.setFavourite();
		Assert.assertTrue(notificationSistemPage.getMessageText().contains("Please login first!"), "Login notifcation ERROR");
		loginPage.getLoginPage();
		loginPage.login(email, password);
		driver.get(baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		mealPage.setFavourite();
		Assert.assertTrue(notificationSistemPage.getMessageText().contains("Product has been added to your favorites."), "Add meal ERROR");

	}
	
//	@Test (priority = 3)
	public void clearCartTest() {
		driver.get(baseUrl + "");
	
	}
}
