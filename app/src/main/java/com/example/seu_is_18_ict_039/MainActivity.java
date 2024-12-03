package com.example.seu_is_18_ict_039;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    private EditText emailField, passwordField;
    private Button loginButton, registerButton;
    private FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_button);
        db = FirebaseFirestore.getInstance();

        // Set listeners for login and register buttons
        loginButton.setOnClickListener(v -> loginUser());
        registerButton.setOnClickListener(v -> {
            startActivity(new Intent(this, SignupActivity.class)); // Go to registration screen
            finish();
        });
    }

    private void loginUser() {
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if it's admin login
        if (email.equals("admin@example.com") && password.equals("1234")) {
            // Admin login success
            Toast.makeText(this, "Admin Login Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, AdminDashboardActivity.class)); // Navigate to admin dashboard
            finish();
        } else {
            // Customer login, check Firebase Firestore for user credentials
            db.collection("users")
                    .whereEqualTo("email", email)
                    .whereEqualTo("password", password)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful() && !task.getResult().isEmpty()) {
                            // Customer login successful
                            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(this, ProductListActivity.class)); // Navigate to user activity
                            finish();
                        } else {
                            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
