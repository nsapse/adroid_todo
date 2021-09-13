package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Define the data for persistence
    List<String> items;

    // Define the widgets for interacting with the UI
    Button btnAdd;
    EditText etItem;
    RecyclerView rvItems;
    ItemsAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Define actual widgets
        btnAdd = findViewById(R.id.btnAdd);
        etItem = findViewById(R.id.etItem);
        rvItems = findViewById(R.id.rvItems);

        // Write mock data
        items = new ArrayList<>();
        items.add("Buy milk");
        items.add("Learn Kotlin");
        items.add("Fix small font");
        items.add("Finish Book");

        ItemsAdapter.OnLongClickListener onLongClickListener = new ItemsAdapter.OnLongClickListener(){
            @Override
            public void onItemLongClicked(int Position) {
               // Delete the item from the model
                items.remove(Position);
                itemsAdapter.notifyItemRemoved(Position);
                Toast.makeText(getApplicationContext(), "item has been removed", Toast.LENGTH_SHORT).show();
            }
        };

        itemsAdapter = new ItemsAdapter(items, onLongClickListener);
        rvItems.setAdapter(itemsAdapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // extract the text from the input
                String todoItem = etItem.getText().toString();

                // add the item to the model
                items.add(todoItem);

                // notify the adapter that the item is inserted
                itemsAdapter.notifyItemInserted(items.size() - 1);
                etItem.setText("");

                // notify the user of the add with a toast
                Toast.makeText(getApplicationContext(), "item has been added", Toast.LENGTH_SHORT).show();

            }
        });

    }
 }