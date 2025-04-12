package org.example;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelToJson {
//    String version="Amirza_v09";
    public void ExcellToJsonPersonel(String fileName){
        String filePath = "E:\\Amirza\\HRIS\\"+fileName+".xlsx";
        List<Map<String, Object>> receiveData = new ArrayList<>();
        Map<String, Object> data = new HashMap<>();

        data.put("TimeEntryCode", "1403-01");
        data.put("DataSetCode", "01");
        data.put("CompanyCode", "1222401");

        List<Map<String, Object>> dataContent = new ArrayList<>();
        Map<String, Object> dataCategory = new HashMap<>();
        dataCategory.put("DataCategoryCode", "personeli_hokm");
        dataCategory.put("ReceiveData", receiveData);
        dataContent.add(dataCategory);
        data.put("DataContent", dataContent);

        try (Workbook workbook = new XSSFWorkbook(new File(filePath))) {
            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getPhysicalNumberOfRows();
            List<String> columns = new ArrayList<>();

            Row headerRow = sheet.getRow(0);
            for (Cell cell : headerRow) {
                columns.add(cell.getStringCellValue());
            }

            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                Map<String, Object> rowData = new HashMap<>();
//                rowData.put("ROWINDEX", String.valueOf(i + 1));
                for (int j = 0; j < columns.size(); j++) {
                    if  (columns.get(j).equalsIgnoreCase("PER_CODE"))
                        rowData.put("ROWINDEX", (row.getCell(j) != null) ? row.getCell(j).toString() : "");

                }

                List<Map<String, String>> rowValues = new ArrayList<>();
                for (int j = 0; j < columns.size(); j++) {
                    String keyName = columns.get(j).toLowerCase();
                    String value = (row.getCell(j) != null) ? row.getCell(j).toString() : "";
                    Map<String, String> valueMap = new HashMap<>();
                    valueMap.put("KeyName", keyName);
                    valueMap.put("VALUE", value);
                    rowValues.add(valueMap);
                }
                rowData.put("RowData", rowValues);
                receiveData.add(rowData);
            }
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonData = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
            try (FileWriter jsonFile = new FileWriter("E:\\Amirza\\HRIS\\"+fileName+".json")) {
                jsonFile.write(jsonData);
            }
            System.out.println("داده‌ها با موفقیت به فرمت JSON تبدیل شد و به نام "+ fileName + "ذخیره شد");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void excelToJsonSalary(String fileName){



        String filePath = "E:\\Amirza\\HRIS\\"+fileName+".xlsx";
        List<Map<String, Object>> receiveData = new ArrayList<>();
        Map<String, Object> data = new HashMap<>();

        data.put("TimeEntryCode", "1403-01");
        data.put("DataSetCode", "02");
        data.put("CompanyCode", "1222401");

        List<Map<String, Object>> dataContent = new ArrayList<>();
        Map<String, Object> dataCategory = new HashMap<>();
        dataCategory.put("DataCategoryCode", "fish");
        dataCategory.put("ReceiveData", receiveData);
        dataContent.add(dataCategory);
        data.put("DataContent", dataContent);

        try (Workbook workbook = new XSSFWorkbook(new File(filePath))) {
            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getPhysicalNumberOfRows();
            List<String> columns = new ArrayList<>();

            Row headerRow = sheet.getRow(0);
            for (Cell cell : headerRow) {
                columns.add(cell.getStringCellValue());
            }

            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                Map<String, Object> rowData = new HashMap<>();
//                rowData.put("ROWINDEX", String.valueOf(i + 1));
                for (int j = 0; j < columns.size(); j++) {
                    if  (columns.get(j).equalsIgnoreCase("rowindex"))
                        rowData.put("ROWINDEX", (row.getCell(j) != null) ? row.getCell(j).toString() : "");

                }

                List<Map<String, String>> rowValues = new ArrayList<>();
                for (int j = 0; j < columns.size(); j++) {
                    if  (!columns.get(j).equalsIgnoreCase("rowindex")) {
                        String keyName = columns.get(j).toLowerCase();
                        String value = (row.getCell(j) != null) ? row.getCell(j).toString() : "";
                        Map<String, String> valueMap = new HashMap<>();
                        valueMap.put("KeyName", keyName);
                        valueMap.put("VALUE", value);
                        rowValues.add(valueMap);
                    }
                }
                rowData.put("RowData", rowValues);
                receiveData.add(rowData);
            }
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonData = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
            try (FileWriter jsonFile = new FileWriter("E:\\Amirza\\HRIS\\"+fileName+".json")) {
                jsonFile.write(jsonData);
            }
            System.out.println("داده‌ها با موفقیت به فرمت JSON تبدیل شد و به نام "+ fileName + "ذخیره شد");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

