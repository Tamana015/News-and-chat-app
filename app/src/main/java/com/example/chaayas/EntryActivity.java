package com.example.chaayas;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.widget.ScrollView;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.widget.TextView;

public class EntryActivity extends AppCompatActivity {
    private ConstraintLayout m1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        m1 = findViewById(R.id.newspage);
        Load_setting();

    }
        private void Load_setting() {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
            boolean chk_night = sp.getBoolean("NIGHT", false);
            if (chk_night) {
                m1.setBackgroundColor(Color.parseColor("#50505050"));
            } else {
                m1.setBackgroundColor(Color.parseColor("#ffffff"));
            }


            String orien = sp.getString("ORIENTATION", "false");
            if ("1".equals(orien)) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
            } else if ("2".equals(orien)) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            } else if ("3".equals(orien)) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }

        }
        @Override
        protected void onResume() {
            Load_setting();
            super.onResume();
        }
    }