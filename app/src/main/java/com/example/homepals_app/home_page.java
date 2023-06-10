package com.example.homepals_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.core.view.TintableBackgroundView;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class home_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home_page);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.search) {
                    // เปิดหน้าค้นหา (หรือหน้าที่ต้องการไป)
                    Intent intent = new Intent(home_page.this, search_page.class);
                    startActivity(intent);
                    return true;
                }
                if (itemId == R.id.person) {
                    // เปิดหน้าค้นหา (หรือหน้าที่ต้องการไป)
                    Intent intent = new Intent(home_page.this, profile_page.class);
                    startActivity(intent);
                    return true;
                }

                // รีเทิร์น false เพื่อไม่ปรับเปลี่ยนแท็บอื่น
                return false;
            }

        });

        Button godetail = findViewById(R.id.godetail);
        godetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home_page.this, detail_page.class);
                startActivity(intent);
            }
        });



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home_page.this, add_page.class);
                startActivity(intent);
            }
        });


    }
}