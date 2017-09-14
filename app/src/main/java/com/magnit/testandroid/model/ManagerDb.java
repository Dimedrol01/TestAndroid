package com.magnit.testandroid.model;


import java.util.ArrayList;

public interface ManagerDb {
    void addValue(Values value, String nameTable, String[] nameColumn);
    void updateValue(Values value);
    boolean existsRecords(String nameTable);
    ArrayList<Values> getListValues(String nameTable);
    Values getValueByPosition(int position);
}
