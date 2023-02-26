package datadriven;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class csvFileRead {

	public static void main(String[] args) throws IOException, CsvException {
		// store the address of the file In the string
		String filePath = "./src/test/resources/commonData.csv";

		// create the object for fileReader class of java
		FileReader csvToRead = new FileReader(filePath);

		CSVReader consideredCsvFile = new CSVReader(csvToRead);
		List<String[]> dataToRead = consideredCsvFile.readAll();
		long totalLine = consideredCsvFile.getLinesRead();
		System.out.println("totalLine:= " + totalLine);
		for (String[] singleRow : dataToRead) {
			for (String data : singleRow) {
				System.out.print(data+" ");
			}
			System.out.println();
		}
	}
}
