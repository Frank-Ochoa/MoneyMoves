package com.example.moneymoves.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moneymoves.Database.Entities.BudgetTemplate;
import com.example.moneymoves.Database.MoneyRepository;

import java.util.List;

public class MainActivityViewModel  extends AndroidViewModel
{
    private MoneyRepository repository;
    private LiveData<List<BudgetTemplate>> allBudgets;

    public MainActivityViewModel(@NonNull Application application)
    {
        super(application);
        repository = new MoneyRepository(application);
        allBudgets = repository.getAllBudgets();
    }

    public LiveData<List<BudgetTemplate>> getAllBudgets()
    {
        return allBudgets;
    }

    public LiveData<Double> getSumAmountOfCategory(String cat)
    {
        return repository.getSumAmountOfCategory(cat);
    }
}