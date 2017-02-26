package com.dreamers.shiweitian.Report_page;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dreamers.shiweitian.Home_page.SystemBarTintManager;
import com.dreamers.shiweitian.Personal_information;
import com.dreamers.shiweitian.R;
import com.dreamers.shiweitian.TitleActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by stzha on 2017/1/9.
 */

public class Report_merchant_detail extends AppCompatActivity{

    private Button submit;
    private RelativeLayout upload_img;
    private EditText editText1,editText2;
    private String imageFilePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_merchant_detail);

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
        actionBar.setTitle("             举报商家");

        submit=(Button)this.findViewById(R.id.submit);

        upload_img=(RelativeLayout) this.findViewById(R.id.layout3);
        upload_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater in = LayoutInflater.from(getBaseContext());
                View view = in.inflate(R.layout.photo_select, null);
                new AlertDialog.Builder(Report_merchant_detail.this)
                        .setTitle("头像选择")
                        .setView(view)
                        .setNegativeButton("取消", null)
                        .show();
                ImageView paizhao=(ImageView)view.findViewById(R.id.paizhao);
                paizhao.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
//						 TODO Auto-generated method stub

                        imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/filename.jpg";
                        File temp = new File(imageFilePath);
                        Uri imageFileUri = Uri.fromFile(temp);//获取文件的Uri
                        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//跳转到相机Activity
                        it.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);//告诉相机拍摄完毕输出图片到指定的Uri
                        startActivityForResult(it, 102);
                    }
                });

                ImageView tupianku=(ImageView)view.findViewById(R.id.tupianku);
                tupianku.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        // 如果朋友们要限制上传到服务器的图片类型时可以直接写如：image/jpeg 、 image/png等的类型
//			            pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, image/png);
                        startActivityForResult(pickIntent, 2);

                    }
                });

            }
        });

        editText1=(EditText)this.findViewById(R.id.editText1);
        editText2=(EditText)this.findViewById(R.id.editText2);
        editText1.addTextChangedListener(textWatcher);
        editText2.addTextChangedListener(textWatcher);


    }
    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if(editText1.getText().toString().trim().length()>0&&editText2.getText().toString().trim().length()>0) {
                submit.setBackgroundColor(Color.parseColor("#FF4CAF50"));
            }
            else
                submit.setBackgroundColor(Color.parseColor("#aab3b3b3"));


        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode == RESULT_OK) {  //返回成功
            switch (requestCode) {
                case 102:  {//拍照获取图片
                    String status = Environment.getExternalStorageState();
                    if (resultCode == Activity.RESULT_OK) {

                        Bitmap bmp = BitmapFactory.decodeFile(imageFilePath);
                        Drawable drawable =new BitmapDrawable(bmp);
                        upload_img.setBackground(drawable);

                    }
                    break;
                }

                case 2:  {//相册获取图片
                    String status = Environment.getExternalStorageState();
                    if (resultCode == RESULT_OK && null != data) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = { MediaStore.Images.Media.DATA };

                        Cursor cursor = getContentResolver().query(selectedImage,
                                filePathColumn, null, null, null);
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String picturePath = cursor.getString(columnIndex);
                        cursor.close();

                        Drawable drawable =new BitmapDrawable(BitmapFactory.decodeFile(picturePath));
                        upload_img.setBackground(drawable);

                    }
                    break;
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Report_merchant_detail.this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

