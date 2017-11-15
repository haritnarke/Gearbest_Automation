package com.test.automation.testbase;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;

import com.test.automation.properties.ReadDataFromPropertiesFile;

public class TestBase extends ReadDataFromPropertiesFile {

	public static WebDriver driver;

	public void init() {

		System.setProperty("webdriver.chrome.driver", readdata("ChromeDriverPath"));
		driver = new ChromeDriver();
		driver.manage().getCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.gearbest.com/");
		
	}


	public void screencapture(String FileName) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// The below method will save the screen shot in d drive with name
		// "screenshot.png"
		try {

			String fileName = FileName + new Date().getTime() + ".png";

			FileUtils.copyFile(scrFile, new File("C:\\Users\\madhav\\eclipse-workspace\\Gearbest\\Screenshot\\" + fileName));

			Reporter.log("<a href='" + "C:\\Users\\madhav\\eclipse-workspace\\Gearbest\\Screenshot\\" + fileName + "'> <img src='"
					+ "C:\\Users\\madhav\\eclipse-workspace\\Gearbest\\Screenshot\\" + fileName
					+ "' height='100' width='100'/> </a>");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Something is wrong in try catch bloack of taking screenshots");
		}

		
	}
	public void closeBrowser() {
		driver.close();

	}

}
