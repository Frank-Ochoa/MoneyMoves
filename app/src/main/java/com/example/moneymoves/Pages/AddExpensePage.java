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

    public static final String EXTRA_CATEGORY = "CATEGORY";
    public static final String EXTRA_AMOUNT = "AMOUNT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    public void addExpense(View v){
        TextView categoryTV = findViewById(R.id.expenseCategory);
        String category = categoryTV.getText().toString();

        EditText amountTV = findViewById(R.id.budgetAmount);
        double amount = Double.valueOf(amountTV.getText().toString());

        Intent data = new Intent();
        data.putExtra(EXTRA_CATEGORY, category);
        data.putExtra(EXTRA_AMOUNT, amount);

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
