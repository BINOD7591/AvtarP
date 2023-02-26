package datadriven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteAndReadToExcel {

	public static void main(String[] args) throws IOException {
		//Created Workbook
		XSSFWorkbook workBook=new XSSFWorkbook();

		//Create the sheet in the workbook
		XSSFSheet sheet = workBook.createSheet("created");

		//Create the row in the sheet
		XSSFRow row = sheet.createRow(1);

		//create a cell In the prefred row
		XSSFCell cell = row.createCell(2);

		//write The data in the created cell
		cell.setCellValue("BINOD");

		//Create a file type object with it's relative path 
		File path=new File("./src/test/resources/created.xlsx");

		//pass the file path in FileOutPutStream
		FileOutputStream fos = new FileOutputStream(path);

		//assign the data to the file created 
		workBook.write(fos);

		workBook.close();
		fos.close();

		FileInputStream fis = new FileInputStream("./src/test/resources/created.xlsx");
		Workbook readWorkBook = WorkbookFactory.create(fis);
		Sheet readSheet = readWorkBook.getSheet("created");
		Row readRow = readSheet.getRow(1);
		Cell readCell = readRow.getCell(2);
		CellType cellType = readCell.getCellType();
		if (cellType.toString().equals("NUMERIC")) {
			double readData = readCell.getNumericCellValue();
			System.out.println(readData);
		}
		else if(cellType.toString().equals("STRING"))
		{
			String readData = readCell.getStringCellValue();
			System.out.println(readData);
		}
		readWorkBook.close();
		fis.close();
	}
}
