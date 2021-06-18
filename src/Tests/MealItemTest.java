package Tests;

import org.testng.annotations.Test;

public class MealItemTest extends BasicTest {

	@Test (priority = 1)
	public void addMealToCartTest() {
		this.driver.get("http://demo.yo-meals.com/");
	
	}
	
	@Test (priority = 2)
	public void addMealToFavoriteTest() {
		this.driver.get("http://demo.yo-meals.com/");
	
	}
	
	@Test (priority = 3)
	public void clearCartTest() {
		this.driver.get("http://demo.yo-meals.com/");
	
	}
}
