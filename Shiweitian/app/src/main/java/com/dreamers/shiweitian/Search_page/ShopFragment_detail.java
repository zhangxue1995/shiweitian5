package com.dreamers.shiweitian.Search_page;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.dreamers.shiweitian.Home_page.SystemBarTintManager;
import com.dreamers.shiweitian.R;
import com.dreamers.shiweitian.Report_page.Report_merchant_detail;

/**
 * Created by stzha on 2017/2/14.
 */

public class ShopFragment_detail  extends AppCompatActivity {

    private TextView month1,month2,month3,month4,month5,month6;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopfragment_detail);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setTintColor(Color.parseColor("#FF4CAF50"));//设置状态栏颜色

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
//
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeAsUpIndicator(R.drawable.back);
        actionBar.setTitle("                 详情");

        Time time = new Time("GMT+8");
        time.setToNow();
        int month = time.month;
        month1=(TextView)this.findViewById(R.id.month1);
        month2=(TextView)this.findViewById(R.id.month2);
        month3=(TextView)this.findViewById(R.id.month3);
        month4=(TextView)this.findViewById(R.id.month4);
        month5=(TextView)this.findViewById(R.id.month5);
        month6=(TextView)this.findViewById(R.id.month6);
        month6.setText(Integer.toString(time.month)+"月");
        month5.setText(Integer.toString(time.month-1<=0?time.month-1+12:time.month-1)+"月");
        month4.setText(Integer.toString(time.month-2<=0?time.month-2+12:time.month-2)+"月");
        month3.setText(Integer.toString(time.month-3<=0?time.month-3+12:time.month-3)+"月");
        month2.setText(Integer.toString(time.month-4<=0?time.month-4+12:time.month-4)+"月");
        month1.setText(Integer.toString(time.month-5<=0?time.month-5+12:time.month-5)+"月");



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                ShopFragment_detail.this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

