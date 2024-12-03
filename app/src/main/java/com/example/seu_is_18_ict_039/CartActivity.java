package com.example.seu_is_18_ict_039;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    Button load;
    private List<Product> cartProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

load=findViewById(R.id.back);
        recyclerView = findViewById(R.id.cartRecyclerView);
        cartProductList = new ArrayList<>();

        // Add products to cart list (this should be passed from ProductListActivity)
        // Sample data for now
        cartProductList.add(new Product("Laptop", "A high-performance laptop", 1200, R.drawable.laptop));
       // cartProductList.add(new Product("Smartphone", "Latest model smartphone", 70000, R.drawable.smartphone));
      //  cartProductList.add(new Product("Headphones", "Noise-cancelling headphones", 1200, R.drawable.headphones));
       // cartProductList.add(new Product("Camera", "DSLR camera with lens", 1200, R.drawable.camera));
       // cartProductList.add(new Product("Watch", "Smartwatch with fitness tracking", 1200, R.drawable.watch));
       // cartProductList.add(new Product("Speaker", "10-inch screen tablet", 1200, R.drawable.tablet));
       // cartProductList.add(new Product("Mouse", "Wireless mouse", 1200, R.drawable.speaker));

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter = new CartAdapter(cartProductList);
        recyclerView.setAdapter(cartAdapter);

        // "Buy Now" Button
        Button buyNowButton = findViewById(R.id.buyNowButton);
        buyNowButton.setOnClickListener(v -> {
            // Handle the purchase process
        });


load.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(CartActivity.this, ProductListActivity.class);
        startActivity(intent);
        finish();
    }
});
        buyNowButton.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, PaymentActivity.class);
            startActivity(intent);
        });

    }
}
