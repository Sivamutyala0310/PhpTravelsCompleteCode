package com.atmecs.phptravels.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.atmecs.phptravels.helpers.LoginPageData;
import com.atmecs.phptravels.helpers.ProfilePageData;
import com.atmecs.phptravels.helpers.Waits;
import com.atmecs.phptravels.pageactions.PageActions;
import com.atmecs.phptravels.reports.ExtentReport;
import com.atmecs.phptravels.reports.LogReports;
import com.atmecs.phptravels.testbase.TestBase;
import com.atmecs.phptravels.utils.TestDataProvider;
import com.atmecs.phptravels.validateSignUp.SignUp;
import com.atmecs.phptravels.validateSignUp.SignUpPassword;

public class PhpTravels_signUp extends TestBase
{
	LogReports log = new LogReports();
	LoginPageData data = new LoginPageData();
	ProfilePageData profileData = new ProfilePageData();
	ExtentReport report = new ExtentReport();

	@BeforeTest
	public void startReports()
	{
		report.startReport();             // ExtentReports
	}

	@Test(dataProvider = "testdata", dataProviderClass = TestDataProvider.class) // providing data from excelfile to
																					// dataprovider

	public void signUp(String firstName, String lastName, String mobileNumber, String email, String password,
			String confirmPassword) throws Exception 
	{
		ExtentReport.logger = extent.startTest("signUp");

		data.getData();

		profileData.getData();

		log.info("navigating to Php travels website");

		PageActions.clickElement(driver, data.getclickOnMyAccountXpath());
		PageActions.clickElement(driver, data.getSignUpDropDown());

		log.info("registering with all the required details to signup");

		PageActions.sendKeys(driver, data.getFirstNameXpath(), firstName);
		PageActions.sendKeys(driver, data.getLastNameXpath(), lastName);
		PageActions.sendKeys(driver, data.getMobileNumberXpath(), mobileNumber);
		PageActions.sendKeys(driver, data.getEmailXpath(), email);
		PageActions.sendKeys(driver, data.getPasswordXpath(), password);
		PageActions.sendKeys(driver, data.getConfirmPasswordXpath(), confirmPassword);
		Waits.explicitWaitClick(driver, data.getSignUpXpath());

		log.info(" User  able to signUp successfully");

		PhpTravels_profile.validateProfile(); // validating profile

		PageActions.clickElement(driver, profileData.getProfileAccount()); // clicking on logout profile
		PageActions.clickElement(driver, profileData.getProfileLogout());

		SignUp.validateSignUpEmail(); // validating SignUp Email field giving value without domain

		SignUp.validateSignUp(); // validating SignUp field giving any values to the fields

		SignUpPassword.validatePasswordField(); // validating password field

	}
}
