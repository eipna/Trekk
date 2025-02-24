package com.eipna.trekk;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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