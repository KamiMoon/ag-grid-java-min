package com.agridexample.demo.aggrid.response;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EnterpriseGetRowsResponse {
    private List<Object> data = new ArrayList<>();
    private int lastRow;
    private List<String> secondaryColumnFields;

    public EnterpriseGetRowsResponse() { }

    public EnterpriseGetRowsResponse(List<Object> data, int lastRow, List<String> secondaryColumnFields) {
        this.data = data;
        this.lastRow = lastRow;
        this.secondaryColumnFields = secondaryColumnFields;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public int getLastRow() {
        return lastRow;
    }

    public void setLastRow(int lastRow) {
        this.lastRow = lastRow;
    }

    public List<String> getSecondaryColumnFields() {
        return secondaryColumnFields;
    }

    public void setSecondaryColumns(List<String> secondaryColumnFields) {
        this.secondaryColumnFields = secondaryColumnFields;
    }

    @Override
    public String toString() {
        return "EnterpriseGetRowsResponse{" +
                "data=" + data +
                ", lastRow=" + lastRow +
                ", secondaryColumnFields=" + secondaryColumnFields +
                '}';
    }
}
