package org.example.Entities;

import java.util.ArrayList;

public class TimeEntry {
    String Status;
    ArrayList<DataEntryModel> model = new ArrayList<>();

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public ArrayList<DataEntryModel> getModel() {
        return model;
    }

    public void setModel(ArrayList<DataEntryModel> model) {
        this.model = model;
    }
}
