package com.example.islam.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShoppingCart extends AppCompatActivity {
Cart cart ;
    ArrayAdapter<String> adapter;
    ArrayList<String> listItems;
TextView moneytxt ;
    GridView grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        cart = new Cart();
        listItems= new ArrayList<>();
        listItems.add("Product ");
        listItems.add("price ");
        listItems.add("quantity ");
        ArrayList<Product> allProducts= cart.getProductsInCart();
        for (Product pr: allProducts){
            listItems.add(pr.getName());
            listItems.add(String.valueOf( pr.getPrice()));
            listItems.add(String.valueOf( pr.getQuantity()));


        }
        moneytxt= (TextView) findViewById(R.id.totalmoney_cart);
        moneytxt.setText(String.valueOf(cart.getTotalMoney()));

        grid=(GridView) findViewById(R.id.gridView_cart);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems);
        grid.setAdapter(adapter);



    }

}
