package com.dreamers.shiweitian.Startup_page;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.WindowManager;

import com.dreamers.shiweitian.Home_page.MainActivity;
import com.dreamers.shiweitian.R;

public class Startup_page extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGHT = 3000; // 3秒后进入系统

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_startup_page);

        getSupportActionBar().hide();
        new android.os.Handler().postDelayed(new Runnable() {
            public void run() {
                Intent mainIntent = new Intent(Startup_page.this,
                        MainActivity.class);
                Startup_page.this.startActivity(mainIntent);
                Startup_page.this.finish();
            }

        }, SPLASH_DISPLAY_LENGHT);
    }
}
