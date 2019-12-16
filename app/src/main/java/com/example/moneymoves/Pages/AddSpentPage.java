package com.example.moneymoves.Pages;

import android.content.Intent;
import android.os.Bundle;

import com.example.moneymoves.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddSpentPage  extends AppCompatActivity {
    public static final String EXTRA_ID = "ID";
    public static final String EXTRA_NOTE = "NOTE";
    public static final String EXTRA_AMOUNT = "AMOUNT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spent_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        EditText noteTextView = findViewById(R.id.spentNote);
        EditText amountTextView = findViewById(R.id.spentAmount);


        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            noteTextView.setText(intent.getStringExtra(EXTRA_NOTE));
            amountTextView.setText(String.valueOf(intent.getDoubleExtra(EXTRA_AMOUNT, -1)));
        }
        else{
            setTitle("Add Note");
        }
    }

    public void addSpent(View v){
        TextView noteTV = findViewById(R.id.spentNote);
        String note = noteTV.getText().toString();

        EditText amountTV = findViewById(R.id.spentAmount);

        Intent data = new Intent();

        try
        {
            double amount = Double.valueOf(amountTV.getText().toString());

            data.putExtra(EXTRA_NOTE, note);
            data.putExtra(EXTRA_AMOUNT, amount);

            int id = getIntent().getIntExtra(EXTRA_ID,-1);

            if(id != -1){
                data.putExtra(EXTRA_ID,id);
            }
            setResult(RESULT_OK, data);
        }
        catch(NumberFormatException E)
        {
            setResult(RESULT_CANCELED, data);
        }
        finally
        {
            finish();
        }

    }
}
