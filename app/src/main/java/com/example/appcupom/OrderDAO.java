package com.example.appcupom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public static void insert(Order order, Context context){
        ContentValues valores = new ContentValues();
        valores.put("id", order.getId());
        valores.put("orderNumber", order.getOrderNumber());
        valores.put("productId", order.getProductId());
        valores.put("totalPrice", order.getTotalPrice());
        valores.put("totalQuantity", order.getTotalQuantity());

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.insert("orders", null, valores);
    }

    public static void editar(Order order, Context context){
        ContentValues valores = new ContentValues();
        valores.put("orderNumber", order.getOrderNumber());
        valores.put("productName", order.getProductId());
        valores.put("totalPrice", order.getTotalPrice().toString());
        valores.put("totalQuantity", order.getTotalQuantity());

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.update("orders", valores, " id = " + order.getId(), null);
    }

    public static void excluir(int id, Context context){
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete("orders", " id = " + id , null);
    }

    public static List<Order> getOrders(Context context) throws ParseException {
        List<Order> lista = new ArrayList<>();

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, orderNumber, productId, totalPrice, totalQuantity FROM orders ORDER BY orderNumber", null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                Order order = new Order();
                order.setId(cursor.getInt(0));
                order.setOrderNumber(cursor.getString(1));
                order.setProductId(cursor.getInt(2));
                order.setTotalPrice(cursor.getDouble(3));
                order.setTotalQuantity(cursor.getInt(4));

                lista.add(order);
            }while(cursor.moveToNext());
        }

        return lista;
    }

    public static Order getOrderById(Context context , int id) throws ParseException {
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, orderNumber, productName, totalPrice, totalQuantity FROM orders WHERE id = " + id, null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            Order order = new Order();
            order.setId(cursor.getInt(0));
            order.setOrderNumber(cursor.getString(1));
            order.setProductId(cursor.getInt(2));
            order.setTotalPrice(cursor.getDouble(3));
            order.setTotalQuantity(cursor.getInt(4));
            return order;
        }else{
            return null;
        }
    }

    public static int getNextOrderId(Context context){
        int generatedId = 1;
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(id) FROM orders", null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            generatedId = cursor.getInt(0);
        }
        return generatedId;
    }

}
