package com.example.appcupom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CartActivity extends AppCompatActivity {
    private Button btnCloseOrder;
    private AdapterProduct adapter;
    private List<Product> productsList;
    private ListView lvProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        btnCloseOrder = findViewById(R.id.btnCloseOrder);
        btnCloseOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this, OrderCreatedActivity.class);
                String orderNumber = createOrder();
                intent.putExtra("orderNumber", orderNumber);
                startActivity(intent);
            }
        });

        lvProducts = findViewById(R.id.lvOrderProducts);

        try {
            loadProducts();
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    private void loadProducts() throws ParseException {
        ArrayList<String> productsIdList = new ArrayList<String>();
        List<Cart> cartList = CartDAO.getCarts(this);
        if (cartList.size() > 0) {
            for (Cart c : cartList) {
                productsIdList.add(c.getProductId().toString());
            }
            productsList = ProductDAO.getProductsListById(productsIdList, this);
            adapter = new AdapterProduct(this, productsList);
            lvProducts.setAdapter(adapter);
        }else{
            btnCloseOrder.setEnabled(false);
        }
    }

    private String createOrder(){
        Random gerador = new Random();
        String orderNumberRandom = String.valueOf(gerador.nextInt());
        int id = OrderDAO.getNextOrderId(this);
        finish();
        for (Product product: productsList) {
            Order order = new Order();
            order.setProductId(product.getId());
            order.setOrderNumber(orderNumberRandom);
            order.setId(id);
            order.setTotalPrice(product.getPrice());
            order.setTotalQuantity(product.getQuantity());

            OrderDAO.insert(order, this);
            finish();
        }

        cleanCart();

        return orderNumberRandom;
    }

    private void cleanCart(){
        CartDAO.delete(this);
    }
}