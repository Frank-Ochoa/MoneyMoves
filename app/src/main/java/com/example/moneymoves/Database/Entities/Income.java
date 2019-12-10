package com.example.moneymoves.Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "INCOME")
public class Income implements IEntity
{
	@PrimaryKey(autoGenerate = true)
	private int id;

	private double salary;

	public Income(double salary)
	{
		this.salary = salary;
	}

	public double getSalary()
	{
		return salary;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

}
