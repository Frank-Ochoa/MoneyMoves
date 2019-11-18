package com.example.moneymoves.Pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.moneymoves.R;

public class AddExpensePage extends AppCompatActivity {
	public static final String EXTRA_ID = "ID";
	public static final String EXTRA_CATEGORY = "CATEGORY";
	public static final String EXTRA_AMOUNT = "AMOUNT";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_expense);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		EditText categoryTextView = findViewById(R.id.expenseCategory);
		EditText amountTextView = findViewById(R.id.expenseAmount);


		Intent intent = getIntent();

		if(intent.hasExtra(EXTRA_ID)){
			setTitle("Edit Budget");
			categoryTextView.setText(intent.getStringExtra(EXTRA_CATEGORY));
			amountTextView.setText(String.valueOf(intent.getDoubleExtra(EXTRA_AMOUNT, -1)));
		}
		else{
			setTitle("Add Budget");
		}
	}

	public void addExpense(View v){
		TextView categoryTV = findViewById(R.id.expenseCategory);
		String category = categoryTV.getText().toString();

		EditText amountTV = findViewById(R.id.expenseAmount);
		double amount = Double.valueOf(amountTV.getText().toString());

		Intent data = new Intent();
		data.putExtra(EXTRA_CATEGORY, category);
		data.putExtra(EXTRA_AMOUNT, amount);

		int id = getIntent().getIntExtra(EXTRA_ID,-1);

		if(id != -1){
			data.putExtra(EXTRA_ID,id);
		}
		setResult(RESULT_OK, data);
		finish();

       /* MMDatabase dbHelper = new MMDatabase(this);
        dbHelper.insertBudgetRow(category, amount);
        Intent intent = new Intent(this, ExpensesPage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //this flag will destroy the previous expense activity
        startActivity(intent);*/
	}
}
