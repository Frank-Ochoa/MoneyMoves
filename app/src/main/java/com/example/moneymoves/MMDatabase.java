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


    public static final String CATEGORY = "category";
    public static final String NAME = "name";
    public static final String AMOUNT = "amount";
    private static final String CREATE_MONTHLYSPENT_TABLE =
            "create table PERSISTENT_EXPENSES ("+
                    CATEGORY + " Text," +
                    NAME + " Text," +
                    AMOUNT + " Real" +
                    ")";

    private static final String CREATE_BUDGETTEMPLATE_TABLE =
            "create table BUDGET_TEMPLATE (" +
                    CATEGORY + " Text," +
                    AMOUNT + " Text" +
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
        db.execSQL(CREATE_BUDGETTEMPLATE_TABLE);
        db.execSQL(CREATE_MONTHLYSPENT_TABLE);
        db.execSQL(CREATE_MONTHLYRECORD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
