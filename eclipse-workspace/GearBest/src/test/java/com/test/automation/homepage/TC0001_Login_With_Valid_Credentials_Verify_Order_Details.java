package com.test.automation.homepage;

import org.testng.AssertJUnit;
import org.testng.annotations.*;
import com.test.automation.UiActions.Login;
import com.test.automation.UiActions.Logout;
import com.test.automation.UiActions.OrderHistory;
import com.test.automation.testbase.TestBase;

public class TC0001_Login_With_Valid_Credentials_Verify_Order_Details extends TestBase {

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
		System.out.println(login.GetAccountName());
		AssertJUnit.assertEquals(login.GetAccountName(), readdata("AccountName"));
		orderdetails = new OrderHistory(driver);
		orderdetails.MyOrderHistory();
	}

	@AfterClass
	public void endTest() {
		logout = new Logout(driver);
		logout.signout();
		closeBrowser();	}

}
