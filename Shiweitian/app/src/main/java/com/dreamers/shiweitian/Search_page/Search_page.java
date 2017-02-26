package com.dreamers.shiweitian.Search_page;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dreamers.shiweitian.Home_page.MainActivity;
import com.dreamers.shiweitian.News_page.News_page;
import com.dreamers.shiweitian.Personal_information;
import com.dreamers.shiweitian.QandA_page.QandA_page;
import com.dreamers.shiweitian.R;
import com.dreamers.shiweitian.Home_page.SystemBarTintManager;
import com.dreamers.shiweitian.Report_page.Report_page;

import java.util.ArrayList;

/**
 * Created by stzha on 2017/1/8.
 */

public class Search_page extends AppCompatActivity implements OnClickListener ,RadioGroup.OnCheckedChangeListener{
    private TextView tab1;
    private TextView tab2;
    private TextView tab3;
    private TextView tab4;
    private TextView tab5;

    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private RadioButton rbOrder,rbEvaluation,rbMerchant;
    private ArrayList<Fragment> alFragment;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setTintColor(Color.parseColor("#FF4CAF50"));//设置状态栏颜色

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.user_photo);
        actionBar.setTitle("这是昵称");

        //初始化界面组件
        initView();
        //初始化ViewPager
        initViewPager();

        //底部导航栏
        tab1 = (TextView)this.findViewById(R.id.txt_1);
        tab2 = (TextView)this.findViewById(R.id.txt_2);
        tab3 = (TextView)this.findViewById(R.id.txt_3);
        tab4 = (TextView)this.findViewById(R.id.txt_4);
        tab5 = (TextView)this.findViewById(R.id.txt_5);
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);
        tab4.setOnClickListener(this);
        tab5.setOnClickListener(this);
        tab5.setSelected(true);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent it =new Intent();
                it.setClass(Search_page.this, Personal_information.class);
                startActivity(it);
                //    MainActivity.this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //重置所有文本的选中状态
    public void selected(){
        tab1.setSelected(false);
        tab2.setSelected(false);
        tab3.setSelected(false);
        tab4.setSelected(false);
        tab5.setSelected(false);
    }
    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.txt_1:
                selected();
                tab1.setSelected(true);
                Intent it1 =new Intent();
                it1.setClass(Search_page.this, MainActivity.class);
                startActivity(it1);
                Search_page.this.finish();
                break;

            case R.id.txt_2:
                selected();
                tab2.setSelected(true);
                Intent it2 =new Intent();
                it2.setClass(Search_page.this, News_page.class);
                startActivity(it2);
                Search_page.this.finish();
                break;

            case R.id.txt_3:
                selected();
                tab3.setSelected(true);
                Intent it3 =new Intent();
                it3.setClass(Search_page.this, Report_page.class);
                startActivity(it3);
                Search_page.this.finish();
                break;

            case R.id.txt_4:
                selected();
                tab4.setSelected(true);
                Intent it4 =new Intent();
                it4.setClass(Search_page.this, QandA_page.class);
                startActivity(it4);
                Search_page.this.finish();
                break;

            case R.id.txt_5:
                selected();
                tab5.setSelected(true);
                break;
        }

//        transaction.commit();
    }

    private void initView(){
        viewPager=(ViewPager) findViewById(R.id.viewpager);
        radioGroup=(RadioGroup) findViewById(R.id.radiogroup);
        rbOrder=(RadioButton) findViewById(R.id.rb_order);
        rbEvaluation=(RadioButton) findViewById(R.id.rb_evaluation);
        radioGroup.setOnCheckedChangeListener(this);
    }

    private void initViewPager(){
        FoodFragment orderFragment=new FoodFragment();
        ShopFragment evaluationFragment=new ShopFragment();
        alFragment=new ArrayList<>();
        alFragment.add(orderFragment);
        alFragment.add(evaluationFragment);
        //ViewPager设置适配器
        viewPager.setAdapter(new myFragmentPagerAdapter(getSupportFragmentManager(), alFragment));
        //ViewPager显示第一个Fragment
        viewPager.setCurrentItem(0);
        //ViewPager页面切换监听
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            //滑动ViewPager,RadioButton选中状态做相应变换
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        radioGroup.check(R.id.rb_order);
                        break;
                    case 1:
                        radioGroup.check(R.id.rb_evaluation);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 点击RadioButton切换ViewPager中相应的Fragment
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_order:
                viewPager.setCurrentItem(0,false);
                break;
            case R.id.rb_evaluation:
                viewPager.setCurrentItem(1,false);
                break;
        }
    }

}
