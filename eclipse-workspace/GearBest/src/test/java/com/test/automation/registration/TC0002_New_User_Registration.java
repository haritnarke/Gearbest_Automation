package com.test.automation.registration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.automation.UiActions.Logout;
import com.test.automation.UiActions.Signup;
import com.test.automation.testbase.TestBase;

import junit.framework.Assert;

public class TC0002_New_User_Registration extends TestBase {

	Signup registration;
	Logout logout;
	@BeforeTest
	public void setup() {

		init();
	}

	@Test
	public void verifyLogin() {

		registration = new Signup(driver);
		registration.register();
		Assert.assertEquals(registration.ActivationConfirmation(), readdata("ActivationConfirmation"));
	}

	@AfterClass
	public void endTest() {

		logout = new Logout(driver);
		logout.signout();
		closeBrowser();

	}

}
