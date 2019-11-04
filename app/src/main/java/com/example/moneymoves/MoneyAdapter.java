package com.example.moneymoves;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MoneyAdapter extends RecyclerView.Adapter<MoneyAdapter.ViewHolder> {


    @NonNull
    @Override
    public MoneyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MoneyAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView expenseName;
        EditText expenseAmount;
        
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            expenseName = itemView.findViewById(R.id.expenseName);
            expenseAmount = itemView.findViewById(R.id.expenseAmount);
        }

        /*public void bindData(Contact contact) {
            System.out.println("BINDING DATA");
            expenseName.setText(contact.getName());
            expenseName.setTextColor(Color.BLACK);

            expenseAmount.setText(contact.getPhoneNumber());
            expenseAmount.setTextColor(Color.BLUE);
        }*/
    }
}
