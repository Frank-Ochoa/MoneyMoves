package com.example.moneymoves.Database.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.moneymoves.Database.Entities.MonthlySpent;

import java.util.List;

@Dao public abstract class MonthlySpentDao implements IDao<MonthlySpent>
{

	@Query("SELECT * FROM MONTHLY_SPENT") public abstract LiveData<List<MonthlySpent>> getMonthlySpent();
}
