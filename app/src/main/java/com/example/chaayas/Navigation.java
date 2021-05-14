 package com.example.chaayas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class Navigation extends AppCompatActivity {
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    androidx.appcompat.widget.Toolbar toolbar;
    TextView textElement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        Load_setting();

        Intent intent = getIntent();
        String name = intent.getStringExtra("name_key");
        NavigationView nav= findViewById(R.id.navmenu);
        View headerview = nav.getHeaderView(0);
        TextView newtext = headerview.findViewById(R.id.user);
            newtext.setText(name);
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();

                nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.menu_setting:
                                Toast.makeText(getApplicationContext(), "Setting Panel is Open", Toast.LENGTH_LONG).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                Intent intent1 = new Intent(Navigation.this, Preference.class);
                                startActivity(intent1);
                                break;
                            case R.id.menu_home:
                                Toast.makeText(getApplicationContext(), "News Panel is Open", Toast.LENGTH_LONG).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                Intent intent = new Intent(Navigation.this, EntryActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.menu_chat:
                                Toast.makeText(getApplicationContext(), "Chat Panel is Open", Toast.LENGTH_LONG).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                Intent intent2 = new Intent(Navigation.this, chat_activity.class);
                                intent2.putExtra("name_key1", name);
                                startActivity(intent2);
                                break;

                        }
                        return true;
                    }
                });

            }

    private void Load_setting() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        boolean chk_night = sp.getBoolean("NIGHT", false);
        if (chk_night) {
            drawerLayout.setBackgroundResource(R.drawable.darkimggreen);
        }
        else
        {
            drawerLayout.setBackgroundResource(R.drawable.greenimg);
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