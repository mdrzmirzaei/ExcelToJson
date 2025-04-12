package org.example.myTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.Entities.DataEntryModel;
import org.example.Entities.TimeEntry;
import org.example.TokenArray;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class sendRequestToRestAPI {
    private static String geturl = "http://192.168.5.72:8080";

    public static void main(String[] args) throws Exception {
//Deprecated    sendOKHttpRequest();
//        sendHttpRequest();
//        getToken();
        getDataEntryModel();
//        DataModelToExcel();
    }


    private static void sendHttpRequest() throws Exception {
        URL url = new URL(geturl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int responseCode = httpURLConnection.getResponseCode();
        System.out.println("this is response Code" + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputline;
            StringBuffer response = new StringBuffer();

            while ((inputline = in.readLine()) != null) {
                response.append(inputline);
            }
            in.close();

            System.out.println("this is Response : " + response);
        }
    }


    private static void getToken() throws Exception {
        geturl = "http://192.168.5.72:8080";
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            String jsonData = "{\"user\":\"123456\",\"password\":\"13650813\"}";
            MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(mediaType, jsonData);
            Request request = new Request.Builder().url(geturl).post(body).build();

            ResponseBody responseBody = okHttpClient.newCall(request).execute().body();
            ObjectMapper objectMapper = new ObjectMapper();
            TokenArray tokenArray = objectMapper.readValue(responseBody.string(), TokenArray.class);
            for (int i = 0; i < tokenArray.getTokens().size(); i++) {
                System.out.println(tokenArray.getTokens().get(i).getToken());
                System.out.println(tokenArray.getTokens().get(i).getStatus());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void DataModelToExcel() throws Exception {
        geturl = "http://192.168.5.72:8080/mapResponse";
        OkHttpClient okHttpClient = new OkHttpClient();
        String jsonData = "{\"user\":\"123456\",\"password\":\"13650813\"}";
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(mediaType, jsonData);
        Request request = new Request.Builder().url(geturl).post(body).build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("unExpected Excpetion");


            assert response.body() != null;
            String responseBodyStr = response.body().string();
            System.out.println(responseBodyStr);


            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> responseMap = objectMapper.readValue(responseBodyStr, new TypeReference<Map<String, Object>>() {
            });
            List<Map<String, Object>> modelList = (List<Map<String, Object>>) responseMap.get("model");
//            List<Map<String, Object>> jsonArray = objectMapper.readValue(responseBody.string(), objectMapper.getTypeFactory().constructCollectionType(List.class, Map.class));
            for (int i = 0; i < modelList.size(); i++) {
                System.out.println("this is Ids  " + modelList.get(i).get("id"));
            }

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("sheet1");

            if (!modelList.isEmpty()) {
                Map<String, Object> firstRow = modelList.get(0);
                Row headerRow = sheet.createRow(0);
                int headerCellIndex = 0;

                for (String key : firstRow.keySet()) {
                    Cell cell = headerRow.createCell(headerCellIndex++);
                    cell.setCellValue(key);
                }
            }
            int rowIndex = 1;
            for (Map<String, Object> rowData : modelList) {
                Row row = sheet.createRow(rowIndex++);
                int cellIndex = 0;
                for (Object value : rowData.values()) {
                    Cell cell = row.createCell(cellIndex++);
                    cell.setCellValue(value != null ? value.toString() : "");
                }
            }
            try (FileOutputStream fileOutputStream = new FileOutputStream("modelData.xlsx")) {
                workbook.write(fileOutputStream);
                ;
                System.out.println("excel of Columns are created !!");
            }
            workbook.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }



        public static void getDataEntryModel () throws Exception {
            geturl = "http://192.168.5.72:8080/mapResponse";
            OkHttpClient okHttpClient = new OkHttpClient();
            String jsonData = "{\"user\":\"123456\",\"password\":\"13650813\"}";
            MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(mediaType, jsonData);
            Request request = new Request.Builder().url(geturl).post(body).build();

            ResponseBody responseBody = okHttpClient.newCall(request).execute().body();
            ObjectMapper objectMapper = new ObjectMapper();
            assert responseBody != null;
            TimeEntry timeEntry = objectMapper.readValue(responseBody.string(), TimeEntry.class);

            StringBuffer sb=new StringBuffer();
            List<String> fields = new ArrayList<>();
            for (DataEntryModel dataEntryModel : timeEntry.getModel() ) {
                fields.add(dataEntryModel.getTitle());
            }
Connection con=null;
            String sb1=String.join(", ",fields);
            String Query = "select " + sb1 + " from hr_c_bpartners";

            String url="jdbc:oracle:thin:@192.168.5.30:1521:orcl";
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
                con = DriverManager.getConnection(url,"repos","repos");
                if (con != null) {
                    System.out.println("is connected!!");
                    PreparedStatement preparedStatement=con.prepareStatement(Query);
                    ResultSet rs=preparedStatement.executeQuery();
                    System.out.println("size of rs is "+rs.getFetchSize());

                }
            }
            catch (Exception exception)
            {
                System.out.println(exception.getCause());
            }
            finally {

            }


            System.out.println(sb1);
            /*
            for (int i = 0; i < timeEntry.getModel().size(); i++) {
                sb.append(timeEntry.getModel().get(i).getTitle());
                if (i<timeEntry.getModel().size())
                sb.append(",");
                System.out.println(timeEntry.getModel().get(i).getTitle());
            }

             */
            System.out.println(sb);


            /*
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("sheet1");
            Row row = null;
            if (timeEntry.getStatus().equals("OK")) {
                row = sheet.createRow(0);
                row.createCell(0).setCellValue("id");
                row.createCell(1).setCellValue("code");
                row.createCell(2).setCellValue("title");
                row.createCell(3).setCellValue("description");
                row.createCell(4).setCellValue("begindate");
                row.createCell(5).setCellValue("enddate");

//            for (DataEntryModel element : timeEntry.getModel()) {             }
            }
            for (int i = 0; i < timeEntry.getModel().size(); i++) {
                sheet.createRow(i);
                row.createCell(0).setCellValue(timeEntry.getModel().get(i).getId());
                row.createCell(1).setCellValue(timeEntry.getModel().get(i).getCode());
                row.createCell(2).setCellValue(timeEntry.getModel().get(i).getTitle());
                row.createCell(3).setCellValue(timeEntry.getModel().get(i).getDescription());
                row.createCell(4).setCellValue(timeEntry.getModel().get(i).getBeginDate());
                row.createCell(5).setCellValue(timeEntry.getModel().get(i).getEndDate());

                System.out.println(timeEntry.getModel().get(i).getId());
                System.out.println(timeEntry.getModel().get(i).getCode());
                System.out.println(timeEntry.getModel().get(i).getTitle());
                System.out.println(timeEntry.getModel().get(i).getDescription());
                System.out.println(timeEntry.getModel().get(i).getBeginDate());
                System.out.println(timeEntry.getModel().get(i).getEndDate());
                System.out.println("======================================");

            }
            try (FileOutputStream outputStream = new FileOutputStream("myData.xlsx")) {
                workbook.write(outputStream);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } finally {
                workbook.close();
            }

             */
        }


    private static void sendOKHttpRequest() throws Exception {
        geturl = "http://192.168.5.72:8080/testResponse";
        OkHttpClient okHttpClient = new OkHttpClient();
        String token = "";

        Request request = new Request.Builder().url(geturl).addHeader("Authorization", "Bearer " + token).build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("unExcepted code" + response);

            System.out.println(response.body());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
