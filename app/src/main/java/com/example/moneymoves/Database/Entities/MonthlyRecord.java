package com.example.moneymoves.Database.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = "MONTHLY_RECORD", primaryKeys = {"month", "year"})
public class MonthlyRecord implements IEntity
{
	@NonNull
	private String month, year;

	public MonthlyRecord(String month, String year)
	{
		this.month = month;
		this.year = year;
	}

	public String getMonth()
	{
		return month;
	}

	public void setMonth(String month)
	{
		this.month = month;
	}

	public String getYear()
	{
		return year;
	}

	public void setYear(String year)
	{
		this.year = year;
	}
}
