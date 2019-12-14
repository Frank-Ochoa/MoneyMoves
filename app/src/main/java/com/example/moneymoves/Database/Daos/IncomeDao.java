package com.example.moneymoves.Database.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.moneymoves.Database.Entities.Income;

@Dao public abstract class IncomeDao implements IDao<Income>
{

	@Query("SELECT sum(SALARY) FROM INCOME") public abstract LiveData<Double> getIncome();

	@Query("DELETE FROM INCOME") public abstract void deleteAllIncome();
}
