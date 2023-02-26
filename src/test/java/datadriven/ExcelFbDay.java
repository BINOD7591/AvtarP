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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ExcelFbDay {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.facebook.com/r.php?locale=en_GB&display=page");
		WebElement dayDropDown = driver.findElement(By.id("day"));
		Select select = new Select(dayDropDown);
		FileInputStream fis = new FileInputStream("./src/test/resources/Book1.xlsx");
		Workbook workBook = WorkbookFactory.create(fis);
		Sheet sheet = workBook.getSheet("Day");
		Row row = sheet.getRow(0);
		short minColIx = row.getFirstCellNum();
		short maxColIx = row.getLastCellNum();
		for(short colIx=minColIx; colIx<maxColIx; colIx++) {
			Cell cell = row.getCell(colIx);
			int day = (int)cell.getNumericCellValue();
			String data=""+day;
			select.selectByVisibleText(data);
			if(select.getFirstSelectedOption().isSelected()) {
				System.out.println(data+" selected");
			}
			else
			{
				System.out.println(data+" not Selected");
			}
		}
		fis.close();
		driver.manage().window().minimize();
		driver.quit();
	}
}
