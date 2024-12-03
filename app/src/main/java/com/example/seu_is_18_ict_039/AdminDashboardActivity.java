package com.example.seu_is_18_ict_039;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminDashboardActivity extends AppCompatActivity {

    private Button addProductButton, updateProductButton, deleteProductButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        addProductButton = findViewById(R.id.addProductButton);
        updateProductButton = findViewById(R.id.updateProductButton);
        deleteProductButton = findViewById(R.id.deleteProductButton);

        // Navigate to Add Product Activity
        addProductButton.setOnClickListener(v -> {
            startActivity(new Intent(this, AddProductActivity.class));
        });

        // Navigate to Update Product Activity
        updateProductButton.setOnClickListener(v -> {
            //startActivity(new Intent(this, UpdateProductActivity.class));
        });

        // Navigate to Delete Product Activity
        deleteProductButton.setOnClickListener(v -> {
            startActivity(new Intent(this, DeleteProductActivity.class));
        });
    }
}
