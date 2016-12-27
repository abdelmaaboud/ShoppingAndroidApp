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

public class products extends AppCompatActivity {
    ListView mylist ;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    final UserDbHelper helper = new UserDbHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);


        mylist = (ListView) findViewById(R.id.products_listview);

        String catname= getIntent().getExtras().getString("categoryname");
        Toast.makeText(getApplicationContext(), catname, Toast.LENGTH_SHORT).show();


        listItems=helper.getProducts(catname);
        if(listItems != null){
            Toast.makeText(getApplicationContext(), "n= " +String.valueOf( listItems.size()), Toast.LENGTH_LONG).show();
            adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems);
            mylist.setAdapter(adapter);
            mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView v = (TextView)view ;
                    String name = v.getText().toString();
                    Intent intent = new Intent(products.this,product_act.class);
                    intent.putExtra("name",name);
                    startActivity(intent);
                }
            });


        }
        else{
            Toast.makeText(getApplicationContext(), "null ", Toast.LENGTH_LONG).show();

        }



    }
}
