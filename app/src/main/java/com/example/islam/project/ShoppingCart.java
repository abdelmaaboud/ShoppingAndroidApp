package com.example.islam.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShoppingCart extends AppCompatActivity {
Cart cart ;
    ArrayAdapter<String> adapter;
    ArrayList<String> listItems;
TextView moneytxt ;
    GridView grid;
    Button makeOrderbtn;
    EditText addresstxt;
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
        addresstxt=(EditText)findViewById(R.id.address);

        makeOrderbtn=(Button)findViewById(R.id.make_order_cart);
        makeOrderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address= addresstxt.getText().toString();
                if(address.equals("")){
                    Toast.makeText(getApplicationContext(),"enter your address ",Toast.LENGTH_LONG).show();
                }
                else{
                    Cart.MakeOrder(getApplicationContext(),address);
                    Toast.makeText(getApplicationContext(),"order has added .. Done  ",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ShoppingCart.this,categories.class);
                    startActivity(intent);


                }
            }
        });



    }

}
