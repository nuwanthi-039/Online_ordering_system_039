package com.example.seu_is_18_ict_039;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {

    private EditText accountNumberInput;
    private CheckBox visaCheckbox, masterCardCheckbox, otherCheckbox;
    private Button payButton;
    private ImageView logoutImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        accountNumberInput = findViewById(R.id.accountNumberInput);
        visaCheckbox = findViewById(R.id.visaCheckbox);
        masterCardCheckbox = findViewById(R.id.masterCardCheckbox);
        otherCheckbox = findViewById(R.id.otherCheckbox);
        payButton = findViewById(R.id.payButton);
        logoutImage = findViewById(R.id.logoutImage);

        // Handle payment process
        payButton.setOnClickListener(v -> {
            String accountNumber = accountNumberInput.getText().toString();

            if (accountNumber.isEmpty()) {
                Toast.makeText(PaymentActivity.this, "Please enter account number", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!visaCheckbox.isChecked() && !masterCardCheckbox.isChecked() && !otherCheckbox.isChecked()) {
                Toast.makeText(PaymentActivity.this, "Please select a payment method", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(PaymentActivity.this, "Payment Successful!", Toast.LENGTH_SHORT).show();
        });

        // Handle logout process
        logoutImage.setOnClickListener(v -> {
            Intent intent = new Intent(PaymentActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Close PaymentActivity
        });
    }
}
