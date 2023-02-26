package datadriven;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class RunTimePolymorphism {
	WebDriver driver;
	@Test
	public void Polymorphism() throws IOException
	{
		 //run time polymorphism
		 //script is data independent
		 // run same script in different browser - cross browser testing is easy
		 // resuability of the script enhanced
		 //not to hard code the data - automation standard
		 //maintainenece of the script has become easy.
		FileInputStream fis = new FileInputStream("./src/test/resources/commonsdata.properties");
		Properties pobj = new Properties();
		pobj.load(fis);
		System.out.println(System.getProperty("user.dir"));
		if(pobj.getProperty("browser").equals("chrome"))
		{
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(pobj.getProperty("url"));
			driver.switchTo().activeElement().sendKeys(pobj.getProperty("username"),Keys.TAB,pobj.getProperty("password"),Keys.ENTER);
			//driver.findElement(By.id("loginButton")).click();
		}
		else if(pobj.getProperty("browser").equals("edge"))
		{
			driver=new EdgeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(pobj.getProperty("url"));
			driver.switchTo().activeElement().sendKeys(pobj.getProperty("username"),Keys.TAB,pobj.getProperty("password"));
			driver.findElement(By.id("loginButton")).click();
		}
	}
}
