package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationPopupPage extends BasicPage {

	public LocationPopupPage(WebDriver driver, JavascriptExecutor javascriptExecutor, WebDriverWait waiter) {
		super(driver, javascriptExecutor, waiter);

	}

	public WebElement getSelectSection() {
		return this.driver.findElement(By.className("location-selector"));
	}
	
	public WebElement getCloseBtn() {
		return this.driver.findElement(By.className("close-btn-white"));
	}
	
	public WebElement getKeyword() {
		return this.driver.findElement(By.id("locality_keyword"));
	}
	
	public WebElement getLocationItem(String locationName) {
		return this.driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]/.."));
	}
	
	public WebElement getLocationInput() {
		return this.driver.findElement(By.xpath("//*[@id='location_id']"));
	}
	
	public WebElement getSubmit() {
		return this.driver.findElement(By.xpath("//*[@name='btn_submit']"));
	}
	
	public void getLocationForm() {
		this.getSelectSection().click();
	}
	
	public void setLocation(String locationName) throws InterruptedException {
		this.getKeyword().click();
		Thread.sleep(1500);

		String dataValue = this.getLocationItem(locationName).getAttribute("data-value");
		Thread.sleep(1500);
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].value=arguments[1]", this.getLocationInput(), dataValue);
		Thread.sleep(1500);
		javascriptExecutor.executeScript("arguments[0].click();", this.getSubmit());
	}

	public void getClosePopup() {
		this.getCloseBtn().click();
	}
	

}