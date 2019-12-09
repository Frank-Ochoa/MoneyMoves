package com.example.moneymoves.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
	private OnItemClickListener listener;

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

	public BudgetTemplate getBudget(int position)
	{
		return budgets.get(position);
	}

	class BudgetHolder extends RecyclerView.ViewHolder
	{
		private TextView category;
		private TextView amount;

		public BudgetHolder(@NonNull View itemView)
		{
			super(itemView);
			category = itemView.findViewById(R.id.budgetCategory);
			amount = itemView.findViewById(R.id.spentAmount);

			itemView.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v){
					int position = getAdapterPosition();
					//make sure the listener has been created
					//make sure that a deleted item isnt clicked before the row has been removed
					if(listener != null && position != RecyclerView.NO_POSITION) {
						listener.onItemClickListener(budgets.get(position));
					}
				}
			});
		}
	}

	public interface OnItemClickListener{
		void onItemClickListener(BudgetTemplate budget); //passing in the budget that we want to update
	}

	public void setOnItemClickListener(OnItemClickListener listener){
		this.listener = listener;
	}
}
