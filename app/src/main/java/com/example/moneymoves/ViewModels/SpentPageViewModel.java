package com.example.moneymoves.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moneymoves.Database.MoneyRepository;
import com.example.moneymoves.Database.POJOs.NoteAmount;

import java.util.List;

public class SpentPageViewModel extends AndroidViewModel
{
    private MoneyRepository repository;
    private LiveData<List<NoteAmount>> allSpent;
    private LiveData<Double> totalAmountSpent;

    public SpentPageViewModel(@NonNull Application application)
    {
        super(application);
        repository = new MoneyRepository(application);
    }

    public LiveData<List<NoteAmount>> getAllSpent()
    {
        return allSpent;
    }

    public LiveData<Double> getSumAmountOfCategory(String cat)
    {
        if (totalAmountSpent == null)
        {
            return new LiveData<Double>()
            {
                @Nullable @Override public Double getValue()
                {
                    return 0.0;
                }
            };
        }
        else
        {
            return totalAmountSpent;
        }
    }

    public void setSumAmountOfCategory(String cat)
    {
        totalAmountSpent = repository.getSumAmountOfCategory(cat);
    }

    public void setAllSpent(String cat){
        allSpent = repository.getAllCategoryAmount(cat);
    }



}
