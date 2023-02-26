package datadriven;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PolymorPhismAlter {
	WebDriver driver;
	
	@BeforeTest
	public void lunchBrowser() throws IOException
	{
		switch (properties("browser")) {
		case "chrome":
			driver=new ChromeDriver();
			break;
		case "edge":
			driver=new EdgeDriver();
			break;
		case "firefox":
			driver=new FirefoxDriver();
			break;

		default:
			System.err.println("invalid browser");
			break;
		}
		driver.manage().window().maximize();
	}
	@Test
	public void lunchApp() throws IOException
	{
		driver.get(properties("url"));
	}
	@Test
	public void login() throws IOException, InterruptedException
	{
		driver.switchTo().activeElement().sendKeys(properties("username"),Keys.TAB,properties("password"),Keys.ENTER);
		Thread.sleep(3000);
	}
	@AfterTest
	public void tearDown()
	{
		driver.manage().window().minimize();
		driver.quit();
	}
	public String properties(String value) throws IOException
	{
		FileInputStream fis = new FileInputStream("./src/test/resources/commonsdata.properties");
		Properties pobj = new Properties();
		pobj.load(fis);
		String data=pobj.getProperty(value);
		fis.close();
		return data;
	}
	

}
