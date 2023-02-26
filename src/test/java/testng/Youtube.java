package testng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Youtube {
	
	@Test(invocationCount = 10)
	public void run() {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.youtube.com/watch?v=SIDegIHkUIY&feature=youtu.be");
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[starts-with(@class,'ytp-large-play-button')]")))).click();
	}
	

}
