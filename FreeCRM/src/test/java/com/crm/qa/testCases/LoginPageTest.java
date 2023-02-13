package com.crm.qa.testCases;

import org.testng.Assert;
import org.testng.annotations.*;

import com.crm.qa.base.BaseClass;
import com.crm.qa.pageObjects.HomePage;
import com.crm.qa.pageObjects.LoginPage;

public class LoginPageTest extends BaseClass
{
	public LoginPage loginPO;
	public HomePage homePO;
	
	public LoginPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void createObj()
	{
		initialize();
		loginPO= new LoginPage();
	}
	@Test(priority=1)
	public void loginPageTitleTest()
	{
		String title= loginPO.validateLoginPageTitle();
		Assert.assertEquals(title, "Free CRM - CRM software for customer relationship management, sales, and support.");
	}
	@Test(priority=2)
	public void crmLogoTest()
	{
		boolean flag=loginPO.validateCRMImage();
		Assert.assertTrue(flag);
	}
	@Test(priority=3)
	public void loginTest()
	{
		homePO=loginPO.login();
	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
