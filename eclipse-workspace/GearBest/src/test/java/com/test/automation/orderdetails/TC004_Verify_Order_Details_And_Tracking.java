package com.test.automation.orderdetails;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.automation.UiActions.Login;
import com.test.automation.UiActions.Logout;
import com.test.automation.UiActions.OrderHistory;
import com.test.automation.testbase.TestBase;

public class TC004_Verify_Order_Details_And_Tracking extends TestBase{
	Login login;
	Logout logout;
	OrderHistory orderdetails;

	@BeforeTest
	public void setup() {

		init();
	}

	@Test
	public void verifyLogin() throws InterruptedException {

		login = new Login(driver);
		login.LoginToTheApplication();
		screencapture("Login_");
		System.out.println(login.GetAccountName());
		AssertJUnit.assertEquals(login.GetAccountName(), readdata("AccountName"));
		orderdetails = new OrderHistory(driver);
		orderdetails.MyOrderHistory();
	
	}

	@AfterClass
	public void endTest() {
		logout = new Logout(driver);
		logout.signout();
		screencapture("Logout_");
		closeBrowser();
		
	}

}
