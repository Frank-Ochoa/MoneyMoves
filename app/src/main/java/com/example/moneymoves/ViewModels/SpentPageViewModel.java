package com.example.moneymoves.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moneymoves.Database.Entities.MonthlySpent;
import com.example.moneymoves.Database.MoneyRepository;

import java.util.List;

public class SpentPageViewModel extends AndroidViewModel
{
	private MoneyRepository repository;
	private LiveData<List<MonthlySpent>> allSpent;
	private LiveData<Double> totalAmountSpent;

	public SpentPageViewModel(@NonNull Application application)
	{
		super(application);
		repository = new MoneyRepository(application);
	}

	public LiveData<List<MonthlySpent>> getAllSpent()
	{
		return allSpent;
	}


	public void setAllSpent(String cat)
	{
		allSpent = repository.getAllMonthlySpentFromCat(cat);
	}

	public LiveData<Double> getSumAmountOfCategory(String cat)
	{
		return repository.getSumAmountOfCategory(cat);
	}

	public void deleteSpent(MonthlySpent monthlySpent)
	{
		repository.deleteMonthlySpent(monthlySpent);
	}


	public void setSumAmountOfCategory(String cat)
	{
		totalAmountSpent = repository.getSumAmountOfCategory(cat);
	}

	public void insertNote(MonthlySpent note)
	{
		this.repository.insertMonthlySpent(note);
	}

	public void updateNote(MonthlySpent note){
		this.repository.updateMonthlySpent(note);
	}
}
