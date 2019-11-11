package com.example.moneymoves.Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "INCOME")
public class Income implements IEntity
{
	@PrimaryKey
	private double income;

	public Income(double income)
	{
		this.income = income;
	}

	public double getIncome()
	{
		return income;
	}

	public void setIncome(double income)
	{
		this.income = income;
	}
}
