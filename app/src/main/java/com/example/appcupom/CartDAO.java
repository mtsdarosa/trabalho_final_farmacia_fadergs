package com.example.appcupom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    public static void insert(Cart cart, Context context){
        ContentValues valores = new ContentValues();
        valores.put("productId", cart.getProductId());
        valores.put("totalPrice", cart.getTotalPrice());
        valores.put("totalQuantity", cart.getTotalQuantity());

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.insert("cart", null, valores);
    }

    public static void edit(Cart cart, Context context){
        ContentValues valores = new ContentValues();
        valores.put("totalPrice", cart.getTotalPrice().toString());
        valores.put("totalQuantity", cart.getTotalQuantity());

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.update("cart", valores, " id = " + cart.getId(), null);
    }

    public static void delete(Context context){
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete("cart",null , null);
    }

    public static List<Cart> getCarts(Context context) throws ParseException {

        List<Cart> lista = new ArrayList<>();

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, productId, totalPrice, totalQuantity FROM cart ORDER BY id", null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                Cart cart = new Cart();
                cart.setId(cursor.getInt(0));
                cart.setProductId(cursor.getInt(1));
                cart.setTotalPrice(cursor.getDouble(2));
                cart.setTotalQuantity(cursor.getInt(3));

                lista.add(cart);
            }while(cursor.moveToNext());
        }

        return lista;
    }

    public static Cart getCartById(Context context , int id) throws ParseException {
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, productId, totalPrice, totalQuantity FROM cart WHERE id = " + id, null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            Cart cart = new Cart();
            cart.setId(cursor.getInt(0));
            cart.setProductId(cursor.getInt(1));
            cart.setTotalPrice(cursor.getDouble(2));
            cart.setTotalQuantity(cursor.getInt(3));
            return cart;
        }else{
            return null;
        }
    }

    public static Cart getCartByProductId(Context context , int id) throws ParseException {
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, productId, totalPrice, totalQuantity FROM cart WHERE productId = " + id, null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            Cart cart = new Cart();
            cart.setId(cursor.getInt(0));
            cart.setProductId(cursor.getInt(1));
            cart.setTotalPrice(cursor.getDouble(2));
            cart.setTotalQuantity(cursor.getInt(3));
            return cart;
        }else{
            return null;
        }
    }
}
