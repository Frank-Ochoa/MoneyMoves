package com.example.moneymoves.Database.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.moneymoves.Database.Entities.Income;

import java.util.List;

@Dao public abstract class IncomeDao implements IDao<Income>
{

	@Query("SELECT * FROM INCOME") public abstract LiveData<List<Income>> getIncome();
}
