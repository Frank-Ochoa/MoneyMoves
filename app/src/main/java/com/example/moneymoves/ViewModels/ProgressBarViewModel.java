package com.example.moneymoves.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moneymoves.Database.Entities.BudgetTemplate;
import com.example.moneymoves.Database.Entities.Income;
import com.example.moneymoves.Database.MoneyRepository;

import java.util.List;

public class ProgressBarViewModel extends AndroidViewModel {

    private MoneyRepository repository;
    private LiveData<List<BudgetTemplate>> allBudgets;

    public ProgressBarViewModel(@NonNull Application application)
    {
        super(application);
        repository = new MoneyRepository(application);
        allBudgets = repository.getAllBudgets();
    }

    //returns a list of all incomes
    public LiveData<List<Income>> getAllIncome() {return repository.getAllIncome();}

    //returns a sum of all budgets as a double
    public double sumBudgets()
    {
        return repository.sumBudgets();
    }


}
