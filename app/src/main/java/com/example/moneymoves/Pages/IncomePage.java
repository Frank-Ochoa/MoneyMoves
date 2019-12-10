package com.example.moneymoves.Pages;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.moneymoves.Database.Entities.Income;
import com.example.moneymoves.Database.MoneyRepository;
import com.example.moneymoves.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class IncomePage extends AppCompatActivity {

    MoneyRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.addSpentButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        repository = new MoneyRepository(this.getApplication());
    }

    public void expensePage(View view){
        SharedPreferences pref = getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("firstStart",false);
        editor.apply();
        Intent intent = new Intent(this, ExpensesPage.class); //an action something thats going to happen

        Spinner spinner = findViewById(R.id.frequency);
        EditText editText = findViewById(R.id.income);
        double amount = Double.valueOf(editText.getText().toString());

        String frequency =  spinner.getSelectedItem().toString();

        switch (frequency)
        {
            case "Weekly":
                amount = amount * 4;
                System.out.println("WEEKLY :: " + amount);
                break;
            case "Biweekly":
                amount = amount * 2;
                System.out.println("Biweekly :: " + amount);
                break;
            default:
        }


        repository.insertIncome(new Income(amount));

        startActivity(intent);
    }

}
