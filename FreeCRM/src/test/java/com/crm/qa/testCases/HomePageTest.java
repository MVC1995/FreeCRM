package com.crm.qa.testCases;

import org.testng.annotations.*;

import com.crm.qa.base.BaseClass;
import com.crm.qa.pageObjects.ContactsPage;
import com.crm.qa.pageObjects.HomePage;
import com.crm.qa.pageObjects.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends BaseClass
{
	public LoginPage loginPO;
	public HomePage homePO;
	public TestUtil utilPO;
	public ContactsPage contactPO;
	public HomePageTest()
	{
		super();
	}
	@BeforeMethod
	public void createObj()
	{
		initialize();
		loginPO= new LoginPage();
		homePO= new HomePage();
		contactPO= new ContactsPage();
		utilPO= new TestUtil();
		loginPO.login();
	}

	@Test (priority=1)
	public void verifyHomePageTitleTest()
	{
		homePO.verifyHomePageTitle();
	}
	
	@Test(priority=2)
	public void verifyUserNameTest()
	{
		utilPO.switchToFrame();
		homePO.verifyHomePageTitle();
	}
	@Test(priority=3)
	public void verifyContactsTabTest()
	{
		utilPO.switchToFrame();
		contactPO=homePO.clickOnContactsTab();
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
