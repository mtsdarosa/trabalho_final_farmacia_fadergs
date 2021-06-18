package com.example.appcupom;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {
    private static final int VERSAO = 3;
    private static final String NOME = "AppFarmacia";

    public Banco(Context context){
        super(context, NOME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS product (" +
                "      id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "      productName TEXT NOT NULL, " +
                "      price REAL NOT NULL, " +
                "      quantity INTEGER NOT NULL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS orders (" +
                "      id INTEGER NOT NULL," +
                "      orderNumber TEXT NOT NULL, " +
                "      productId INTEGER NOT NULL, " +
                "      totalPrice REAL NOT NULL, " +
                "      totalQuantity INTEGER NOT NULL," +
                "      PRIMARY KEY(id, orderNumber, productId) )");

        db.execSQL("CREATE TABLE IF NOT EXISTS cart (" +
                "      id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "      productId INTEGER NOT NULL, " +
                "      totalPrice REAL NOT NULL, " +
                "      totalQuantity INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
