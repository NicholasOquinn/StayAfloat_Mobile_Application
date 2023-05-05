package com.example.stayafloat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView listView;
    private Button button;
    String complete = "Complete";
    String incomplete = "Incomplete";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Objects.requireNonNull(getSupportActionBar()).setTitle("Stay Afloat - To-do List Application");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(view);
            }
        });

        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(itemsAdapter);
        setupListViewListener();
    }

    private void setupListViewListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Context context = getApplicationContext();
                Toast.makeText(context, "Item Removed", Toast.LENGTH_LONG).show();

                items.remove(i);
                itemsAdapter.notifyDataSetChanged();
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Context context = getApplicationContext();
                String item = listView.getItemAtPosition(i).toString();
                //Toast.makeText(context, "hello"+item, Toast.LENGTH_LONG).show();
                if (item.contains(incomplete)) {
                    //item.replaceFirst("\bIncomplete\b", "Complete");
                    item = item.replaceFirst("Incomplete", "Complete");
                    String newMessage = item;
                    items.remove(i);
                    itemsAdapter.add(newMessage);
                }
                else if (item.contains(complete)) {
                    //item.replaceFirst("\bIncomplete\b", "Complete");
                    item = item.replaceFirst("Complete", "Incomplete");
                    String newMessage = item;
                    items.remove(i);
                    itemsAdapter.add(newMessage);
                }



            }
        });
    }

    private void addItem(View view) {
        EditText input = findViewById(R.id.editText);
        String itemText = input.getText().toString();


        if(!(itemText.equals(""))){
            itemsAdapter.add(itemText+"                                                " +
                    "                   "+incomplete);
            //listView.setAdapter(itemsAdapter);
            input.setText("");
        }
        else{
            Toast.makeText(getApplicationContext(), "Please enter text...", Toast.LENGTH_LONG).show();
        }
    }
}