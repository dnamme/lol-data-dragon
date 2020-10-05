package com.devnamme.ddragon;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoadingScreenUserGen extends AppCompatActivity {
    String username;
    String club;
    boolean isBlue;
    int spell_left;
    int spell_right;
    int championIndex;
    int championSkinIndex;
    String championSkinName;
    String icon;
    int rune_primary;
    int rune_secondary;

    LoadingScreenUserView genView = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        username = intent.getStringExtra("_username");
        club = intent.getStringExtra("_club");
        isBlue = intent.getBooleanExtra("_isBlue", true);
        spell_left = intent.getIntExtra("_spell_left", 0);
        spell_right = intent.getIntExtra("_spell_right", 0);
        championIndex = intent.getIntExtra("_champion_index", 0);
        championSkinIndex = intent.getIntExtra("_champion_skin_index", 0);
        championSkinName = intent.getStringExtra("_champion_skin_name");
        icon = intent.getStringExtra("_icon");
        rune_primary = intent.getIntExtra("_rune_primary", 0);
        rune_secondary = intent.getIntExtra("_rune_secondary", 0);

        setContentView(R.layout.activity_loading_screen_user_gen);

        genView = findViewById(R.id.loading_screen_user_view);

        genView.setData(username, club, isBlue, spell_left, spell_right, championIndex, championSkinIndex, championSkinName, icon, rune_primary, rune_secondary);
    }
}
