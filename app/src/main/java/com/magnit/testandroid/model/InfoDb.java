package com.magnit.testandroid.model;


import android.provider.BaseColumns;

public abstract class InfoDb {

    public static abstract class DbEntries implements BaseColumns {
        public static final String TABLE_NAME = "entries";
        static final String COLUMN_NAME_ID = "id_main";
        public static final String COLUMN_NAME_VALUE = "value";
        public static final String COLUMN_NAME_RATIO = "ratio";
    }

    public static abstract class DbHistory implements BaseColumns {
        public static final String TABLE_NAME = "history";
        private static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_ID_MAIN = "id_main";
        public static final String COLUMN_NAME_RATIO = "ratio";

    }


    private static final String INTEGER_TYPE = "INTEGER";
    private static final String FLOAT_TYPE = "REAL";
    static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s %s, %s %s);",
            DbEntries.TABLE_NAME, DbEntries.COLUMN_NAME_ID, DbEntries.COLUMN_NAME_VALUE, INTEGER_TYPE, DbEntries.COLUMN_NAME_RATIO, FLOAT_TYPE);
    static final String SQL_CREATE_HISTORY = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s %s, %s %s, FOREIGN KEY(%s) REFERENCES %s(%s));",
            DbHistory.TABLE_NAME, DbHistory.COLUMN_NAME_ID, DbHistory.COLUMN_NAME_ID_MAIN, INTEGER_TYPE, DbHistory.COLUMN_NAME_RATIO, FLOAT_TYPE, DbHistory.COLUMN_NAME_ID_MAIN, DbEntries.TABLE_NAME, DbEntries.COLUMN_NAME_ID);

    static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DbEntries.TABLE_NAME;
    static final String SQL_DELETE_HISTORY = "DROP TABLE IF EXISTS " + DbHistory.TABLE_NAME;
}
