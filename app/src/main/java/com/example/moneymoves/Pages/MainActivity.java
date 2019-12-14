package com.example.moneymoves.Pages;

import android.content.Intent;
import android.os.Bundle;

import com.example.moneymoves.Adapters.AdvavcedMoneyAdapter;
import com.example.moneymoves.Adapters.MonthlySpentAdapter;
import com.example.moneymoves.Database.Entities.BudgetTemplate;
import com.example.moneymoves.R;
import com.example.moneymoves.ViewModels.BudgetTemplateViewModel;
import com.example.moneymoves.ViewModels.MainActivityViewModel;
import com.example.moneymoves.ViewModels.ProgressBarViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    MonthlySpentAdapter adapter;
    private MainActivityViewModel mainViewModel;
    private ProgressBarViewModel progressBarViewModel;

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

        final TextView x = findViewById(R.id.noteAmount);
        TextView y = findViewById(R.id.budgetCategory);
        y.setText("Total Spent");

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

        final MainActivity page = this;
        final ProgressBar bar = findViewById(R.id.pb_red_progress);
        progressBarViewModel = ViewModelProviders.of(this).get(ProgressBarViewModel.class);

        progressBarViewModel.getAllIncome().observe(page, new Observer<Double>()
        {
            @Override public void onChanged(final Double income)
            {
                bar.setMax(income.intValue());

                progressBarViewModel.getSumSpent().observe(page, new Observer<Double>()
                {
                    @Override public void onChanged(Double spent)
                    {
                        if (spent == null)
                        {
                            bar.setProgress(income.intValue());
                            x.setText(0.0 + "/" + income + " Income");
                        }
                        else
                        {

                            bar.setProgress(income.intValue() - spent.intValue());
                            x.setText(spent + "/" + income + " Income");
                        }
                    }
                });

            }
        });

        adapter.setOnItemClickListener(new MonthlySpentAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(BudgetTemplate budget) {
                Intent intent = new Intent(MainActivity.this, CategoryPage.class);
                intent.putExtra(CategoryPage.EXTRA_ID, budget.getId());
                intent.putExtra(CategoryPage.EXTRA_CATEGORY, budget.getCategory());
                intent.putExtra(CategoryPage.EXTRA_AMOUNT, budget.getAmount());

                startActivity(intent);
            }
        });
    }

    public void expensePage(View view)
    {
        Intent intent = new Intent(this, ExpensesPage.class);
        startActivity(intent);
    }

    public void incomePage(View view)
    {
        Intent intent = new Intent(this, IncomePage.class);
        startActivity(intent);
    }
}
