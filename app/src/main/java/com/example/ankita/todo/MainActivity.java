package com.example.ankita.todo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.ankita.todo.R.id.add_Btn;
import static com.example.ankita.todo.R.id.item_List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EditText ItemET;
    private Button btn;
    private ListView ItemList;

    private ArrayList <String> items;
    private ArrayAdapter <String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ItemET = findViewById(R.id.item_edit_text);
        btn = findViewById(add_Btn);
        ItemList = findViewById(R.id.item_List);

        items = FileHelper.readData(this);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        ItemList.setAdapter(adapter);

        btn.setOnClickListener(this);
        ItemList.setOnItemClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case add_Btn:
                String itemEntered = ItemET.getText().toString();

                adapter.add(itemEntered);
                ItemET.getText();

                FileHelper.writeData(items, this);

                Toast.makeText(this, "Item add", Toast.LENGTH_SHORT).show();

                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        items.remove(position);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
    }
}
