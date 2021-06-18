package com.example.appcupom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public static void inserir(Product product, Context context){
        ContentValues valores = new ContentValues();
        valores.put("productName", product.getProductName());
        valores.put("price", product.getPrice());
        valores.put("quantity", product.getQuantity());

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.insert("product", null, valores);
    }

    public static void editar(Product product, Context context){
        ContentValues valores = new ContentValues();
        valores.put("productName", product.getProductName());
        valores.put("price", product.getPrice().toString());
        valores.put("quantity", product.getQuantity());

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.update("product", valores, " id = " + product.getId(), null);
    }

    public static void excluir(int id, Context context){
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete("product", " id = " + id , null);
    }

    public static List<Product> getProducts(Context context) throws ParseException {

        List<Product> lista = new ArrayList<>();

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, productName, price, quantity FROM product ORDER BY productName", null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                Product product = new Product();
                product.setId(cursor.getInt(0));
                product.setProductName(cursor.getString(1));
                product.setPrice(cursor.getDouble(2));
                product.setQuantity(cursor.getInt(3));

                lista.add(product);
            }while(cursor.moveToNext());
        }

        return lista;
    }

    public static List<Product> getProductsListById(List<String> productIdList, Context context) throws ParseException {
        List<Product> lista = new ArrayList<>();

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        String productsId = "";
        for (String id: productIdList) {
            productsId = productsId  + id + ",";
        }
        productsId = productsId.substring(0, productsId.length() - 1);

        Cursor cursor = db.rawQuery("SELECT id, productName, price, quantity FROM product WHERE id IN ("+productsId+") ORDER BY productName", null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                Product product = new Product();
                product.setId(cursor.getInt(0));
                product.setProductName(cursor.getString(1));
                product.setPrice(cursor.getDouble(2));
                product.setQuantity(cursor.getInt(3));

                lista.add(product);
            }while(cursor.moveToNext());
        }

        return lista;
    }

    public static Product getProductById(Context context , int id) throws ParseException {
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, productName, price, quantity FROM product WHERE id = " + id, null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            Product product = new Product();
            product.setId(cursor.getInt(0));
            product.setProductName(cursor.getString(1));
            product.setPrice(cursor.getDouble(2));
            product.setQuantity(cursor.getInt(3));
            return product;
        }else{
            return null;
        }
    }
}
