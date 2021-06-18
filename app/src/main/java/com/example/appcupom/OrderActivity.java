package com.example.appcupom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderActivity extends AppCompatActivity {
    private AdapterOrder adapter;
    private ListView lvOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        lvOrders = findViewById(R.id.lvOrderProducts);

        try {
            loadOrders();
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    private void loadOrders() throws ParseException {
        ArrayList<String> productsIdList = new ArrayList<String>();
        List<Order> orderList = OrderDAO.getOrders(this);
        if (orderList.size() > 0) {
            adapter = new AdapterOrder(this, orderList);
            lvOrders.setAdapter(adapter);
        }
    }
}