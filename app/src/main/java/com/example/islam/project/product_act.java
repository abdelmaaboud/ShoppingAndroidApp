package com.example.islam.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class product_act extends AppCompatActivity {
TextView nametxt,pricetxt,quanttxt;
    EditText quant_required;
    Button  showcart,addtocart;
    Product pr;
    Cart cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_act);
        String name= getIntent().getExtras().getString("name");
        cart= new Cart();
        final UserDbHelper helper = new UserDbHelper(this);
         pr =helper.getproduct(name);
        nametxt=(TextView) findViewById(R.id.prod_name_prod);
        pricetxt=(TextView) findViewById(R.id.prod_price_prod);
        quanttxt=(TextView) findViewById(R.id.prod_quan_prod);
        nametxt.setText(pr.getName());
        final int prquantity=  pr.getQuantity();
        pricetxt.setText("price : "+ String.valueOf(pr.getPrice()));
        quanttxt.setText("available Quantity : "+ String.valueOf(pr.getQuantity()));

        quant_required = (EditText) findViewById(R.id.quantity_requir);
        showcart =  (Button) findViewById(R.id.prod_show_cart_prod);
        addtocart = (Button) findViewById(R.id.prod_addtocart_prod);

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n ;
                String quantReq_txt = quant_required.getText().toString();
                if(!quantReq_txt.equals(""))
                {
                    n=  Integer.parseInt(quantReq_txt );
                    if (n<= prquantity )
                    {
                        //  add to cart
                        Product newpr= pr;
                        newpr.setQuantity(n);
                        cart.addProdToCart(newpr);
                        Toast.makeText(getApplicationContext(),pr.getName()+ " Added ",Toast.LENGTH_LONG).show();


                    }
                    else {
                        Toast.makeText(getApplicationContext(),"your quantity is more than available" ,Toast.LENGTH_LONG).show();
                    }
                }
            }

        });
        showcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(product_act.this,ShoppingCart.class);
                startActivity(intent);

            }
        });

    }
}
