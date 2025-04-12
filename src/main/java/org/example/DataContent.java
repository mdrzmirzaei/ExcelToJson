package org.example;
import java.util.List;

public class DataContent {
    private String DataCategoryCode;
    private String DataCategoryTitle;
    private String DataCategoryDescription;
    private List<RowData> RowData;

    // Getters and Setters
    public String getDataCategoryCode() {
        return DataCategoryCode;
    }

    public void setDataCategoryCode(String dataCategoryCode) {
        DataCategoryCode = dataCategoryCode;
    }

    public String getDataCategoryTitle() {
        return DataCategoryTitle;
    }

    public void setDataCategoryTitle(String dataCategoryTitle) {
        DataCategoryTitle = dataCategoryTitle;
    }

    public String getDataCategoryDescription() {
        return DataCategoryDescription;
    }

    public void setDataCategoryDescription(String dataCategoryDescription) {
        DataCategoryDescription = dataCategoryDescription;
    }

    public List<RowData> getRowData() {
        return RowData;
    }

    public void setRowData(List<RowData> rowData) {
        RowData = rowData;
    }
}
