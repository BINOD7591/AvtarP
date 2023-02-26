package datadriven;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ReadDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();
		Select select = new Select(driver.findElement(By.id("month")));
		FileInputStream fis = new FileInputStream("./src/test/resources/Book.xlsx");
		Workbook workBook = WorkbookFactory.create(fis);
		Sheet sheet = workBook.getSheet("commondata");
		Row row = sheet.getRow(0);
		short minColIx = row.getFirstCellNum();
		short maxColIx = row.getLastCellNum();
		for(short colIx=++minColIx; colIx<maxColIx; colIx++) {
			Cell cell = row.getCell(colIx);
			//String data = cell.getStringCellValue();
			//System.out.println(data+" ");
			select.selectByVisibleText(cell.getStringCellValue());
			if (select.getFirstSelectedOption().isSelected()) {
				System.out.println(cell.getStringCellValue()+" selected");
				if (select.getFirstSelectedOption().getText().equals(cell.getStringCellValue())) {
					System.out.println("Pass: when user select "+cell.getStringCellValue()+", "+select.getFirstSelectedOption().getText()+" is selected.");
				}
				else
				{
					System.out.println("Fail: when user select "+cell.getStringCellValue()+", "+select.getFirstSelectedOption().getText()+" is selected.");
				}
			}
			else
			{
				System.out.println(cell.getStringCellValue()+" not selected.");
			} 
		}
		fis.close();
		driver.manage().window().minimize();
		driver.quit();
	}
}
