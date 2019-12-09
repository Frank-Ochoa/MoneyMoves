package com.example.moneymoves.Database.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.moneymoves.Database.Entities.BudgetTemplate;

import java.util.List;

@Dao
public abstract class BudgetTemplateDao implements IDao<BudgetTemplate>
{

	@Query("DELETE FROM BUDGET_TEMPLATE") public abstract void deleteAllBudgets();

	@Query("SELECT * FROM BUDGET_TEMPLATE") public abstract LiveData<List<BudgetTemplate>> getAllBudgets();

	@Query(("SELECT SUM(amount) FROM BUDGET_TEMPLATE")) public abstract LiveData<Double> sumBudgets();

	@Query("SELECT amount FROM BUDGET_TEMPLATE WHERE CATEGORY = :cat") public abstract LiveData<Double> getCategoryBudget(String cat);
}
