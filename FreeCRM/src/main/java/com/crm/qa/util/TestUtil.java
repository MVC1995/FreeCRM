package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.stream.FileImageInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.base.BaseClass;

public class TestUtil extends BaseClass
{

	public static long PAGE_LOAD_TIMEOUT=20;
	public static long IMPLICIT_WAIT=30;
	public static String excelFilePath="E:\\Java_Learn\\Amazon\\FreeCRM\\src\\main\\java\\com\\crm\\qa\\testData\\TestData.xlsx";
	public static XSSFWorkbook book;
	public static XSSFSheet sheet;

	/*
	 * Description: This method used for switching to main frame
	 * 				can be used in any class
	 */
	public void switchToFrame()
	{
		driver.switchTo().frame("mainpanel");
	}

	/*
	 * Reading Excel
	 */
	public static Object[][] getExcelData(String sheetName)
	{
		FileInputStream fis=null;
		try {
			fis=new FileInputStream(excelFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try 
		{
			book=(XSSFWorkbook) WorkbookFactory.create(fis);
		} 
		catch (EncryptedDocumentException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		sheet=book.getSheet(sheetName);
		Object[][] data= new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i=0;i<sheet.getLastRowNum();i++)
		{
			for(int j=0;j<sheet.getRow(0).getLastCellNum();j++)
			{
				data[i][j]=sheet.getRow(i+1).getCell(j).toString();
			}
		}
		return data;
	}

	public static void takeScreenshotAtEndOfTest() 
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String trg=System.getProperty("User.dir");
		try {
			FileUtils.copyFile(src, new File(trg+"/screenshots/"+System.currentTimeMillis()+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
