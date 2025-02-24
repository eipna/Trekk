package com.eipna.trekk.ui.activities;

import androidx.activity.EdgeToEdge;

import android.os.Bundle;

import com.eipna.trekk.R;
import com.eipna.trekk.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }
}