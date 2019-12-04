package com.example.moneymoves.Database.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.moneymoves.Database.Entities.MonthlySpent;
import com.example.moneymoves.Database.POJOs.NoteAmount;

import java.util.List;

@Dao public abstract class MonthlySpentDao implements IDao<MonthlySpent>
{

	@Query("SELECT * FROM MONTHLY_SPENT") public abstract LiveData<List<MonthlySpent>> getMonthlySpent();

	@Query("SELECT NAME, AMOUNT FROM MONTHLY_SPENT WHERE CATEGORY = :cat ")
	public abstract LiveData<List<NoteAmount>> getAllInCategory(
			String cat);

	@Query("SELECT sum(AMOUNT) FROM MONTHLY_SPENT WHERE CATEGORY = :cat")
	public abstract LiveData<Double> getSumAmountOfCategory(String cat);

}
