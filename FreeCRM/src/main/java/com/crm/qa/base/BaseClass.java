package com.crm.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass 
{
	public static WebDriver driver;
	public static String propFilePath="E:\\Java_Learn\\Amazon\\FreeCRM\\src\\main\\java\\com\\crm\\qa\\configuration\\config.properties";
	public static Properties prop;
	public static EventFiringWebDriver e_driver; // Which shows the actions of WebDriver in output console while executing
	public static WebEventListener eventListener;
	
	public BaseClass()
	{
		try 
		{
			File fil = new File(propFilePath);
			FileInputStream fis;
			fis = new FileInputStream(fil);
			prop= new Properties();
			prop.load(fis);
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void initialize()
	{
		if(prop.getProperty("browser").equalsIgnoreCase("Chrome")==true)
		{
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}
		else if (prop.getProperty("browser").equalsIgnoreCase("FireFox")==true)
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		e_driver = new EventFiringWebDriver(driver);
		//Now create object of EventListenerHandler to register it with EventFiringWebDriver
		eventListener= new WebEventListener();
		e_driver.register(eventListener);
		driver=e_driver;
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
//		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT)); //PAGE_LOAD_TIMEOUT is declared as public variable in util package
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		driver.get(prop.getProperty("url"));
		 
	}
	
}
