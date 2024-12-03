package com.example.seu_is_18_ict_039;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {
    private EditText nameField, emailField, passwordField;
    private Button signupButton;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nameField = findViewById(R.id.name);
        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);
        signupButton = findViewById(R.id.signup_button);
        db = FirebaseFirestore.getInstance();

        signupButton.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        HashMap<String, String> user = new HashMap<>();
        user.put("name", name);
        user.put("email", email);
        user.put("password", password);

        db.collection("users").add(user).addOnSuccessListener(documentReference -> {
            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }).addOnFailureListener(e -> {
            Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show();
        });
    }
}
