package com.example.moneymoves.Pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymoves.Adapters.AdvavcedMoneyAdapter;
import com.example.moneymoves.Adapters.CategoryAdapter;
import com.example.moneymoves.Database.Entities.BudgetTemplate;
import com.example.moneymoves.Database.Entities.MonthlySpent;
import com.example.moneymoves.R;
import com.example.moneymoves.ViewModels.ProgressBarViewModel;
import com.example.moneymoves.ViewModels.SpentPageViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CategoryPage extends AppCompatActivity
{
	public static final int ADD_NOTE_REQUEST = 1;
	public static final int EDIT_NOTE_REQUEST = 2;
	public static final String EXTRA_ID = "ID";
	public static final String EXTRA_CATEGORY = "CATEGORY";
	public static final String EXTRA_AMOUNT = "AMOUNT";
	public static String category;

	CategoryAdapter adapter;
	private SpentPageViewModel spentViewModel;
	private ProgressBarViewModel progressBarViewModel;

	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category_page);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton addSpentButton = findViewById(R.id.addSpentButton);
		addSpentButton.setOnClickListener(new View.OnClickListener()
		{
			@Override public void onClick(View view)
			{
				Intent intent = new Intent(CategoryPage.this, AddSpentPage.class);
				startActivityForResult(intent, ADD_NOTE_REQUEST);
			}
		});

		final TextView x = findViewById(R.id.noteAmount);
		TextView y = findViewById(R.id.budgetCategory);

		Intent intent = getIntent();
		final Double budgetAmount = intent.getDoubleExtra(EXTRA_AMOUNT, 0.0);
		category = intent.getStringExtra(EXTRA_CATEGORY);
		y.setText(category);

		RecyclerView recyclerView = findViewById(R.id.recyclerView);
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		layoutManager.setOrientation(RecyclerView.VERTICAL);

		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setHasFixedSize(true);
		adapter = new CategoryAdapter();
		recyclerView.setAdapter(adapter);

		new ItemTouchHelper(
				new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT)
				{
					@Override public boolean onMove(@NonNull RecyclerView recyclerView,
							@NonNull RecyclerView.ViewHolder viewHolder,
							@NonNull RecyclerView.ViewHolder target)
					{
						return false;
					}

					@Override public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,
							int direction)
					{
						spentViewModel.deleteSpent(
								adapter.getNoteAmount(viewHolder.getAdapterPosition()));
						Toast.makeText(CategoryPage.this, "Expense Deleted", Toast.LENGTH_SHORT);
					}
				}).attachToRecyclerView(recyclerView);

		spentViewModel = ViewModelProviders.of(this).get(SpentPageViewModel.class);
		spentViewModel.setAllSpent(category);
		spentViewModel.getAllSpent().observe(this, new Observer<List<MonthlySpent>>()
		{
			@Override public void onChanged(List<MonthlySpent> noteAmounts)
			{
				adapter.setNoteAmounts(noteAmounts);
			}
		});

		spentViewModel.getSumAmountOfCategory(category).observe(this, new Observer<Double>()
		{
			@Override public void onChanged(Double aDouble)
			{
				if (aDouble == null)
				{
					x.setText(0.0 + "/" + budgetAmount + " available");
				}
				else
				{
					x.setText(budgetAmount-aDouble + "/" + budgetAmount + " available");
				}
			}
		});

		adapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
			@Override
			public void onItemClickListener(MonthlySpent spentItem) {
				Intent intent2 = new Intent(CategoryPage.this, AddSpentPage.class);
				intent2.putExtra(AddSpentPage.EXTRA_ID, spentItem.getId());
				intent2.putExtra(AddSpentPage.EXTRA_NOTE, spentItem.getName());
				intent2.putExtra(AddSpentPage.EXTRA_AMOUNT, spentItem.getAmount());
				startActivityForResult(intent2,EDIT_NOTE_REQUEST);
			}
		});


		final CategoryPage page = this;
		final ProgressBar bar = findViewById(R.id.pb_red_progress);
		progressBarViewModel = ViewModelProviders.of(this).get(ProgressBarViewModel.class);

		bar.setMax(budgetAmount.intValue());

		//bar.setMax(budgetAmount.intValue());

				progressBarViewModel.getSumAmountOfCategory(category).observe(page, new Observer<Double>()
				{
					@Override public void onChanged(Double budget)
					{
						if (budget == null)
						{
							bar.setProgress(budgetAmount.intValue());
						}
						else
						{

							bar.setProgress(budgetAmount.intValue() - budget.intValue());
						}
					}
				});

			}


	@Override protected void onActivityResult(int requestCode, int resultCode,
			@Nullable Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK)
		{
			assert data != null;
			String note = data.getStringExtra(AddSpentPage.EXTRA_NOTE);
			double amount = data.getDoubleExtra(AddSpentPage.EXTRA_AMOUNT, 0.0);
			MonthlySpent spentItem = new MonthlySpent(category, note, amount);
			spentViewModel.insertNote(spentItem);
			Toast.makeText(this, "New Note Saved", Toast.LENGTH_SHORT).show();
		}
		else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK)
		{
			int id = data.getIntExtra(AddSpentPage.EXTRA_ID, -1);
			if (id == -1)
			{
				Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show();
				return;
			}
			String note = data.getStringExtra(AddSpentPage.EXTRA_NOTE);
			double amount = data.getDoubleExtra(AddExpensePage.EXTRA_AMOUNT, 0.0);
			MonthlySpent spentItem = new MonthlySpent(category, note, amount);
			spentItem.setId(id);
			spentViewModel.updateNote(spentItem);

			Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT).show();
		}
		else
		{
			Toast.makeText(this, "Budget Not Saved", Toast.LENGTH_SHORT).show();
		}
	}

	public void mainPage(View view)
	{
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

}

