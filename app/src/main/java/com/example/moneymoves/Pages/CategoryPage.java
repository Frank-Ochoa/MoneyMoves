package com.example.moneymoves.Pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymoves.Adapters.CategoryAdapter;
import com.example.moneymoves.Database.Entities.BudgetTemplate;
import com.example.moneymoves.Database.Entities.MonthlySpent;
import com.example.moneymoves.Database.POJOs.NoteAmount;
import com.example.moneymoves.R;
import com.example.moneymoves.ViewModels.SpentPageViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.Month;
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
		Double initialSpent = 0.0;
		x.setText(initialSpent + "/" + budgetAmount);

		RecyclerView recyclerView = findViewById(R.id.recyclerView);
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		layoutManager.setOrientation(RecyclerView.VERTICAL);

		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setHasFixedSize(true);
		adapter = new CategoryAdapter();
		recyclerView.setAdapter(adapter);

		spentViewModel = ViewModelProviders.of(this).get(SpentPageViewModel.class);
		spentViewModel.setAllSpent(category);
		spentViewModel.getAllSpent().observe(this, new Observer<List<NoteAmount>>()
		{
			@Override public void onChanged(List<NoteAmount> noteAmounts)
			{
				adapter.setNoteAmounts(noteAmounts);
			}
		});


		spentViewModel.getSumAmountOfCategory(category).observe(this, new Observer<Double>()
		{
			@Override public void onChanged(Double aDouble)
			{
				x.setText(aDouble + "/" + budgetAmount);
			}
		});

//		adapter.setOnItemClickListener(new CategoryAdapter().OnItemClickListener() {
//			@Override
//			public void onItemClickListener(monthlySpent) {
//				Intent intent = new Intent(CategoryPage.this, AddSpentPage.class);
//				intent.putExtra(AddSpentPage.EXTRA_ID, monthlySpent.getId());
//				intent.putExtra(AddSpentPage.EXTRA_NOTE, budget.getCategory());
//				intent.putExtra(AddSpentPage.EXTRA_AMOUNT, budget.getAmount());
//				startActivityForResult(intent,EDIT_BUDGET_REQUEST);
//			}
//		});
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
			MonthlySpent spentItem = new MonthlySpent(category, note,amount);
			spentViewModel.insertNote(spentItem);

			Toast.makeText(this, "New Note Saved", Toast.LENGTH_SHORT).show();
		}
		else if(requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK){
			int id = data.getIntExtra(AddSpentPage.EXTRA_ID,-1);
			if(id == -1){
				Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show();
				return;
			}
			String note = data.getStringExtra(AddSpentPage.EXTRA_NOTE);
			double amount = data.getDoubleExtra(AddExpensePage.EXTRA_AMOUNT, 0.0);
			MonthlySpent spentItem = new MonthlySpent(category, note,amount);
			spentItem.setId(id);
			spentViewModel.updateNote(spentItem);

			Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT).show();
		}
		else
		{
			Toast.makeText(this, "Budget Not Saved", Toast.LENGTH_SHORT).show();
		}
	}

	public void addSpentPage(View view)
	{
		Intent intent = new Intent(this, AddSpentPage.class);
		startActivity(intent);
	}

	public void mainPage(View view)
	{
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

}
