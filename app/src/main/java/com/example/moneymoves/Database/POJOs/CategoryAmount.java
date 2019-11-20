package com.example.moneymoves.Database.POJOs;

import androidx.room.ColumnInfo;

public class CategoryAmount
{
	@ColumnInfo(name = "name")
	public String note;

	@ColumnInfo(name = "amount")
	public double amount;
}
