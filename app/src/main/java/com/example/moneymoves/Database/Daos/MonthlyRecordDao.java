package com.example.moneymoves.Database.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.moneymoves.Database.Entities.MonthlyRecord;

import java.util.List;

@Dao public abstract class MonthlyRecordDao implements IDao<MonthlyRecord>
{
	@Query("SELECT * FROM MONTHLY_RECORD") public abstract LiveData<List<MonthlyRecord>> getMonthlyRecords();
}
