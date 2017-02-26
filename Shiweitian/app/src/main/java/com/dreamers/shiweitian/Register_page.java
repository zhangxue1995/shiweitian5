package com.dreamers.shiweitian;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dreamers.shiweitian.Home_page.SystemBarTintManager;

/**
 * Created by stzha on 2017/1/10.
 */

public class Register_page extends AppCompatActivity {

    private EditText editText1,editText2,editText3;
    private Button register;
    private String name,account,password;
    private ProgressDialog p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setTheme(R.style.AppBarTheme);
        setContentView(R.layout.register_page);

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
        actionBar.setTitle("                 注册");

        editText1=(EditText)this.findViewById(R.id.name);
        editText2=(EditText)this.findViewById(R.id.account);
        editText3=(EditText)this.findViewById(R.id.password);
        editText3.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        register=(Button) findViewById(R.id.register);
        register.setOnClickListener(new RegisterOnclick());

        p=new ProgressDialog(Register_page.this);
        p.setTitle("注册中");
        p.setMessage("注册中，马上就好");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Register_page.this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class RegisterOnclick implements View.OnClickListener
    {
        User user=(User)getApplication();
        public void onClick(View arg0) {
            name=editText1.getText().toString().trim();
            if (name==null||name.length()<=0)
            {
                editText1.requestFocus();
                editText1.setError("昵称不能为空");
                return;
            }
            else
            {
                name=editText1.getText().toString().trim();
            }
            account=editText2.getText().toString().trim();
            if (account==null||account.length()<=0)
            {
                editText2.requestFocus();
                editText2.setError("用户名不能为空");
                return;
            }
            else
            {
                account=editText2.getText().toString().trim();
            }
            password=editText3.getText().toString().trim();
            if (password==null||password.length()<=0)
            {
                editText3.requestFocus();
                editText3.setError("密码不能为空");
                return;
            }
            else
            {
                password=editText3.getText().toString().trim();
            }
            p.show();
            new Thread(new Runnable() {

                public void run() {
                    Operaton operaton=new Operaton();
                    String result=operaton.register("UserServlet", name,account, password);
                    Message msg=new Message();
//                    msg.obj=result;
//                    handler.sendMessage(msg);

                    if (result.equals("2")){
                        user.isLogin=true;
                        Register_page.this.finish();
                        result="注册成功";
                    }
                    if (result.equals("1")){
                        user.isLogin=false;
                        result="用户名已存在";
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
            Toast.makeText(Register_page.this, string, Toast.LENGTH_LONG).show();
            super.handleMessage(msg);
        }
    };

}