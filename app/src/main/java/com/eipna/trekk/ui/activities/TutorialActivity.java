package com.eipna.trekk.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.eipna.trekk.R;
import com.eipna.trekk.databinding.ActivityTutorialBinding;
import com.eipna.trekk.databinding.ItemTutorialPageBinding;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class TutorialActivity extends AppCompatActivity {
    private ActivityTutorialBinding binding;
    private List<TutorialPage> tutorialPages;
    private TutorialAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityTutorialBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupToolbar();
        setupTutorialPages();
        setupViewPager();
        setupClickListeners();
    }

    private void setupToolbar() {
        binding.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void setupTutorialPages() {
        tutorialPages = new ArrayList<>();
        tutorialPages.add(new TutorialPage(
            "Welcome to Trekking!",
            "Trekking is an exciting outdoor activity that combines hiking and camping. " +
            "It's a great way to explore nature, challenge yourself, and create unforgettable memories.",
            R.drawable.illustration_welcome
        ));
        tutorialPages.add(new TutorialPage(
            "Essential Equipment",
            "• Sturdy hiking boots\n" +
            "• Weather-appropriate clothing\n" +
            "• Backpack (30-50L)\n" +
            "• Water bottles (2-3L)\n" +
            "• First aid kit\n" +
            "• Navigation tools (map, compass)\n" +
            "• Headlamp/flashlight\n" +
            "• Multi-tool or knife",
            R.drawable.illustration_equip
        ));
        tutorialPages.add(new TutorialPage(
            "Safety First",
            "• Always check weather conditions\n" +
            "• Inform someone about your trek plan\n" +
            "• Stay on marked trails\n" +
            "• Keep emergency contacts handy\n" +
            "• Carry enough water and food\n" +
            "• Know basic first aid\n" +
            "• Respect wildlife and nature",
            R.drawable.illustration_safety
        ));
        tutorialPages.add(new TutorialPage(
            "Physical Preparation",
            "• Start with shorter treks\n" +
            "• Build endurance gradually\n" +
            "• Practice with a loaded backpack\n" +
            "• Stay hydrated and well-rested\n" +
            "• Know your limits\n" +
            "• Take regular breaks\n" +
            "• Listen to your body",
            R.drawable.illustration_prepare
        ));
        tutorialPages.add(new TutorialPage(
            "Leave No Trace",
            "• Pack out all trash\n" +
            "• Stay on designated trails\n" +
            "• Respect wildlife\n" +
            "• Camp in designated areas\n" +
            "• Minimize campfire impact\n" +
            "• Be considerate of other hikers\n" +
            "• Leave nature as you found it",
            R.drawable.illustration_trace
        ));
    }

    private void setupViewPager() {
        adapter = new TutorialAdapter();
        binding.viewPager.setAdapter(adapter);
        
        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
            (tab, position) -> {
                // Empty implementation as we're using custom tab indicators
            }).attach();

        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                updateNavigationButtons(position);
            }
        });
    }

    private void setupClickListeners() {
        binding.nextButton.setOnClickListener(v -> {
            int currentItem = binding.viewPager.getCurrentItem();
            if (currentItem < tutorialPages.size() - 1) {
                binding.viewPager.setCurrentItem(currentItem + 1);
            } else {
                navigateToMainActivity();
            }
        });

        binding.skipButton.setOnClickListener(v -> {
            navigateToMainActivity();
        });
    }

    private void updateNavigationButtons(int position) {
        if (position == tutorialPages.size() - 1) {
            binding.nextButton.setText("Get Started");
        } else {
            binding.nextButton.setText("Next");
        }
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private class TutorialAdapter extends RecyclerView.Adapter<TutorialAdapter.TutorialViewHolder> {
        @NonNull
        @Override
        public TutorialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ItemTutorialPageBinding itemBinding = ItemTutorialPageBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
            return new TutorialViewHolder(itemBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull TutorialViewHolder holder, int position) {
            holder.bind(tutorialPages.get(position));
        }

        @Override
        public int getItemCount() {
            return tutorialPages.size();
        }

        class TutorialViewHolder extends RecyclerView.ViewHolder {
            private final ItemTutorialPageBinding binding;

            TutorialViewHolder(ItemTutorialPageBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }

            void bind(TutorialPage page) {
                binding.titleTextView.setText(page.title);
                binding.descriptionTextView.setText(page.description);
                binding.imageView.setImageResource(page.imageResId);
            }
        }
    }

    private static class TutorialPage {
        final String title;
        final String description;
        final int imageResId;

        TutorialPage(String title, String description, int imageResId) {
            this.title = title;
            this.description = description;
            this.imageResId = imageResId;
        }
    }
} 