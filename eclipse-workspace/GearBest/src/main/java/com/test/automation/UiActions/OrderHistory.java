package com.test.automation.UiActions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.test.automation.properties.ReadDataFromPropertiesFile;
import com.test.automation.testbase.TestBase;

public class OrderHistory extends ReadDataFromPropertiesFile {

	public WebDriver driver;
	TestBase basicutilities=new TestBase() ;
	public void MyOrderHistory() throws InterruptedException {
		Actions builder = new Actions(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement link = driver.findElement(By.xpath("//*[@id=\"js_isLoginInfo\"]/span"));
		builder.moveToElement(link).build().perform();
		Thread.sleep(2000);

		jse.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@id='js_user_links']/li[2]/a")));

		jse.executeScript("window.scrollBy(0,200)", "");

		String parentHandle = driver.getWindowHandle(); // get the current window handle
		// System.out.println(parentHandle);
		driver.findElement(By.xpath("//*[@id=\"userMain\"]/div[1]/table[2]/tbody/tr[1]/td[4]/a[1]")).click();
		jse.executeScript("window.scrollBy(0,200)", "");

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's
													// your newly opened window)
		}

		Thread.sleep(1500);
		driver.findElement(By.xpath("//*[@id=\"userMain\"]/div[2]/div/div[3]/div[3]/div[2]/div/div[1]/div[2]/p[3]/a"))
				.click();
		jse.executeScript("window.scrollBy(0,200)", "");

		Thread.sleep(2000);

		System.out.println("********************Below are your order and tracking details******************");

		// If will implemet logic if it dosent find tracking details on gearbest
		if (GetTrackingInformation().equals(readdata("TrackingInformation"))) {
			
			basicutilities.screencapture("Order_Tracking_Not_Available_At_GearBest_");
			String orderid = driver
					.findElement(By.xpath(
							"//*[@id='userMain']/div[2]/div/div[3]/div[3]/div[2]/div/div[1]/div[2]/p[2]/strong/a"))
					.getText();
			// System.out.println(orderid);

			// OrderTracking method fetches tracking data from third party
			OrderTracking(orderid, parentHandle);
		}

		// Logic to list out the order details if its available in Gearbest
		else {
			basicutilities.screencapture("Order_Tracking_");
			List<WebElement> numberofrows = driver.findElements(By.xpath("//*[@id=\"js_trackingInfoBox_0\"]/dd/ul/li"));
			int countOfNumberOfRows = numberofrows.size();
			// System.out.println(countOfNumberOfRows);
			for (int iteration = 1; iteration <= countOfNumberOfRows; iteration++) {
				String trackingDetails = driver
						.findElement(By.xpath("//*[@id='js_trackingInfoBox_0']/dd/ul/li[" + iteration + "]")).getText();
				System.out.println(trackingDetails);
			}
			// Thread.sleep(2000);
			
			
			driver.close();
			driver.switchTo().window(parentHandle);
			
			
		}
	}

	// Logic for getting order details from thirdparty ie Gati if its not available
	// on Gearbest
	public void OrderTracking(String OrderId, String PresentWindowHandle) throws InterruptedException {

		System.out.println("Order ID         :" + OrderId);
		driver.get("http://www.gaticn.com/");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//*[@id='baseTop']/header/div/div[3]/span/a[2]")).click();
		driver.findElement(By.xpath("//*[@id=\"example-navbar-default-collapse\"]/ul/li[3]/a/font")).click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,300)", "");
		driver.findElement(By.xpath("//*[@id=\"trackNums\"]")).sendKeys(OrderId);
		driver.findElement(By.xpath("//*[@id=\"queryBill\"]")).click();
		Thread.sleep(2000);

		

		// Logic to iterate between the table to print out all the values of tracking
		List<WebElement> col = driver.findElements(By.xpath("//*[@id=\"track_list_show\"]/div/table/tbody/tr[1]/td"));
		List<WebElement> row = driver.findElements(By.xpath("//*[@id=\"track_list_show\"]/div/table/tbody/tr/td[1]"));
		int ROW = row.size();
		int COL = col.size();
//System.out.println(ROW+" "+COL);
		for (int a = 1; a <= ROW; a++) {
			for (int b = 1; b <= COL; b++) {
				if (b == 1)
					System.out.println("Date             :"+driver.findElement(By.xpath("//*[@id=\"track_list_show\"]/div/table/tbody/tr[" + a + "]/td[" + b + "]")).getText());
				if (b == 2)
					System.out.println("Place            :"+driver.findElement(By.xpath("//*[@id=\"track_list_show\"]/div/table/tbody/tr[" + a + "]/td[" + b + "]")).getText());
				if (b == 3)
					System.out.println("Status           :"+driver.findElement(By.xpath("//*[@id=\"track_list_show\"]/div/table/tbody/tr[" + a + "]/td[" + b + "]")).getText());
				
				//System.out.println(driver.findElement(By.xpath("//*[@id=\"track_list_show\"]/div/table/tbody/tr[" + a + "]/td[" + b + "]")).getText());

			}
		}
		Thread.sleep(5000);
		// Close the gati tab and go back to orignal tab to logout
		jse.executeScript("window.scrollBy(0,250)", "");
		basicutilities.screencapture("Gati_Order_Tracking_");
		driver.close();
		driver.switchTo().window(PresentWindowHandle);
	}

	public OrderHistory(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public String GetTrackingInformation() {

		// depending on what it returns there is logic implemented above. This will
		// either return Tracking details or no tracking details found based on website
		// behaviour
		boolean searchIconPresence = false;
		try {
			searchIconPresence = driver.findElement(By.xpath("//*[@id=\"js_trackingInfoBox_0\"]/dd/p")).isDisplayed();
		} catch (Exception e) {
		}

		if (searchIconPresence == true) {
			return driver.findElement(By.xpath("//*[@id=\"js_trackingInfoBox_0\"]/dd/p")).getText();
		} else
			return driver.findElement(By.xpath("//*[@id=\"js_trackingInfoBox_0\"]/dd/ul/li[3]")).getText();
	}

}
