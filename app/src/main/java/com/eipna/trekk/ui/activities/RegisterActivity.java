package com.eipna.trekk.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.eipna.trekk.R;
import com.eipna.trekk.databinding.ActivityRegisterBinding;
import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
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
        binding.registerButton.setOnClickListener(v -> {
            String name = binding.nameEditText.getText().toString().trim();
            String email = binding.emailEditText.getText().toString().trim();
            String password = binding.passwordEditText.getText().toString().trim();
            String confirmPassword = binding.confirmPasswordEditText.getText().toString().trim();

            if (validateInput(name, email, password, confirmPassword)) {
                // TODO: Implement registration logic
                Snackbar.make(binding.getRoot(), "Registration functionality coming soon!", Snackbar.LENGTH_SHORT).show();
            }
        });

        binding.loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
    }

    private boolean validateInput(String name, String email, String password, String confirmPassword) {
        boolean isValid = true;

        if (name.isEmpty()) {
            binding.nameLayout.setError("Name is required");
            isValid = false;
        } else {
            binding.nameLayout.setError(null);
        }

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

        if (confirmPassword.isEmpty()) {
            binding.confirmPasswordLayout.setError("Please confirm your password");
            isValid = false;
        } else if (!confirmPassword.equals(password)) {
            binding.confirmPasswordLayout.setError("Passwords do not match");
            isValid = false;
        } else {
            binding.confirmPasswordLayout.setError(null);
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