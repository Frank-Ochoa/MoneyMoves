package com.example.moneymoves.Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "MONTHLY_SPENT")
public class MonthlySpent implements IEntity
{
	@PrimaryKey(autoGenerate = true)
	private int id;

	private String category;

	private String name;

	private double amount;

	public MonthlySpent(String category, String name, double amount)
	{
		this.category = category;
		this.name = name;
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

	public String getName()
	{
		return name;
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
