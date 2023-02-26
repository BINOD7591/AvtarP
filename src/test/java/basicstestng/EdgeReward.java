package basicstestng;

import java.time.Duration;
import java.time.LocalDateTime;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EdgeReward {
	WebDriver driver;
	@BeforeClass
	public void lunchBing()
	{ 
		driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.bing.com/");
	}
	@Test
	public void RewardCollection() throws InterruptedException
	{
		WebElement search = driver.findElement(By.id("sb_form_q"));
		search.sendKeys(LocalDateTime.now().toString());
		driver.findElement(By.id("search_icon")).click();
		WebElement searchTextf = driver.findElement(By.id("sb_form_q"));
		searchTextf.clear();
		Thread.sleep(200);
		searchTextf.sendKeys(LocalDateTime.now().toString());
		driver.findElement(By.id("sb_form_go")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("id_a")).click();
		Thread.sleep(2000);
	}
	@Test(dependsOnMethods = "RewardCollection",invocationCount = 35)
	public void RRcollect() throws InterruptedException
	{
		WebElement searchTextf = driver.findElement(By.id("sb_form_q"));
		searchTextf.clear();
		Thread.sleep(300);
		searchTextf.sendKeys(LocalDateTime.now().toString());
		driver.findElement(By.id("sb_form_go")).click();
	}
	@AfterClass
	public void tearDown()
	{
		driver.manage().window().minimize();
		driver.quit();
	}



}
