package com.eipna.trekk.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.eipna.trekk.R;
import com.eipna.trekk.databinding.ActivityLoginBinding;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupToolbar();
        setupClickListeners();
    }

    private void setupToolbar() {
        binding.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void setupClickListeners() {
        binding.loginButton.setOnClickListener(v -> {
            String email = binding.emailEditText.getText().toString().trim();
            String password = binding.passwordEditText.getText().toString().trim();

            if (validateInput(email, password)) {
                // TODO: Implement login logic
                Snackbar.make(binding.getRoot(), "Login functionality coming soon!", Snackbar.LENGTH_SHORT).show();
            }
        });

        binding.registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        binding.forgotPasswordButton.setOnClickListener(v -> {
            // TODO: Implement forgot password functionality
            Snackbar.make(binding.getRoot(), "Forgot password functionality coming soon!", Snackbar.LENGTH_SHORT).show();
        });
    }

    private boolean validateInput(String email, String password) {
        boolean isValid = true;

        if (email.isEmpty()) {
            binding.emailLayout.setError("Email is required");
            isValid = false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailLayout.setError("Please enter a valid email");
            isValid = false;
        } else {
            binding.emailLayout.setError(null);
        }

        if (password.isEmpty()) {
            binding.passwordLayout.setError("Password is required");
            isValid = false;
        } else if (password.length() < 6) {
            binding.passwordLayout.setError("Password must be at least 6 characters");
            isValid = false;
        } else {
            binding.passwordLayout.setError(null);
        }

        return isValid;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}