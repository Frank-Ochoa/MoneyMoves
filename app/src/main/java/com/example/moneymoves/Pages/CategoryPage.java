package com.example.moneymoves.Pages;

import android.content.Intent;
import android.os.Bundle;

import com.example.moneymoves.Adapters.AdvavcedMoneyAdapter;
import com.example.moneymoves.Adapters.CategoryAdapter;
import com.example.moneymoves.Database.Entities.BudgetTemplate;
import com.example.moneymoves.Database.POJOs.NoteAmount;
import com.example.moneymoves.ViewModels.MainActivityViewModel;
import com.example.moneymoves.ViewModels.SpentPageViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.example.moneymoves.R;

import java.util.List;

public class CategoryPage extends AppCompatActivity {

    public static final String EXTRA_ID = "ID";
    public static final String EXTRA_CATEGORY = "CATEGORY";
    public static final String EXTRA_AMOUNT = "AMOUNT";

    CategoryAdapter adapter;
    private SpentPageViewModel spentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Intent intent = getIntent();
        Double budgetAmount = intent.getDoubleExtra(EXTRA_AMOUNT, 0.0);
        String cat = intent.getStringExtra(EXTRA_CATEGORY);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new CategoryAdapter();
        recyclerView.setAdapter(adapter);

        spentViewModel = ViewModelProviders.of(this).get(SpentPageViewModel.class);
        spentViewModel.setAllSpent(cat);
        spentViewModel.getAllSpent().observe(this, new Observer<List<NoteAmount>>()
        {
            @Override public void onChanged(List<NoteAmount> noteAmounts)
            {
                adapter.setNoteAmounts(noteAmounts);
            }
        });

        spentViewModel.getSumAmountOfCategory(cat).observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {

            }
        });
    }

}
