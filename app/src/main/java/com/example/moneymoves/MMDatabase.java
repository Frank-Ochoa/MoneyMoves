package com.example.moneymoves;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MMDatabase extends SQLiteOpenHelper
{

    private static final String TAG = "MMDATABASE :: ";

    public static final String INCOME_TABLE = "INCOME";
    public static final String INCOME = "income";
    private static final String CREATE_INCOME_TABLE =
            "create table " + INCOME_TABLE + " ("+
                    INCOME + " REAL"+
                    ")";


    public static final String MONTHLY_SPENT_TABLE = "MONTHLY_SPENT";
    public static final String CATEGORY = "category";
    public static final String NAME = "name";
    public static final String AMOUNT = "amount";
    private static final String CREATE_MONTHLYSPENT_TABLE =
            "create table " + MONTHLY_SPENT_TABLE + " ("+
                    CATEGORY + " TEXT NOT NULL," +
                    NAME + " TEXT NOT NULL," +
                    AMOUNT + " REAL NOT NULL" +
                    ")";

    public static final String BUDGET_TEMPLATE_TABLE = "BUDGET_TEMPLATE";
    public static final String BUDGET_ID = "budgetid";
    private static final String CREATE_BUDGETTEMPLATE_TABLE =
            "create table " + BUDGET_TEMPLATE_TABLE + " (" +
                    BUDGET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    CATEGORY + " TEXT NOT NULL," +
                    AMOUNT + " REAL NOT NULL" +
                    ")";

    public static final String MONTHLY_RECORD_TABLE = "MONTHLY_RECORD";
    public static final String MONTH_NAME = "month";
    public static final String MONTHLY_AMOUNT = "amount";
    private static final String CREATE_MONTHLYRECORD_TABLE =
            "create table " + MONTHLY_RECORD_TABLE + " (" +
                    MONTH_NAME + " TEXT NOT NULL," +
                    MONTHLY_AMOUNT + " REAL NOT NULL" +
                    ")";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MoneyMovesDB.db";

    public MMDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i(TAG, "ON CREATE");

        db.execSQL(CREATE_INCOME_TABLE);
        db.execSQL(CREATE_BUDGETTEMPLATE_TABLE);
        db.execSQL(CREATE_MONTHLYSPENT_TABLE);
        db.execSQL(CREATE_MONTHLYRECORD_TABLE);
    }

    public void nukeTable(String tableName)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " + tableName);
    }

    public void nukeAllTables()
    {
        Log.i(TAG, "NUKE ALL");

        SQLiteDatabase db = this.getWritableDatabase();
        String dropTable = "DROP TABLE IF EXISTS ";

        db.execSQL(dropTable + INCOME_TABLE);
        db.execSQL(dropTable + MONTHLY_SPENT_TABLE);
        db.execSQL(dropTable + BUDGET_TEMPLATE_TABLE);
        db.execSQL(dropTable + MONTHLY_RECORD_TABLE);
    }

    public void addTable(SQLiteDatabase db, String createTable)
    {
        db.execSQL(createTable);
    }

    public void reAddExistingTables()
    {
        Log.i(TAG, "RE-ADD EXISTING");
        SQLiteDatabase db = this.getWritableDatabase();
        onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertIncomeRow(double income)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(INCOME, income);

        db.insert(INCOME_TABLE, null, values);
    }

    public void insertSpentRow( String category, String name, double amount)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CATEGORY, category);
        values.put(NAME, name);
        values.put(AMOUNT, amount);

        db.insert(MONTHLY_SPENT_TABLE, null, values);

    }

    public void insertBudgetRow(String category, double amount)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CATEGORY, category);
        values.put(AMOUNT, amount);

        db.insert(BUDGET_TEMPLATE_TABLE, null, values);
    }

    public void insertMonthlyRecordRow(String month, double amount)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MONTH_NAME, month);
        values.put(MONTHLY_AMOUNT, amount);

        db.insert(MONTHLY_RECORD_TABLE, null, values);
    }

    public void removeRowFromTable(String tableName, String where, int id)
    {
        Log.i(TAG, "REMOVED ROW " + id);
        SQLiteDatabase db = this.getWritableDatabase();

        //db.execSQL("DELETE FROM " + tableName + " WHERE ? = ?", new String[]{where, String.valueOf(id)});
        db.execSQL("DELETE FROM " + tableName + " WHERE " + BUDGET_ID + " = " + id);

        // This is a little wonky, might try and figure out how to do this better, necessary for adapter though
        db.execSQL("UPDATE " + tableName + " SET " + BUDGET_ID + " = " + BUDGET_ID + " - 1" + " WHERE " + BUDGET_ID + " != 1");
    }

    public Cursor getBudgetRow(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.i(TAG, String.valueOf(id));

        return db.rawQuery("SELECT " + CATEGORY + ", " + AMOUNT + " FROM " + BUDGET_TEMPLATE_TABLE +
                " WHERE " + BUDGET_ID + " = ?",  new String[]{String.valueOf(id)});
    }


    public int getNumEntries(String tableName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return Long.valueOf(DatabaseUtils.queryNumEntries(db, tableName)).intValue();
    }
}
