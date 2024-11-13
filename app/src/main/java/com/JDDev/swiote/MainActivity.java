package com.JDDev.swiote;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager2.widget.ViewPager2;

import com.JDDev.swiote.CardAdapter;
import com.JDDev.swiote.Quote;
import com.JDDev.swiote.R;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Quote> loadQuotesFromAssets() {
        List<Quote> quotes = new ArrayList<>();
        try {
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open("data.json");
            InputStreamReader reader = new InputStreamReader(inputStream);
            Type quoteListType = new TypeToken<List<Quote>>() {
            }.getType();
            quotes = new Gson().fromJson(reader, quoteListType);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return quotes;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Quote> quotes = loadQuotesFromAssets();
        CardAdapter adapter = new CardAdapter(quotes);
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);

        Button nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(v -> {
            int currentItem = viewPager.getCurrentItem();
            if (currentItem < quotes.size() - 1) {
                viewPager.setCurrentItem(currentItem + 1);
            }
        });
    }
}
