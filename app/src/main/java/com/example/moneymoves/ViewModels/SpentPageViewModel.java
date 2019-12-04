package com.example.moneymoves.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moneymoves.Database.Entities.BudgetTemplate;
import com.example.moneymoves.Database.MoneyRepository;
import com.example.moneymoves.Database.POJOs.CategoryAmount;

import java.util.List;

public class SpentPageViewModel extends AndroidViewModel
{
    private MoneyRepository repository;
    private LiveData<List<CategoryAmount>> allSpent;
    private LiveData<Double> totalAmountSpent;

    public SpentPageViewModel(@NonNull Application application)
    {
        super(application);
        repository = new MoneyRepository(application);
        allSpent = repository.getAllCategoryAmount();
    }

    public LiveData<List<CategoryAmount>> getAllBudgets()
    {
        return allSpent;
    }

    public LiveData<Double> getSumAmountOfCategory(String cat)
    {
        return repository.getSumAmountOfCategory(cat);
    }
}
