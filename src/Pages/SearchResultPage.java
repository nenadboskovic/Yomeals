package Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage extends BasicPage{

	public SearchResultPage(WebDriver driver, JavascriptExecutor javascriptExecutor, WebDriverWait waiter) {
		super(driver, javascriptExecutor, waiter);

	}

	public List<WebElement> getSearchResults() {
//		List<WebElement> resultLinks = driver.findElements(By.xpath("//*[@class='featured-img']/a"));
		List<WebElement> resultLinks = driver.findElements(By.xpath("//*[@class='product-name']/a"));

		return resultLinks;
	}
	
	public ArrayList<String> getSearchResultsText() {
		ArrayList<String> textList = new ArrayList<String>();
		for (int i = 0; i < this.getSearchResults().size(); i++) {
			textList.add(this.getSearchResults().get(i).getText());                // getAttribute("href"));
	//		System.out.println(getSearchResults().get(i).getAttribute("href"));
		}
		return textList;
	}
	
	public int getSearchResultsNum() {
		return this.getSearchResults().size();
	}
	
}
