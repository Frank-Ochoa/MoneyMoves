package com.example.moneymoves;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MoneyAdapter extends RecyclerView.Adapter<MoneyAdapter.ViewHolder>
{

	private static final String TAG = "MoneyAdapter :: ";
	// Expense row id in the database that we then pass in here
	private MMDatabase dbHelper;

	public MoneyAdapter(Context context)
	{
		this.dbHelper = new MMDatabase(context);
	}

	@NonNull @Override public MoneyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
			int viewType)
	{
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View v = inflater.inflate(R.layout.expenserow, parent, false);
		return new ViewHolder(v);
	}

	@Override public void onBindViewHolder(@NonNull MoneyAdapter.ViewHolder holder, int id)
	{
		holder.bindData(id + 1);
	}

	@Override public int getItemCount()
	{
		return dbHelper.getNumEntries(MMDatabase.BUDGET_TEMPLATE_TABLE);
	}

	class ViewHolder extends RecyclerView.ViewHolder
	{
		TextView expenseName;
		EditText expenseAmount;
		Button deleteButton;

		ViewHolder(@NonNull View itemView)
		{
			super(itemView);
			expenseName = itemView.findViewById(R.id.expenseName);
			expenseAmount = itemView.findViewById(R.id.expenseAmount);
			deleteButton = itemView.findViewById(R.id.deleteButton);
		}

		void bindData(final int id)
		{
			Log.i(TAG, "ID IS : " + id);
			// Will query DB based on a unique ID that we have in the table now
			final Cursor cursor = dbHelper.getBudgetRow(id);
			cursor.moveToNext();

			expenseName.setText(cursor.getString(cursor.getColumnIndex(MMDatabase.CATEGORY)));
			expenseName.setTextColor(Color.BLACK);

            expenseAmount.setText(cursor.getString(cursor.getColumnIndex(MMDatabase.AMOUNT)));
			expenseAmount.setTextColor(Color.BLUE);
			deleteButton.setOnClickListener(new View.OnClickListener()
			{
				@Override public void onClick(View view)
				{
					// Remove row from Table
					dbHelper.removeRowFromTable(MMDatabase.BUDGET_TEMPLATE_TABLE, MMDatabase.BUDGET_ID, id);

					// Remove the view
					MoneyAdapter.this.notifyItemRemoved(id - 1);
					MoneyAdapter.this.notifyItemRangeChanged(id - 1, getItemCount());

				}
			});

			cursor.close();

		}
	}

}
