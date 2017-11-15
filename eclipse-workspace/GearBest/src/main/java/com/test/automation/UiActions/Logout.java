package com.test.automation.UiActions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.test.automation.testbase.TestBase;

public class Logout extends TestBase{

	public WebDriver driver;
	
	
	public void signout() {
		Actions builder = new Actions(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement link = driver.findElement(By.xpath("//*[@id=\"js_isLoginInfo\"]/span"));
		builder.moveToElement(link).build().perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		jse.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@id=\"js_user_links\"]/li[9]/a")));

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	
	public Logout(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

}
