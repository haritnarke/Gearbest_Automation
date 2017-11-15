package com.test.automation.UiActions;

import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import com.test.automation.properties.ReadDataFromPropertiesFile;

public class Signup extends ReadDataFromPropertiesFile {

	public WebDriver driver;

	public void register() {

		driver.findElement(By.id("close")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Actions builder = new Actions(driver);
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement link = driver.findElement(By.xpath("//*[@id='js_isNotLogin']/a/span"));
		builder.moveToElement(link).build().perform();
		driver.findElement(By.xpath("//*[@id='js_isNotLogin']/div/a")).click();

		driver.findElement(By.id("reg_email")).sendKeys(readdata("RegistrationEmail"));
		driver.findElement(By.id("password")).sendKeys(readdata("Password"));
		driver.findElement(By.id("password_confirm")).sendKeys(readdata("Password"));
		Scanner sc = new Scanner(System.in);
		String captcha = sc.nextLine();
		sc.close();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("reg_code")).sendKeys(captcha);

		driver.findElement(By.xpath("//*[@id='js_registBtn']/span")).click();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Signup(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public String ActivationConfirmation() {

		return driver.findElement(By.xpath("//*[@id='mainWrap']/article/h3")).getText();
	}

}
