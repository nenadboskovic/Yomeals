package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasicPage {

	protected WebDriver driver;
	protected JavascriptExecutor javascriptExecutor;
	protected WebDriverWait waiter;
	
	public BasicPage(WebDriver driver, JavascriptExecutor javascriptExecutor, WebDriverWait waiter) {
		this.driver = driver;
		this.javascriptExecutor = javascriptExecutor;
		this.waiter = waiter;
	}
	
	public boolean elementExist(By by) {
		boolean exist = true;
		try {
			driver.findElement(by);
		} catch( Exception e) {
			exist = false;
		}
		return exist;
	}
}