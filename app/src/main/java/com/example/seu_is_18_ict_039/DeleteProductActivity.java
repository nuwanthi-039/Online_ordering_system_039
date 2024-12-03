package com.example.seu_is_18_ict_039;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;

public class DeleteProductActivity extends AppCompatActivity {

    private EditText productIdToDelete;
    private Button deleteProductButton;
    private FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_product);

        productIdToDelete = findViewById(R.id.productIdToDelete);
        deleteProductButton = findViewById(R.id.deleteProductButton);
        db = FirebaseFirestore.getInstance();

        deleteProductButton.setOnClickListener(v -> deleteProduct());
    }

    private void deleteProduct() {
        String productId = productIdToDelete.getText().toString();

        if (productId.isEmpty()) {
            Toast.makeText(this, "Please enter product ID", Toast.LENGTH_SHORT).show();
            return;
        }

        db.collection("products").document(productId)
                .delete()
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(DeleteProductActivity.this, "Product deleted successfully", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(DeleteProductActivity.this, "Error deleting product: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
