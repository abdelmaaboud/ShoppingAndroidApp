package com.example.islam.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by islam on 16/12/16.
 */

public class UserDbHelper extends SQLiteOpenHelper {
    SQLiteDatabase userdatabase;
    private static String dataBaseName = "souq";

    public UserDbHelper(Context context) {
        super(context, dataBaseName, null, 1);
        this.userdatabase = userdatabase;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //users
        db.execSQL("CREATE TABLE `users` (\n" +
                "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`name`\tTEXT NOT NULL,\n" +
                "\t`email`\tTEXT NOT NULL,\n" +
                "\t`password`\tTEXT NOT NULL,\n" +
                "\t`birthdate`\tDATETIME\n" +
                ");");
        //products
        db.execSQL("CREATE TABLE `products` (\n" +
                "\t`id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`name`\tTEXT NOT NULL,\n" +
                "\t`price`\tNUMERIC NOT NULL,\n" +
                "\t`quantity`\tINTEGER NOT NULL,\n" +
                "\t`catid`\tINTEGER NOT NULL\n" +
                ");");

        //categories
        db.execSQL("CREATE TABLE `categories` (\n" +
                "\t`id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`name`\tTEXT NOT NULL\n" +
                ");");
        db.execSQL("CREATE TABLE `orders` (\n" +
                "\t`id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`date`\tDATETIME,\n" +
                "\t`custid`\tINTEGER NOT NULL,\n" +
                "\t`address`\tINTEGER\n" +
                ")");


        db.execSQL("CREATE TABLE `orederdetails` (\n" +
                "\t`id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`orderid`\tINTEGER NOT NULL,\n" +
                "\t`prodid`\tINTEGER NOT NULL,\n" +
                "\t`quantity`\tINTEGER NOT NULL\n" +
                ");");




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public  void CreateNewUser(String name,String email,String password,String birthdate){
        ContentValues row = new ContentValues();
     //   INSERT INTO `users`(`id`,`name`,`email`,`password`,`birthdate`) VALUES (NULL,'','','',NULL);


        row.put("name",name);
        row.put("email",email);
        row.put("password",password);
        row.put("birthdate",birthdate);
        userdatabase=getWritableDatabase();
        userdatabase.insert("users",null,row);
        userdatabase.close();


    }
    public int Checkuser(String email,String password){
        String[] arg = {email};
        userdatabase=getReadableDatabase();
        Cursor cr= userdatabase.rawQuery("select password ,id from users where email= ?",arg);

       if (cr.getCount()>0)
       {
           cr.moveToFirst();

            int n = cr.getInt(1);
           String p = cr.getString(0);
           userdatabase.close();
           cr.close();
           if (p.equals(password)){
               return n;

           }
           else{return 0;}

       }
        return 0;

    }
    public int getcountuser(){
        String countQuery = "SELECT  * FROM users";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }
    public ArrayList<String> getCategories(){
        ArrayList<String> cat = new ArrayList<String>();
        String query = "SELECT  name FROM categories";
        SQLiteDatabase db = this.getReadableDatabase();
       Cursor cr =  db.rawQuery(query,null);
        if(cr!=null){
            while (cr.moveToNext()){
               String name =  cr.getString(0);
                cat.add(name);

            }
        }


        return cat;
    }
    public void addCategory(String name){
        ContentValues row = new ContentValues();
        //   INSERT INTO `users`(`id`,`name`,`email`,`password`,`birthdate`) VALUES (NULL,'','','',NULL);


        row.put("name",name);
        userdatabase=getWritableDatabase();
        userdatabase.insert("categories",null,row);
        userdatabase.close();

    }
    public int getcategoryid(String name){
        int n;
        String[] arg = {name};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("select id from categories where name = ?",arg);
        if (cr.getCount()>0){
            return cr.getInt(0);
        }
        return 0;
    }

    public ArrayList<String> getProducts(String catName){
        ArrayList<String> prod = new ArrayList<String>();
        String[] arg = {catName};
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT  name FROM products where catid = (select id from categories where name = ? )";
        Cursor cr =  db.rawQuery(query,arg);
        if(cr.getCount()>0){
            while (cr.moveToNext()){
                String name =  cr.getString(0);
                prod.add(name);

            }
            db.close();
            return prod;
        }
        return null;

}
    public Product getproduct(String name)
    {
        String[] arg = {name};
        Product pr= new Product(name);
        String query = "SELECT  id , price,quantity,catid FROM products where name = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr =  db.rawQuery(query,arg);
        if(cr!=null){
            cr.moveToFirst();

            pr.setId(cr.getInt(0));
            pr.setName(name);
           pr.setPrice(cr.getDouble(1));
            pr.setQuantity(cr.getInt(2));
            pr.setCatid(cr.getInt(3));

    }
        db.close();
        return pr;

}
    public void addProduct(String name,double price ,int quantity,int catid ){

        // name price quantity catid
        ContentValues row = new ContentValues();


        row.put("name",name);
        row.put("price",price);
        row.put("quantity",quantity);
        row.put("catid",catid);

        userdatabase=getWritableDatabase();
        userdatabase.insert("products",null,row);
        userdatabase.close();

    }

}