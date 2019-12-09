package com.example.moneymoves.Pages;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymoves.Adapters.CategoryAdapter;
import com.example.moneymoves.Database.Entities.MonthlySpent;
import com.example.moneymoves.R;
import com.example.moneymoves.ViewModels.SpentPageViewModel;

import java.util.List;

public class CategoryPage extends AppCompatActivity
{

	public static final String EXTRA_ID = "ID";
	public static final String EXTRA_CATEGORY = "CATEGORY";
	public static final String EXTRA_AMOUNT = "AMOUNT";

	CategoryAdapter adapter;
	private SpentPageViewModel spentViewModel;

	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category_page);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		final TextView x = findViewById(R.id.noteAmount);
		TextView y = findViewById(R.id.budgetCategory);

		Intent intent = getIntent();
		final Double budgetAmount = intent.getDoubleExtra(EXTRA_AMOUNT, 0.0);
		String cat = intent.getStringExtra(EXTRA_CATEGORY);
		y.setText(cat);

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
		spentViewModel.setAllSpent(cat);
		spentViewModel.getAllSpent().observe(this, new Observer<List<MonthlySpent>>()
		{
			@Override public void onChanged(List<MonthlySpent> noteAmounts)
			{
				adapter.setNoteAmounts(noteAmounts);
			}
		});

		spentViewModel.getSumAmountOfCategory(cat).observe(this, new Observer<Double>()
		{
			@Override public void onChanged(Double aDouble)
			{
				if (aDouble == null)
				{
					x.setText(0.0 + "/" + budgetAmount);
				}
				else {
					x.setText(aDouble + "/" + budgetAmount);
				}
			}
		});
	}

}
