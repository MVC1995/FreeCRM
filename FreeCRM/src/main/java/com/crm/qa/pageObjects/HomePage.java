package com.crm.qa.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.crm.qa.base.BaseClass;

public class HomePage extends BaseClass
{
	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//td[@class='headertext' and contains(text(),'User:')]")
	private WebElement userNameLabel;
	@FindBy(xpath="//a[@title='Contacts']")
	private WebElement contactsTab;
	@FindBy(xpath="//a[@title='Deals']")
	private WebElement dealsTab;
	@FindBy(xpath="//a[@title='Tasks']")
	private WebElement taskTab;
	@FindBy(xpath="//a[@title='New Contact']")
	private WebElement newContactTab;

	public void verifyHomePageTitle()
	{
		String expected= driver.getTitle();
		String actual="CRMPRO";
		Assert.assertEquals(expected, actual,"HomePage Title not Matched");
	}
	public void verifyUserName()
	{
		boolean expected=userNameLabel.isDisplayed();
		Assert.assertEquals(expected, true,"User is not Matched");
	}
	public ContactsPage clickOnContactsTab()
	{
		contactsTab.click();
		return new ContactsPage();
	}
	
	public DealsPage clickOnDealsTab()
	{
		dealsTab.click();
		return new DealsPage();
	}
	
	public TaskPage clickOnTasksTab()
	{
		taskTab.click();
		return new TaskPage();
	}
	 public void clickonNewContact()
	 {
		 Actions act= new Actions(driver);
		 act.moveToElement(contactsTab).perform();
		 newContactTab.click();
	 }

}
