package com.dreamers.shiweitian;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dreamers.shiweitian.Home_page.SystemBarTintManager;
import com.dreamers.shiweitian.Report_page.Report_merchant;

import java.util.ArrayList;

/**
 * Created by stzha on 2017/1/10.
 */

public class Login_page extends AppCompatActivity {

    private EditText editText1,editText2;
    private Button login;
    private String account;
    private String password;
    private TextView register,forget;
    private ProgressDialog p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setTheme(R.style.AppBarTheme);
        setContentView(R.layout.login_page);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setTintColor(Color.parseColor("#FF4CAF50"));//设置状态栏颜色
        if(Build.VERSION.SDK_INT>=21){
            getSupportActionBar().setElevation(0);
        }
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeAsUpIndicator(R.drawable.back);
        actionBar.setTitle("                 登录");

        editText2=(EditText)this.findViewById(R.id.password);
        editText2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);  // 隐藏密码

        editText1=(EditText) findViewById(R.id.account);
        login=(Button) findViewById(R.id.login);
        p=new ProgressDialog(Login_page.this);
        p.setTitle("登录中");
        p.setMessage("登录中，马上就好");

        login.setOnClickListener(new LoginOnclick());

        register=(TextView)this.findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent();
                it.setClass(Login_page.this, Register_page.class);
                startActivity(it);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Login_page.this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class LoginOnclick implements View.OnClickListener
    {
        User user=(User)getApplication();
        public void onClick(View arg0) {
            account=editText1.getText().toString().trim();
            if (account==null||account.length()<=0)
            {
                editText1.requestFocus();
                editText1.setError("对不起，用户名不能为空");
                return;
            }
            else
            {
                account=editText1.getText().toString().trim();
            }
            password=editText2.getText().toString().trim();
            if (password==null||password.length()<=0)
            {
                editText2.requestFocus();
                editText2.setError("对不起，密码不能为空");
                return;
            }
            else
            {
                password=editText2.getText().toString().trim();
            }
            p.show();
            new Thread(new Runnable() {

                public void run() {
                    Operaton operaton=new Operaton();
                    String result=operaton.login("UserServlet", account, password);
                    Message msg=new Message();
//                    msg.obj=result;
//                    handler.sendMessage(msg);

                    if (result.equals("1")){
                        user.isLogin=true;
                        Login_page.this.finish();
                        result="登录成功";
                    }
                    if (result.equals("0")){
                        user.isLogin=false;
                        result="用户名不存在";
                    }
                    if (result.equals("2")){
                        user.isLogin=false;
                        result="密码错误";
                    }

                    msg.obj=result;
                    handler.sendMessage(msg);
                }
            }).start();



        }
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            p.dismiss();
            Toast.makeText(Login_page.this, string, Toast.LENGTH_LONG).show();
            super.handleMessage(msg);
        }
    };

}