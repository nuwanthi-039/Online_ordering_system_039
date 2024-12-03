package com.example.seu_is_18_ict_039;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AddProductActivity extends AppCompatActivity {

    private EditText productName, productDescription, productPrice;
    private Button selectImageButton, addProductButton;
    private FirebaseFirestore db;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product_activity);

        productName = findViewById(R.id.productName);
        productDescription = findViewById(R.id.productDescription);
        productPrice = findViewById(R.id.productPrice);
        selectImageButton = findViewById(R.id.selectImageButton);
        addProductButton = findViewById(R.id.addProductButton);

        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        selectImageButton.setOnClickListener(v -> {
            // Code to select an image from gallery
            // Use an intent to open image gallery
        });

        addProductButton.setOnClickListener(v -> addProduct());
    }

    private void addProduct() {
        String name = productName.getText().toString();
        String description = productDescription.getText().toString();
        String priceString = productPrice.getText().toString();

        if (name.isEmpty() || description.isEmpty() || priceString.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert priceString to integer
        int price;
        try {
            price = Integer.parseInt(priceString); // Convert String to int
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid price format", Toast.LENGTH_SHORT).show();
            return;
        }

        // Add product to Firestore
        Product product = new Product(name, description, price, R.drawable.laptop);
        db.collection("products")
                .add(product)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(AddProductActivity.this, "Product added successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddProductActivity.this, AdminDashboardActivity.class));
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(AddProductActivity.this, "Error adding product: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

}
