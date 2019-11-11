package com.example.moneymoves.Database.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;


@Dao public interface IDao<T>
{
	@Insert void insert(T entity);

	@Update void update(T entity);

	@Delete void delete(T delete);
}
