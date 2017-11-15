package com.test.automation.UiActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.automation.properties.ReadDataFromPropertiesFile;

public class Login extends ReadDataFromPropertiesFile {
	
	@FindBy(xpath = "//*[@id=\"js_isNotLogin\"]/a/span")
	WebElement Signin;

	@FindBy(id = "close")
	WebElement AlertClose;

	@FindBy(id ="email")
	WebElement Email;

	@FindBy(id ="passwordsign")
	WebElement Password;

	@FindBy(id ="js_signInBtn")
	WebElement signinbutton;

	@FindBy(xpath = "//*[@id='js_isLoginInfo']/span")
	WebElement AccountName;

	public void LoginToTheApplication() {

		AlertClose.click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Signin.click();
		System.out.println(readdata("EmailAddress"));
		Email.sendKeys(readdata("EmailAddress"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Password.sendKeys(readdata("Password"));
		signinbutton.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String GetAccountName() {

		return AccountName.getText();
	}

	public Login(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}

}
