package com.crm.qa.testCases;

import org.testng.annotations.*;

import com.crm.qa.base.BaseClass;
import com.crm.qa.pageObjects.ContactsPage;
import com.crm.qa.pageObjects.HomePage;
import com.crm.qa.pageObjects.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends BaseClass {

	public ContactsPage contactPO;
	public TestUtil	utilPO;
	public LoginPage loginPO;
	public HomePage homePO;
	@BeforeMethod
	public void createObj()
	{
		initialize();
		loginPO= new LoginPage();
		contactPO= new ContactsPage();
		utilPO= new TestUtil();
		homePO= new HomePage();
		loginPO.login();
		utilPO.switchToFrame();
		contactPO=homePO.clickOnContactsTab();
	}
	
	@Test(priority=1)
	public void verifyContactsPageLabelTest()
	{
		//utilPO.switchToFrame();
		contactPO.verifyContactPage();	
	}
	
	@Test(priority=2)
	public void verifycontactsDispplayedTest()
	{
		//utilPO.switchToFrame();
		contactPO.selectContactsByName("Test123 Java");
	}
	@DataProvider
	public Object[][] getcontactTestData()
	{
		Object data[][]=TestUtil.getExcelData("Contacts");
		return data;
	}
	@Test(priority=3, dataProvider="getcontactTestData")
	public void validateCreateNewContact(String title, String fname, String lname, String companyNam, String phNum)
	{
		homePO.clickonNewContact();
		//contactPO.createNewContact("Mr.", "Tom", "peeter", "google", "9999552233");
		contactPO.createNewContact(title, fname, lname, companyNam, phNum);
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
