package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.TextClassification;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.Viewholder> {


    // interface for calling remove on a long click
    public interface OnLongClickListener {
        void onItemLongClicked(int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use a layout inflator to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        // Wrap it in a ViewHolder and return it
        return new Viewholder(todoView);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        // Grab the item at the position
        String item = items.get(position);

        // Bind the item to the specified viewholder
        holder.bind(item);

    }

    // Tells RV how many items are in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    // The adapter for the app recycler view
    // Create a container to provide easy access to the views
    // representing each row of the list

    class Viewholder extends RecyclerView.ViewHolder {

        TextView tvItem;

        // Constructor Calling Super
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        // update the view inside of the viewholder by binding the data
        public void bind(String item) {
           tvItem.setText(item);
           tvItem.setOnLongClickListener(new View.OnLongClickListener() {
               @Override
               public boolean onLongClick(View view) {
                   // Notify the listener which position was long pressed
                   longClickListener.onItemLongClicked(getAdapterPosition());
                   return true;
               }
           });
        }
    }
}
