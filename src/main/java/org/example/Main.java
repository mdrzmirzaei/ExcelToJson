package org.example;

import org.example.myTest.CallProcInJava;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        try {
            ExcelToJson excelToJson=new ExcelToJson();
//           excelToJson.ExcellToJsonPersonel("pers_v35");
//            excelToJson.excelToJsonSalary("salary_v05");

            CallProcInJava callProcInJava=new CallProcInJava();
            callProcInJava.connections();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}



     /*

            String json = "{\"DataCategoryCode\":\"personeli_hokm\",\"DataCategoryTitle\":\"پرسنلی و حکم\",\"DataCategoryDescription\":null,\"RowData\":[{\"ValidationData\":[],\"DataType\":\"عدد صحیح (15)\",\"TimeSectionCode\":\"oy\",\"TimeSectionTitle\":\"سالانه\",\"DataItemKeyName\":\"fjazb\",\"DataItemTitle\":\"فوق العاده جذب\",\"DataItemDescription\":null,\"ValidData\":[]},{\"ValidationData\":[],\"DataType\":\"عدد صحیح (15)\",\"TimeSectionCode\":\"oy\",\"TimeSectionTitle\":\"سالانه\",\"DataItemKeyName\":\"knaemt1402\",\"DataItemTitle\":\"کاهش ناشی از اجرای ماده 5 تبصره 2 تصویب نامه هیات وزیران سال 1402\",\"DataItemDescription\":null,\"ValidData\":[]},{\"ValidationData\":[],\"DataType\":\"عدد صحیح (15)\",\"TimeSectionCode\":\"oy\",\"TimeSectionTitle\":\"سالانه\",\"DataItemKeyName\":\"fisar\",\"DataItemTitle\":\"فوق العاده ایثارگری\",\"DataItemDescription\":null,\"ValidData\":[]},{\"ValidationData\":[],\"DataType\":\"عدد صحیح (15)\",\"TimeSectionCode\":\"oy\",\"TimeSectionTitle\":\"سالانه\",\"DataItemKeyName\":\"rss\",\"DataItemTitle\":\"رقم ثابت سالیانه\",\"DataItemDescription\":null,\"ValidData\":[]},{\"ValidationData\":[],\"DataType\":\"عدد صحیح (15)\",\"TimeSectionCode\":\"oy\",\"TimeSectionTitle\":\"سالانه\",\"DataItemKeyName\":\"ftakhasos\",\"DataItemTitle\":\"فوق العاده تخصص و مهارت\",\"DataItemDescription\":null,\"ValidData\":[]},{\"ValidationData\":[],\"DataType\":\"عدد صحیح (15)\",\"TimeSectionCode\":\"oy\",\"TimeSectionTitle\":\"سالانه\",\"DataItemKeyName\":\"kham\",\"DataItemTitle\":\"کمک هزینه اقلام مصرفی (کمک هزینه خوار و بار)\",\"DataItemDescription\":null,\"ValidData\":[]}]}";

            Gson gson= new Gson();
            Type listType= new TypeToken<List<DataContent>>() {}.getType();
            DataContent dc = gson.fromJson(json,listType);
            System.out.println(dc.getDataCategoryTitle());
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }

        }

    */
