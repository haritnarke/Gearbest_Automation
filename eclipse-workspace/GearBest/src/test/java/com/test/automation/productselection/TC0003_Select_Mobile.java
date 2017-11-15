package com.test.automation.productselection;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.automation.UiActions.Login;
import com.test.automation.UiActions.Logout;
import com.test.automation.UiActions.ProductSelection;
import com.test.automation.testbase.TestBase;

public class TC0003_Select_Mobile extends TestBase{

	Login login;
	Logout logout;
	ProductSelection selectproduct;
	@BeforeTest
	public void setup() {

		init();
	}

	@Test
	public void verifyLogin() {

		login = new Login(driver);
		login.LoginToTheApplication();
		System.out.println(login.GetAccountName());
		AssertJUnit.assertEquals(login.GetAccountName(), readdata("AccountName"));
		selectproduct = new ProductSelection(driver);
		selectproduct.SelectMobile();
		
	}

	@AfterClass
	public void endTest() {
		logout = new Logout(driver);
		logout.signout();
		closeBrowser();
	}


}
