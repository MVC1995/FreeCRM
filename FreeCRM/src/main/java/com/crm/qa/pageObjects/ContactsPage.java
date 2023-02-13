package com.crm.qa.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.crm.qa.base.BaseClass;

public class ContactsPage extends BaseClass
{
	public ContactsPage()
	{
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//td[@class='datacardtitle' and contains(text(),'Contacts')]")
	private WebElement contactsDisplayLabel;
	@FindBy(xpath = "//select[@name='title']")
	private WebElement titleList;
	@FindBy(xpath="//input[@name='first_name']")
	private WebElement firstName;
	@FindBy(xpath="//input[@name='surname']")
	private WebElement lastName;
	@FindBy(xpath="//input[@name='client_lookup']")
	private WebElement companyName;
	@FindBy(xpath="//input[@name='phone']")
	private WebElement phoneNum;
	@FindBy(xpath="//input[@value='Load From Company']//following-sibling::input[@value='Save']")
	private WebElement saveBtn;

	
	public void verifyContactPage()
	{
		boolean expected=contactsDisplayLabel.isDisplayed();
		Assert.assertEquals(expected, true, "Contacts page Mismatched");
	}
	
	public void selectContactsByName(String name)
	{
		driver.findElement(By.xpath("//a[contains(text(),'"+name+"')]//parent::td[@class='datalistrow']//preceding-sibling::td//child::input[@type='checkbox']")).click();
	}
	
	public void createNewContact(String title, String fname, String lname, String companyNam, String phNum)
	{
		Select sel= new Select(titleList);
		sel.selectByVisibleText(title);
		firstName.sendKeys(fname);
		lastName.sendKeys(lname);
		companyName.sendKeys(companyNam);
		phoneNum.sendKeys(phNum);
		saveBtn.click();
		
	}
}
