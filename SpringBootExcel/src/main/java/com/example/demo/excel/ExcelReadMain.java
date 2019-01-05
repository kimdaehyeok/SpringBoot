package com.example.demo.excel;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReadMain 
{
	public static void main(String[] args) throws Exception 
	{
		List<ExcelVo> excelList = new ArrayList();

		File excelFile = new File("/Users/gimdaehyeog/Downloads/test.xlsx");

		FileInputStream excelFileInputStream = new FileInputStream(excelFile);

		Workbook workbook = new XSSFWorkbook(excelFileInputStream);

		Sheet datatypeSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = datatypeSheet.iterator();
		
		while (iterator.hasNext()) 
		{
			Row currentRow = iterator.next();
			
			ExcelVo tempExcelVo = new ExcelVo();

			tempExcelVo.setName(currentRow.getCell(0).toString());
			tempExcelVo.setEmail(currentRow.getCell(1).toString());
			tempExcelVo.setPurchasedPackage(currentRow.getCell(2).toString());
			
			excelList.add(tempExcelVo);
			
			System.out.println();
		}
		
		for(ExcelVo excelVo : excelList)
		{
			System.out.println(excelVo.getName());
			System.out.println(excelVo.getEmail());
			System.out.println(excelVo.getPurchasedPackage());

			System.out.println();
		}
	}
}
