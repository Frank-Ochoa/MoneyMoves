package com.example.moneymoves.Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "BUDGET_TEMPLATE")
public class BudgetTemplate implements IEntity
{
	@PrimaryKey(autoGenerate = true)
	private int id;

	private String category;

	private double amount;

	public BudgetTemplate(String category, double amount)
	{
		this.category = category;
		this.amount = amount;
	}

	public int getId()
	{
		return id;
	}

	public String getCategory()
	{
		return category;
	}

	public double getAmount()
	{
		return amount;
	}

	public void setId(int id)
	{
		this.id = id;
	}
}
