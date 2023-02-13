package com.crm.qa.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.BaseClass;

public class LoginPage extends BaseClass
{
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="username")
	private WebElement username;
	@FindBy(name="password")
	private WebElement password;
	@FindBy(xpath="//input[@type='submit']")
	private WebElement loginBtn;
	@FindBy(xpath="//a[@href='https://classic.freecrm.com/register/']")
	private WebElement signUpBtn;
	@FindBy(xpath="//img[@class='img-responsive' and @src='https://classic.freecrm.com/img/logo.png']")
	private WebElement logo;
	
	public String validateLoginPageTitle()
	{
		return driver.getTitle();
	}
	public boolean validateCRMImage()
	{
		return logo.isDisplayed();
	}
	public HomePage login()
	{
		username.sendKeys(prop.getProperty("username"));
		password.sendKeys(prop.getProperty("password"));
		loginBtn.click();
		return new HomePage();
	}
}
