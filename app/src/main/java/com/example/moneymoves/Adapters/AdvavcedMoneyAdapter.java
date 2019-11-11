package com.example.moneymoves.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymoves.Database.Entities.BudgetTemplate;
import com.example.moneymoves.R;

import java.util.ArrayList;
import java.util.List;

public class AdvavcedMoneyAdapter extends RecyclerView.Adapter<AdvavcedMoneyAdapter.BudgetHolder>
{
	private List<BudgetTemplate> budgets = new ArrayList<>();

	@NonNull @Override public BudgetHolder onCreateViewHolder(@NonNull ViewGroup parent,
			int viewType)
	{
		View itemView = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.budget_item, parent, false);
		return new BudgetHolder(itemView);
	}

	@Override public void onBindViewHolder(@NonNull BudgetHolder holder, int position)
	{
		BudgetTemplate curr = budgets.get(position);
		holder.category.setText(curr.getCategory());
		holder.amount.setText(String.valueOf(curr.getAmount()));
	}

	@Override public int getItemCount()
	{
		return budgets.size();
	}

	public void setBudgets(List<BudgetTemplate> budgets)
	{
		this.budgets = budgets;
		// Will replace later with delete and insert
		notifyDataSetChanged();
	}

	class BudgetHolder extends RecyclerView.ViewHolder
	{
		private TextView category;
		private EditText amount;

		public BudgetHolder(@NonNull View itemView)
		{
			super(itemView);
			category = itemView.findViewById(R.id.budgetCategory);
			amount = itemView.findViewById(R.id.budgetAmount);
		}
	}
}
