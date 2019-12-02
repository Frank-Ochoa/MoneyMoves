package com.example.moneymoves.Pages;

import android.os.Bundle;

import com.example.moneymoves.Adapters.AdvavcedMoneyAdapter;
import com.example.moneymoves.Adapters.MonthlySpentAdapter;
import com.example.moneymoves.Database.Entities.BudgetTemplate;
import com.example.moneymoves.R;
import com.example.moneymoves.ViewModels.BudgetTemplateViewModel;
import com.example.moneymoves.ViewModels.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    MonthlySpentAdapter adapter;
    private MainActivityViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new MonthlySpentAdapter();
        recyclerView.setAdapter(adapter);

        // Destroys when finished
        final MainActivity mainInstance = this;
        mainViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainViewModel.getAllBudgets().observe(mainInstance, new Observer<List<BudgetTemplate>>()
        {
            @Override public void onChanged(List<BudgetTemplate> budgetTemplates)
            {
                // update our RecyclerView
                adapter.setBudgets(budgetTemplates);
                for(BudgetTemplate temp : budgetTemplates)
                {
                    final String cat = temp.getCategory();
                    mainViewModel.getSumAmountOfCategory(cat).observe(mainInstance, new Observer<Double>() {
                       @Override
                       public void onChanged(Double aDouble) {
                           adapter.setSpentSums(cat, aDouble);
                       }
                   });
                }
            }
        });
    }

}
