package Tests;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import Pages.AuthPage;
import Pages.CartSummaryPage;
import Pages.LocationPopupPage;
import Pages.LoginPage;
import Pages.MealPage;
import Pages.NotificationSistemPage;
import Pages.ProfilePage;
import Pages.SearchResultPage;



public abstract class BasicTest {
	
	protected WebDriver driver;
	protected JavascriptExecutor javascriptExecutor;
	protected WebDriverWait waiter;
	
	protected String baseUrl;
	protected String email;
	protected String password;
	
	protected AuthPage authPage;
	protected CartSummaryPage cartSummaryPage;
	protected LocationPopupPage locationPopupPage;
	protected LoginPage loginPage;
	protected MealPage mealPage;
	protected NotificationSistemPage notificationSistemPage;
	protected ProfilePage profilePage;
	protected SearchResultPage searchResultPage;
	
	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		driver = new ChromeDriver();
		javascriptExecutor = (JavascriptExecutor) driver;
		waiter = new WebDriverWait(driver, 5, 500);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
		authPage = new AuthPage(driver, javascriptExecutor, waiter);
		cartSummaryPage = new CartSummaryPage(driver, javascriptExecutor, waiter);	
		locationPopupPage = new LocationPopupPage(driver, javascriptExecutor, waiter);
		loginPage = new LoginPage(driver, javascriptExecutor, waiter);
		mealPage = new MealPage(driver, javascriptExecutor, waiter);
		notificationSistemPage = new NotificationSistemPage(driver, javascriptExecutor, waiter);
		profilePage = new ProfilePage(driver, javascriptExecutor, waiter);
		searchResultPage = new SearchResultPage(driver, javascriptExecutor, waiter);

		this.email = "customer@dummyid.com";
		this.password = "12345678a";
		this.baseUrl = "http://demo.yo-meals.com/";

	}
	
	@AfterMethod
	public void cleanup(ITestResult result) throws InterruptedException, IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
			LocalDateTime now = LocalDateTime.now();
			String time = dtf.format(now);
			
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(scrFile, new File("./screenshots/" + time + ".png"));
	//		FileUtils.copyFile(scrFile, new File("./screenshots/" + time + ".png"));
		}
		
		Thread.sleep(2500);
		this.driver.quit();
	}
}
