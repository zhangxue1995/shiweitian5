package com.dreamers.shiweitian.Report_page;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dreamers.shiweitian.Home_page.SystemBarTintManager;
import com.dreamers.shiweitian.R;
import com.dreamers.shiweitian.Search_page.ListViewAdapter_food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by stzha on 2017/1/9.
 */

public class Feedback_detail extends AppCompatActivity {

    private ImageView img1,img2,img3;
    private ImageView dot1,dot2,dot3,dot4,dot5,dot6,dot7,dot8,dot9,dot10,dot11;
    private TextView tv1,tv2,tv3;
    private TextView text1,text2,text3,text4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_detail);

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

//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeAsUpIndicator(R.drawable.back);
        actionBar.setTitle("             举报跟踪");


        TextView tv1 = (TextView)findViewById(R.id.tv1);//文字粗体
        TextPaint tp1 = tv1.getPaint();
        tp1.setFakeBoldText(true);
        TextView tv2 = (TextView)findViewById(R.id.tv2);//文字粗体
        TextPaint tp2 = tv2.getPaint();
        tp2.setFakeBoldText(true);
        TextView tv3 = (TextView)findViewById(R.id.tv3);//文字粗体
        TextPaint tp3 = tv3.getPaint();
        tp3.setFakeBoldText(true);

        img1=(ImageView)this.findViewById(R.id.img1);
        img2=(ImageView)this.findViewById(R.id.img2);
        img3=(ImageView)this.findViewById(R.id.img3);
        dot1=(ImageView)this.findViewById(R.id.dot1);dot2=(ImageView)this.findViewById(R.id.dot2);
        dot3=(ImageView)this.findViewById(R.id.dot3);dot4=(ImageView)this.findViewById(R.id.dot4);
        dot5=(ImageView)this.findViewById(R.id.dot5);dot6=(ImageView)this.findViewById(R.id.dot6);
        dot7=(ImageView)this.findViewById(R.id.dot7);dot8=(ImageView)this.findViewById(R.id.dot8);
        dot9=(ImageView)this.findViewById(R.id.dot9);dot10=(ImageView)this.findViewById(R.id.dot10);
        dot11=(ImageView)this.findViewById(R.id.dot11);
        Intent intent=getIntent();//getIntent将该项目中包含的原始intent检索出来，将检索出来的intent赋值给一个Intent类型的变量intent
        Bundle bundle=intent.getExtras();//.getExtras()得到intent所附带的额外数据
        String str1=bundle.getString("str1");//getString()返回指定key的值
        String str2=bundle.getString("str2");
        if(str1.equals("已提交")){
            img1.setBackgroundResource(R.drawable.location);
            dot1.setImageResource(R.drawable.dot1_green);
            tv1.setTextColor(Color.BLACK);

        }
        if(str1.equals("已受理")){
            img2.setBackgroundResource(R.drawable.location);
            dot1.setImageResource(R.drawable.dot1_green);dot2.setImageResource(R.drawable.dot1_green);
            dot3.setImageResource(R.drawable.dot1_green);dot4.setImageResource(R.drawable.dot1_green);
            dot5.setImageResource(R.drawable.dot1_green);dot6.setImageResource(R.drawable.dot1_green);
            tv1.setTextColor(Color.BLACK);tv2.setTextColor(Color.BLACK);

        }
        text1=(TextView)this.findViewById(R.id.text1);
        text1.setText(str2);
//        text2=(TextView)this.findViewById(R.id.text2);
//        text2.setText(str2);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Feedback_detail.this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
