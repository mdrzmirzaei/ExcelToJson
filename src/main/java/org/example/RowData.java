// RowData.java
package org.example;
import java.util.List;

public class RowData {
    private List<String> ValidationData;
    private String DataType;
    private String TimeSectionCode;
    private String TimeSectionTitle;
    private String DataItemKeyName;
    private String DataItemTitle;
    private String DataItemDescription;
    private List<Object> ValidData;

    // Getters and Setters


    public List<String> getValidationData() {
        return ValidationData;
    }

    public void setValidationData(List<String> validationData) {
        ValidationData = validationData;
    }

    public String getDataType() {
        return DataType;
    }

    public void setDataType(String dataType) {
        DataType = dataType;
    }

    public String getTimeSectionCode() {
        return TimeSectionCode;
    }

    public void setTimeSectionCode(String timeSectionCode) {
        TimeSectionCode = timeSectionCode;
    }

    public String getTimeSectionTitle() {
        return TimeSectionTitle;
    }

    public void setTimeSectionTitle(String timeSectionTitle) {
        TimeSectionTitle = timeSectionTitle;
    }

    public String getDataItemKeyName() {
        return DataItemKeyName;
    }

    public void setDataItemKeyName(String dataItemKeyName) {
        DataItemKeyName = dataItemKeyName;
    }

    public String getDataItemTitle() {
        return DataItemTitle;
    }

    public void setDataItemTitle(String dataItemTitle) {
        DataItemTitle = dataItemTitle;
    }

    public String getDataItemDescription() {
        return DataItemDescription;
    }

    public void setDataItemDescription(String dataItemDescription) {
        DataItemDescription = dataItemDescription;
    }

    public List<Object> getValidData() {
        return ValidData;
    }

    public void setValidData(List<Object> validData) {
        ValidData = validData;
    }
}
