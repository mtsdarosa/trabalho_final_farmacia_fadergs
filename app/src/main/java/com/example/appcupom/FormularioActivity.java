package com.example.appcupom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;

public class FormularioActivity extends AppCompatActivity {
    private EditText nbPrice;
    private EditText nbOrderedQuantity;
    private EditText etProductName;
    private Button btnAddToCart;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        nbPrice = findViewById(R.id.nbPreco);
        nbOrderedQuantity = findViewById(R.id.nbOrderedQuantity);
        etProductName = findViewById(R.id.etNomeProduto);
        btnAddToCart = findViewById(R.id.btnAddToCart);

        try {
            loadProduct();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                try {
                    addToCart();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadProduct() throws ParseException {
        int productId = getIntent().getIntExtra("productId", 0);
        if (productId != 0){
            product = ProductDAO.getProductById(this, productId);

            etProductName.setText(product.getProductName());
            nbPrice.setText(String.valueOf(product.getPrice()));
        }
    }

    private void addToCart() throws ParseException {
        if (etProductName.getText().toString().isEmpty() || nbPrice.getText().toString().isEmpty() ||
            nbOrderedQuantity.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Todos os campos devem ser preenchidos.", Toast.LENGTH_SHORT).show();
        }else{
            int productId = getIntent().getIntExtra("productId", 0);
            String action = "";

            Cart cart = CartDAO.getCartByProductId(this, productId);
            if (cart == null){
                cart = new Cart();
                action = "insert";
            }else{
                action = "update";
            }

            cart.setProductId(productId);
            cart.setTotalQuantity(Integer.parseInt(nbOrderedQuantity.getText().toString()));
            Double totalPrice = Double.parseDouble(nbPrice.getText().toString()) * Integer.parseInt(nbOrderedQuantity.getText().toString());
            cart.setTotalPrice(totalPrice);

            if (action == "insert") {
                CartDAO.insert(cart, this);
            }else{
                CartDAO.edit(cart, this);
            }

            finish();
            Toast.makeText(this,"Product added to cart.", Toast.LENGTH_LONG).show();
        }
    }
}