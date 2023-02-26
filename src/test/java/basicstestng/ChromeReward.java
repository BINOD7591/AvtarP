package basicstestng;

import java.time.Duration;
import java.time.LocalDateTime;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ChromeReward {
	WebDriver driver;
	@BeforeTest
	public void lunchBing()
	{ 
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://login.live.com/login.srf");
	}
	@Parameters({"userName","password"})
	@Test
	public void RewardCollection(String UserName,String Password) throws InterruptedException
	{
		Thread.sleep(2000);
		driver.switchTo().activeElement().sendKeys(UserName,Keys.ENTER);
		Thread.sleep(2000);
		driver.switchTo().activeElement().sendKeys(Password,Keys.ENTER);
		driver.findElement(By.id("idSIButton9")).click();
		Thread.sleep(2000);
		driver.get("https://www.bing.com/");
		Thread.sleep(2000);
		WebElement search = driver.findElement(By.id("sb_form_q"));
		search.sendKeys(LocalDateTime.now().toString());
		driver.findElement(By.id("search_icon")).click();
		
	}
	@Test(dependsOnMethods = "RewardCollection",invocationCount = 20)
	public void RRcollect() throws InterruptedException
	{
		WebElement searchTextf = driver.findElement(By.id("sb_form_q"));
		searchTextf.clear();
		Thread.sleep(300);
		searchTextf.sendKeys(LocalDateTime.now().toString());
		driver.findElement(By.id("sb_form_go")).click();
	}
	@AfterTest
	public void tearDown()
	{
		driver.manage().window().minimize();
		driver.quit();
	}
}
