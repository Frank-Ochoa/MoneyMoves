package com.example.moneymoves.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moneymoves.Database.Entities.BudgetTemplate;
import com.example.moneymoves.Database.MoneyRepository;

import java.util.List;

public class BudgetTemplateViewModel extends AndroidViewModel
{
	private MoneyRepository repository;
	private LiveData<List<BudgetTemplate>> allBudgets;

	public BudgetTemplateViewModel(@NonNull Application application)
	{
		super(application);
		repository = new MoneyRepository(application);
		allBudgets = repository.getAllBudgets();
	}

	public void insertBudget(BudgetTemplate budgetTemplate)
	{
		repository.insertBudget(budgetTemplate);
	}

	public void updateBudget(BudgetTemplate budgetTemplate)
	{
		repository.updateBudget(budgetTemplate);
	}

	public void deleteBudget(BudgetTemplate budgetTemplate)
	{
		repository.deleteBudget(budgetTemplate);
	}

	public void deleteAllBudgets()
	{
		repository.deleteAllBudgets();
	}

	public LiveData<List<BudgetTemplate>> getAllBudgets()
	{
		return allBudgets;
	}
}
