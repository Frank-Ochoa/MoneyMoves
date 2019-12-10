package com.example.moneymoves.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.moneymoves.Database.Daos.BudgetTemplateDao;
import com.example.moneymoves.Database.Daos.IncomeDao;
import com.example.moneymoves.Database.Daos.MonthlyRecordDao;
import com.example.moneymoves.Database.Daos.MonthlySpentDao;
import com.example.moneymoves.Database.Entities.BudgetTemplate;
import com.example.moneymoves.Database.Entities.Income;
import com.example.moneymoves.Database.Entities.MonthlyRecord;
import com.example.moneymoves.Database.Entities.MonthlySpent;

@Database(entities = { BudgetTemplate.class, Income.class, MonthlyRecord.class, MonthlySpent.class }, version = 1, exportSchema = false)
public abstract class MoneyDatabase extends RoomDatabase
{
	private static MoneyDatabase instance;

	public abstract BudgetTemplateDao budgetTemplateDao();

	public abstract IncomeDao incomeDao();

	public abstract MonthlyRecordDao monthlyRecordDao();

	public abstract MonthlySpentDao monthlySpentDao();

	public static synchronized MoneyDatabase getInstance(Context context)
	{
		// Only instantiate if has never been constructed
		if (instance == null)
		{
			instance = Room.databaseBuilder(context.getApplicationContext(), MoneyDatabase.class,
					"Money_Database").fallbackToDestructiveMigration().build();
		}

		return instance;
	}


}
