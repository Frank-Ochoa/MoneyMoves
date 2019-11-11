package com.example.moneymoves.Pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymoves.Adapters.AdvavcedMoneyAdapter;
import com.example.moneymoves.Database.Entities.BudgetTemplate;
import com.example.moneymoves.R;
import com.example.moneymoves.ViewModels.BudgetTemplateViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ExpensesPage extends AppCompatActivity
{

	public static final int ADD_BUDGET_REQUEST = 1;
	AdvavcedMoneyAdapter adapter;
	private BudgetTemplateViewModel budgetTemplateViewModel;

	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expenses_page);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton addExpenseButton = findViewById(R.id.addExpenseButton);
		addExpenseButton.setOnClickListener(new View.OnClickListener()
		{
			@Override public void onClick(View view)
			{
				Intent intent = new Intent(ExpensesPage.this, AddExpensePage.class);
				startActivityForResult(intent, ADD_BUDGET_REQUEST);
			}
		});

		RecyclerView recyclerView = findViewById(R.id.recyclerView);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		adapter = new AdvavcedMoneyAdapter();
		recyclerView.setAdapter(adapter);

		// Destroys when finished
		budgetTemplateViewModel = ViewModelProviders.of(this).get(BudgetTemplateViewModel.class);
		budgetTemplateViewModel.getAllBudgets().observe(this, new Observer<List<BudgetTemplate>>()
		{
			@Override public void onChanged(List<BudgetTemplate> budgetTemplates)
			{
				// update our RecyclerView
				adapter.setBudgets(budgetTemplates);
			}
		});
	}

	@Override protected void onActivityResult(int requestCode, int resultCode,
			@Nullable Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == ADD_BUDGET_REQUEST && resultCode == RESULT_OK)
		{
			assert data != null;
			String category = data.getStringExtra(AddExpensePage.EXTRA_CATEGORY);
			double amount = data.getDoubleExtra(AddExpensePage.EXTRA_AMOUNT, 0.0);

			BudgetTemplate budgetTemplate = new BudgetTemplate(category, amount);
			budgetTemplateViewModel.insertBudget(budgetTemplate);

			Toast.makeText(this, "Budget Saved", Toast.LENGTH_SHORT).show();
		}
		else
		{
			Toast.makeText(this, "Budget Not Saved", Toast.LENGTH_SHORT).show();
		}
	}

	public void addExpensePage(View view)
	{
		Intent intent = new Intent(this, AddExpensePage.class);
		startActivity(intent);
	}

}
