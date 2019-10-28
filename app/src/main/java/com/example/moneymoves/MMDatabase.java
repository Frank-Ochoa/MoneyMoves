package com.example.moneymoves;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MMDatabase extends SQLiteOpenHelper
{

    public static final String INCOME = "income";
    private static final String CREATE_INCOME_TABLE =
            "create table Income ("+
                    INCOME + " Real, pimary"+
                    ")";

    public static final String TEMP_CATEGORY = "category";
    public static final String TEMP_NAME = "name";
    public static final String TEMP_AMOUNT = "amount";
    private static final String CREATE_TEMPEXPENSE_TABLE =
            "create table TEMP_EXPENSES ("+
                    TEMP_CATEGORY + " Text," +
                    TEMP_NAME + " Text," +
                    TEMP_AMOUNT + " Real" +
                    ")";

    public static final String PERSISTENT_CATEGORY = "category";
    public static final String PERSISTENT_NAME = "name";
    public static final String PERSISTENT_AMOUNT = "amount";
    private static final String CREATE_PERSISTENTEXPENSE_TABLE =
            "create table PERSISTENT_EXPENSES ("+
                    PERSISTENT_CATEGORY + " Text," +
                    PERSISTENT_NAME + " Text," +
                    PERSISTENT_AMOUNT + " Real" +
                    ")";

    public static final String MONTH_NAME = "month";
    public static final String MONTHLY_AMOUNT = "amount";
    private static final String CREATE_MONTHLYRECORD_TABLE =
            "create table MONTHLY_RECORD (" +
                    MONTH_NAME + " Text," +
                    MONTHLY_AMOUNT + " Real" +
                    ")";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MoneyMovesDB.db";

    public MMDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_INCOME_TABLE);
        db.execSQL(CREATE_TEMPEXPENSE_TABLE);
        db.execSQL(CREATE_PERSISTENTEXPENSE_TABLE);
        db.execSQL(CREATE_MONTHLYRECORD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
