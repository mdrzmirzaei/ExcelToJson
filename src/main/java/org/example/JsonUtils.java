package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    public void excellReader() throws IOException {
        FileInputStream file = new FileInputStream("E:/excellToJson/2.xlsx");
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING:
                        System.out.print(cell.getStringCellValue() + "\t");
                        break;
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "\t");
                        break;
                    default:
                        break;
                }
            }
            System.out.println();
        }

        workbook.close();
        file.close();
    }

    public void excelToJsonConverter() throws IOException {
        FileInputStream file = new FileInputStream("E:/excellToJson/2.xlsx");
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        List<Map<String, String>> data = new ArrayList<>();

        Row headerRow = sheet.getRow(0);
        int colCount = headerRow.getPhysicalNumberOfCells();

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Map<String, String> jsonMap = new HashMap<>();

            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);
                String header = headerRow.getCell(j).getStringCellValue();

                        if (cell != null) {
                            jsonMap.put("KeyName :"+ header, "VALUE:" + cell.getStringCellValue());
                        }


           /*
                if (cell != null) {
                    switch (cell.getCellType()) {
                        case STRING:
                            jsonMap.put(header, cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            jsonMap.put(header, String.valueOf(cell.getNumericCellValue()));
                            break;
                        default:
                            break;
                    }
                }
            }
            */
                data.add(jsonMap);
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonOutput = gson.toJson(data);

            try (FileWriter writer = new FileWriter("E:/excellToJson/output.json")) {
                writer.write(jsonOutput);
            }

            workbook.close();
            file.close();

        }

    }




    }
