package com.example.appcupom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.util.List;

public class OrderCreatedActivity extends AppCompatActivity {
    private TextView tvOrderNumberCreated;
    private ListView lvProducts;
    private List<Product> productList;
    private AdapterProduct adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_created);

        String orderNumber = getIntent().getExtras().getString("orderNumber");

        tvOrderNumberCreated = findViewById(R.id.tvOrderNumberCreated);

        tvOrderNumberCreated.setText(orderNumber);

        lvProducts = findViewById(R.id.lvOrderedProducts);

        try {
            loadProducts();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private void loadProducts() throws ParseException {
        productList = ProductDAO.getProducts(this);
        adapter = new AdapterProduct(this, productList);
        lvProducts.setAdapter( adapter );
    }
}