package com.example.seu_is_18_ict_039;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private EditText searchBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        // Initialize views
        recyclerView = findViewById(R.id.recyclerView);
        searchBar = findViewById(R.id.search_bar);

        // Sample Product List
        productList = new ArrayList<>();
        populateProductList();

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productAdapter = new ProductAdapter(productList, product -> {
            // On click listener for Add to Cart button
            Intent intent = new Intent(ProductListActivity.this, CartActivity.class);
            // You can pass the product list or individual product here
            startActivity(intent);
        });
        recyclerView.setAdapter(productAdapter);

        // Search bar functionality
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                productAdapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void populateProductList() {
        productList.add(new Product("Laptop", "A high-performance laptop", 1200, R.drawable.laptop));
        productList.add(new Product("Smartphone", "Latest model smartphone", 999, R.drawable.smartphone));
        productList.add(new Product("Headphones", "Noise-cancelling headphones", 150, R.drawable.headphones));
        productList.add(new Product("Camera", "DSLR camera with lens", 800, R.drawable.camera));
        productList.add(new Product("Watch", "Smartwatch with fitness tracking", 200, R.drawable.watch));
        productList.add(new Product("Tablet", "10-inch screen tablet", 300, R.drawable.tablet));
        productList.add(new Product("Speaker", "Bluetooth portable speaker", 100, R.drawable.speaker));
        productList.add(new Product("Mouse", "Wireless mouse", 25, R.drawable.mouse));
        productList.add(new Product("Keyboard", "Mechanical keyboard", 50, R.drawable.keyboard));
        productList.add(new Product("Monitor", "27-inch 4K monitor", 400, R.drawable.monitor));
    }
}
