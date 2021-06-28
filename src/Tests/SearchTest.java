package Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SearchTest extends BasicTest {
	
	@Test
	public void searchResultTest() throws InterruptedException, IOException {
		driver.get(baseUrl + "/meals");
		locationPopupPage.setLocation("City Center - Albany");
		
		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream (file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet1 = workbook.getSheet("Meal Search Results");
		
		for (int i = 1; i < 7; i++) {
			
			Thread.sleep(500);
			driver.get(sheet1.getRow(i).getCell(1).getStringCellValue());	
			
			locationPopupPage.getLocationForm();
			locationPopupPage.setLocation(sheet1.getRow(i).getCell(0).getStringCellValue());
			Thread.sleep(500);

			int actualNum = searchResultPage.getSearchResultsNum();
			int expectedNum = (int) sheet1.getRow(i).getCell(2).getNumericCellValue();
			Assert.assertEquals(expectedNum, actualNum, "Search results number ERROR");
			
			for (int j = 0; j < actualNum; j++) {
				
				String dataResult = sheet1.getRow(i).getCell(j + 3).getStringCellValue();
				String atualResult = searchResultPage.getSearchResultsText().get(j);
				
				if (dataResult != null) {
					Assert.assertTrue(atualResult.contains(dataResult), "Search results ERROR");
				}
				
			}
		}
		
	}
	
}
