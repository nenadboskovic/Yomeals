package Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MealItemTest extends BasicTest {

	@Test (priority = 1)
	public void addMealToCartTest() throws InterruptedException {
		driver.get(baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
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
		locationPopupPage.closePopup();
		mealPage.setFavourite();
		Assert.assertTrue(notificationSistemPage.getMessageText().contains("Please login first!"), "Login notifcation ERROR");
		loginPage.getLoginPage();
		loginPage.login(email, password);
		driver.get(baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		mealPage.setFavourite();
		Thread.sleep(500);
		Assert.assertTrue(notificationSistemPage.getMessageText().contains("Product has been added to your favorites."), "Add meal ERROR");

	}
	
	@Test (priority = 3)
	public void clearCartTest() throws InterruptedException, IOException {
		SoftAssert sa = new SoftAssert();
		driver.get(baseUrl + "/meals");
		locationPopupPage.setLocation("City Center - Albany");
		
		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream (file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet1 = workbook.getSheet("Meals");
				
		for (int i = 1; i < 6; i++) {
			
			driver.get(sheet1.getRow(i).getCell(0).getStringCellValue());			
			double qty = sheet1.getRow(i).getCell(1).getNumericCellValue();
	        int intValue = (int) qty;
	        String stringValue = Integer.toString(intValue);
			mealPage.addMeal(stringValue);
			sa.assertTrue(notificationSistemPage.getMessageText().contains("Meal Added To Cart"), "Add meal ERROR");
		}
		
		cartSummaryPage.ClearCart();
		sa.assertAll();
		Assert.assertTrue(notificationSistemPage.getMessageText().contains("All meals removed from Cart successfully"), "Clear cart ERROR");

	}
}
