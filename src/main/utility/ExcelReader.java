package utility;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    FileInputStream excelFile;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    String path;
    String sheetName;

    public ExcelReader(String path, String sheetName){
        this.path = path;
        this.sheetName = sheetName;
    }

    public String getDataFromCell(int rowNum, int colNum){
        String value;
        try {
            excelFile = new FileInputStream(path);
            workbook = new XSSFWorkbook(excelFile);
            sheet = workbook.getSheet(sheetName);
            value = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return value;
    }
    public List<String> getEntireColumnData(int column) {
        List<String> data = new ArrayList<>();
        try {
            excelFile = new FileInputStream(path);
            workbook = new XSSFWorkbook(excelFile);
            sheet = workbook.getSheet(sheetName);
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                data.add(sheet.getRow(i).getCell(column).getStringCellValue());
            }
            excelFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
    public String getCellValueForGivenKey(String key){
        String data = null;
        int i = 0;
        while(getDataFromCell(0, i) != null){
            for (int j = 0; j < getEntireColumnData(i).size(); j++) {
                if (getEntireColumnData(i).get(j).equalsIgnoreCase(key)){
                    data = getEntireColumnData(i+1).get(j);
                }

            }
            break;
        }
        return data;
    }

    public static void main(String[] args) {
        ExcelReader excelReader = new ExcelReader("C:\\Users\\PNT\\eclipse-workspace\\Aug2023SeleniumProject1\\data\\data.xlsx","data");
        System.out.println(excelReader.getCellValueForGivenKey("Login Page Title"));
    }
}