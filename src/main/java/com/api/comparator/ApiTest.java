package com.api.comparator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ApiTest {
	
	public static Set<String> loadFromXLToLanguageMap(final String fileName) {
		File file = new File(fileName);
		FileInputStream inStream = null;
		Set<String> set = new HashSet<>();
		
		try {
			inStream = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(inStream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			for (Row row : sheet) {
				Cell cell = row.getCell(0);
				if (null == cell ) {
					continue;
				}

				String data = row.getCell(0).getStringCellValue().trim();
				if(data.length() == 0){
					continue;
				}
				data = "{\"data\": [\""+data+"\"]}";
				set.add(data);

			}

			workbook.close();
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException x) {
					x.printStackTrace();
				}
			}
		}
		return set;
	}

}
