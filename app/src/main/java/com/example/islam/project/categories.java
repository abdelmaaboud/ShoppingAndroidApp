package com.example.islam.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class categories extends AppCompatActivity {
    ListView mylist ;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    final UserDbHelper helper = new UserDbHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        mylist = (ListView) findViewById(R.id.categories_list);

        listItems= helper.getCategories();
        adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems);
        mylist.setAdapter(adapter);
    //    adapter.add("islam");




        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView v = (TextView) view;
                String text = v.getText().toString();
                Intent intent = new Intent(categories.this,products.class);
                intent.putExtra("categoryname",text);
                startActivity(intent);

            }
        });



    }
}
