package com.example.islam.project;

import java.util.ArrayList;

/**
 * Created by islam on 17/12/16.
 */

public class Cart {
    private static ArrayList<Product> productsInCart;
    private  static int userId;
    private static int Count ;
    private  static double totalMoney;

    public Cart() {
        if(this.productsInCart== null){
            this.productsInCart= new ArrayList<Product>();
        }

    }

    public Cart(int id) {
        if(id != this.userId ){
            this.userId= id;
            this.productsInCart= new ArrayList<Product>();
            this.Count=0;
            this.totalMoney=0;

        }


    }
    public static void addProdToCart(Product pr){
        Cart.productsInCart.add(pr);
        Cart.Count= Cart.Count+1;
    }

    public static ArrayList<Product> getProductsInCart() {
        return productsInCart;
    }



    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        Cart.userId = userId;
    }

    public static int getCount() {
        return Count;
    }


    public static double getTotalMoney() {
        double n=0,p=0;

        for (Product pr: Cart.productsInCart){
            p= pr.getPrice();
            n=pr.getQuantity();
            Cart.totalMoney+= n*p;

        }



        return totalMoney;
    }

}
