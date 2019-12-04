package com.example.moneymoves.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymoves.Database.Entities.BudgetTemplate;
import com.example.moneymoves.Database.POJOs.NoteAmount;
import com.example.moneymoves.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.NoteHolder>
{
    private List<NoteAmount> noteAmounts = new ArrayList<>();
    private CategoryAdapter.OnItemClickListener listener;

    @NonNull
    @Override public CategoryAdapter.NoteHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                          int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.budget_item, parent, false);
        return new CategoryAdapter.NoteHolder(itemView);
    }

    @Override public void onBindViewHolder(@NonNull CategoryAdapter.NoteHolder holder, int position)
    {
        NoteAmount curr = noteAmounts.get(position);
        holder.noteName.setText(curr.note);
        holder.amount.setText(String.valueOf(curr.amount));
    }

    @Override public int getItemCount()
    {
        return noteAmounts.size();
    }

    public void setNoteAmounts(List<NoteAmount> noteAmounts)
    {
        this.noteAmounts = noteAmounts;
        notifyDataSetChanged();
    }

    public NoteAmount getNoteAmount(int position)
    {
        return noteAmounts.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder
    {
        private TextView noteName;
        private TextView amount;

        public NoteHolder(@NonNull View itemView)
        {
            super(itemView);
            noteName = itemView.findViewById(R.id.budgetCategory);
            amount = itemView.findViewById(R.id.expenseAmount);

//            itemView.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v){
//                    int position = getAdapterPosition();
//                    //make sure the listener has been created
//                    //make sure that a deleted item isnt clicked before the row has been removed
//                    if(listener != null && position != RecyclerView.NO_POSITION) {
//                        listener.onItemClickListener(noteAmounts.get(position));
//                    }
//                }
//            });
        }
    }

//    public interface OnItemClickListener{
//        void onItemClickListener(BudgetTemplate budget); //passing in the budget that we want to update
//    }
//
//    public void setOnItemClickListener(AdvavcedMoneyAdapter.OnItemClickListener listener){
//        this.listener = listener;
//    }
}
