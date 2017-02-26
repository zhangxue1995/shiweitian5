package com.dreamers.shiweitian.News_page;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dreamers.shiweitian.Home_page.MainActivity;
import com.dreamers.shiweitian.JsonUtil;
import com.dreamers.shiweitian.Operaton;
import com.dreamers.shiweitian.Personal_information;
import com.dreamers.shiweitian.R;
import com.dreamers.shiweitian.Search_page.myFragmentPagerAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by stzha on 2017/1/8.
 */

public class RecommendFragment extends Fragment {

    private TextView reloading;

    private RelativeLayout layout1,layout2,layout3,layout4,layout5,layout6,layout7,layout8,layout9;
    private TextView title1,title2,title3,title4,title5,title6,title7,title8,title9;
    private TextView source1,source2,source3,source4,source5,source6,source7,source8,source9;
    private TextView date1,date2,date3,date4,date5,date6,date7,date8,date9;
    private ImageView photo1,photo2,photo3,photo4,photo5,photo6,photo7,photo8,photo9;

    private String title01,title02,title03,title04,title05,title06,title07,title08,title09;
    private String source01,source02,source03,source04,source05,source06,source07,source08,source09;
    private String date01,date02,date03,date04,date05,date06,date07,date08,date09;
    private String photo01,photo02,photo03,photo04,photo05,photo06,photo07,photo08,photo09;

    private String id;
    private String source;  //推送内容（文字）
    private String title;  //推送内容（图片）
    private String content;      //时间
    private String time;      //时间
    private String photo;      //时间
    private String category;

    private Bitmap bitmap;


    public RecommendFragment() {
        // Required empty public constructor
    }

//    public void in(){
//        super.onCreateView();
//    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (isNetworkConnected(getContext())==false) {
            View rootView = inflater.inflate(R.layout.recommend, container, false);
            reloading=(TextView) rootView.findViewById(R.id.a2);
            reloading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final Dialog dialog = new Dialog(getActivity(),R.style.Translucent_NoTitle);

                    dialog.setContentView(R.layout.loading);
                    dialog.show();
                    dialog.setCanceledOnTouchOutside(false);

                    final Timer t = new Timer();
                                t.schedule(new TimerTask() {
                                 public void run() {
                                                 dialog.dismiss(); // when the task active then close the dialog
                                              t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
                                             }
                                    }, 2000);

//                    Intent it1 =new Intent();
//                    it1.setClass(getActivity(), News_page.class);
//                    startActivity(it1);

//                    FragmentTransaction ft = getFragmentManager().beginTransaction();
//                    ft.replace(R.id.viewpager, new RecommendFragment());
//                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//                    ft.commit();

//                    getFragmentManager()
//                          .beginTransaction()
//                            .addToBackStack(null)  //将当前fragment加入到返回栈中
//                                 .replace(R.id.viewpager, new RecommendFragment()).commit();

//                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                }
            });
            return rootView;
        }
        else {
            View rootView = inflater.inflate(R.layout.recommend_load,container,false);
           layout1=(RelativeLayout)rootView.findViewById(R.id.layout1);
           layout2=(RelativeLayout)rootView.findViewById(R.id.layout2);
           layout3=(RelativeLayout)rootView.findViewById(R.id.layout3);
           layout4=(RelativeLayout)rootView.findViewById(R.id.layout4);
           layout5=(RelativeLayout)rootView.findViewById(R.id.layout5);
           layout6=(RelativeLayout)rootView.findViewById(R.id.layout6);
           layout7=(RelativeLayout)rootView.findViewById(R.id.layout7);
           layout8=(RelativeLayout)rootView.findViewById(R.id.layout8);
           layout9=(RelativeLayout)rootView.findViewById(R.id.layout9);

           new Thread(new Runnable() {

               public void run() {
                   Operaton operaton=new Operaton();
                   String category =0+"";
                   String result=operaton.loading("NewsServlet", category);
                   List<News> list= JsonUtil.StringFromJson(result);

                   source01=list.get(0).getSource();
                   title01=list.get(0).getTitle();
                   date01=list.get(0).getTime();
                   photo01= list.get(0).getPhoto();

                   source02=list.get(1).getSource();
                   title02=list.get(1).getTitle();
                   date02=list.get(1).getTime();
                   photo02= list.get(1).getPhoto();

                   source03=list.get(2).getSource();
                   title03=list.get(2).getTitle();
                   date03=list.get(2).getTime();
                   photo03= list.get(2).getPhoto();

                   source04=list.get(3).getSource();
                   title04=list.get(3).getTitle();
                   date04=list.get(3).getTime();
                   photo04= list.get(3).getPhoto();

                   source05=list.get(4).getSource();
                   title05=list.get(4).getTitle();
                   date05=list.get(4).getTime();
                   photo05= list.get(4).getPhoto();

                   source06=list.get(5).getSource();
                   title06=list.get(5).getTitle();
                   date06=list.get(5).getTime();
                   photo06= list.get(5).getPhoto();

                   Message msg_title1=new Message();
                   msg_title1.obj=title01;
                   handler_title1.sendMessage(msg_title1);

                   Message msg_source1=new Message();
                   msg_source1.obj=source01;
                   handler_source1.sendMessage(msg_source1);

                   Message msg_date1=new Message();
                   msg_date1.obj=date01;
                   handler_date1.sendMessage(msg_date1);

                   Message msg_title2=new Message();
                   msg_title2.obj=title02;
                   handler_title2.sendMessage(msg_title2);

                   Message msg_source2=new Message();
                   msg_source2.obj=source02;
                   handler_source2.sendMessage(msg_source2);

                   Message msg_date2=new Message();
                   msg_date2.obj=date02;
                   handler_date2.sendMessage(msg_date2);

                   Message msg_title3=new Message();
                   msg_title3.obj=title03;
                   handler_title3.sendMessage(msg_title3);

                   Message msg_source3=new Message();
                   msg_source3.obj=source03;
                   handler_source3.sendMessage(msg_source3);

                   Message msg_date3=new Message();
                   msg_date3.obj=date03;
                   handler_date3.sendMessage(msg_date3);

                   Message msg_title4=new Message();
                   msg_title4.obj=title04;
                   handler_title4.sendMessage(msg_title4);

                   Message msg_source4=new Message();
                   msg_source4.obj=source04;
                   handler_source4.sendMessage(msg_source4);

                   Message msg_date4=new Message();
                   msg_date4.obj=date04;
                   handler_date4.sendMessage(msg_date4);

                   Message msg_title5=new Message();
                   msg_title5.obj=title05;
                   handler_title5.sendMessage(msg_title5);

                   Message msg_source5=new Message();
                   msg_source5.obj=source05;
                   handler_source5.sendMessage(msg_source5);

                   Message msg_date5=new Message();
                   msg_date5.obj=date05;

                   Message msg_title6=new Message();
                   msg_title6.obj=title06;
                   handler_title6.sendMessage(msg_title6);

                   Message msg_source6=new Message();
                   msg_source6.obj=source06;
                   handler_source6.sendMessage(msg_source6);

                   Message msg_date6=new Message();
                   msg_date6.obj=date06;



                   String url="http://60.205.182.144:8080/shiweitian3/images/"+photo01;
                   try {
                       bitmap=getBitmap(url);
                   }
                   catch (Exception e){

                   }
                   Message msg_photo1=new Message();
                   msg_photo1.obj=bitmap;
                   handler_photo1.sendMessage(msg_photo1);

               }
           }).start();

            title1=(TextView)rootView.findViewById(R.id.title1);
            title2=(TextView)rootView.findViewById(R.id.title2);
            title3=(TextView)rootView.findViewById(R.id.title3);
            title4=(TextView)rootView.findViewById(R.id.title4);
            title5=(TextView)rootView.findViewById(R.id.title5);
            title6=(TextView)rootView.findViewById(R.id.title6);
            title7=(TextView)rootView.findViewById(R.id.title7);
            title8=(TextView)rootView.findViewById(R.id.title8);
            title9=(TextView)rootView.findViewById(R.id.title9);

            source1=(TextView)rootView.findViewById(R.id.source1);
            source2=(TextView)rootView.findViewById(R.id.source2);
            source3=(TextView)rootView.findViewById(R.id.source3);
            source4=(TextView)rootView.findViewById(R.id.source4);
            source5=(TextView)rootView.findViewById(R.id.source5);
            source6=(TextView)rootView.findViewById(R.id.source6);
            source7=(TextView)rootView.findViewById(R.id.source7);
            source8=(TextView)rootView.findViewById(R.id.source8);
            source9=(TextView)rootView.findViewById(R.id.source9);

            date1=(TextView)rootView.findViewById(R.id.date1);
            date2=(TextView)rootView.findViewById(R.id.date2);
            date3=(TextView)rootView.findViewById(R.id.date3);
            date4=(TextView)rootView.findViewById(R.id.date4);



            photo1=(ImageView)rootView.findViewById(R.id.img1);
            photo2=(ImageView)rootView.findViewById(R.id.img2);
            photo3=(ImageView)rootView.findViewById(R.id.img3);
            photo4=(ImageView)rootView.findViewById(R.id.img4);
            photo5=(ImageView)rootView.findViewById(R.id.img5);
            photo6=(ImageView)rootView.findViewById(R.id.img6);


            layout1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Intent it =new Intent();
                    it.setClass(getActivity(), News_detail.class);
//                it.putExtra("str1",listItems.get(position).get("title1").toString() );//给intent添加额外数据，key为“str”,key值为"Intent Demo"
//                it.putExtra("str2",listItems.get(position).get("title2").toString() );
                    startActivity(it);
                }
            });
            layout2.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent it =new Intent();
                    it.setClass(getActivity(), News_detail.class);
//                it.putExtra("str1",listItems.get(position).get("title1").toString() );//给intent添加额外数据，key为“str”,key值为"Intent Demo"
//                it.putExtra("str2",listItems.get(position).get("title2").toString() );
                    startActivity(it);
                }
            });
            layout3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it =new Intent();
                    it.setClass(getActivity(), News_detail.class);
//                it.putExtra("str1",listItems.get(position).get("title1").toString() );//给intent添加额外数据，key为“str”,key值为"Intent Demo"
//                it.putExtra("str2",listItems.get(position).get("title2").toString() );
                    startActivity(it);
                }
            });
            layout4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it =new Intent();
                    it.setClass(getActivity(), News_detail.class);
//                it.putExtra("str1",listItems.get(position).get("title1").toString() );//给intent添加额外数据，key为“str”,key值为"Intent Demo"
//                it.putExtra("str2",listItems.get(position).get("title2").toString() );
                    startActivity(it);
                }
            });
        layout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent();
                it.setClass(getActivity(), News_detail.class);
//                it.putExtra("str1",listItems.get(position).get("title1").toString() );//给intent添加额外数据，key为“str”,key值为"Intent Demo"
//                it.putExtra("str2",listItems.get(position).get("title2").toString() );
                startActivity(it);
            }
        });
        layout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent();
                it.setClass(getActivity(), News_detail.class);
//                it.putExtra("str1",listItems.get(position).get("title1").toString() );//给intent添加额外数据，key为“str”,key值为"Intent Demo"
//                it.putExtra("str2",listItems.get(position).get("title2").toString() );
                startActivity(it);
            }
        });
        layout7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent();
                it.setClass(getActivity(), News_detail.class);
//                it.putExtra("str1",listItems.get(position).get("title1").toString() );//给intent添加额外数据，key为“str”,key值为"Intent Demo"
//                it.putExtra("str2",listItems.get(position).get("title2").toString() );
                startActivity(it);
            }
        });
        layout8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent();
                it.setClass(getActivity(), News_detail.class);
//                it.putExtra("str1",listItems.get(position).get("title1").toString() );//给intent添加额外数据，key为“str”,key值为"Intent Demo"
//                it.putExtra("str2",listItems.get(position).get("title2").toString() );
                startActivity(it);
            }
        });
        layout9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent();
                it.setClass(getActivity(), News_detail.class);
//                it.putExtra("str1",listItems.get(position).get("title1").toString() );//给intent添加额外数据，key为“str”,key值为"Intent Demo"
//                it.putExtra("str2",listItems.get(position).get("title2").toString() );
                startActivity(it);
            }
        });

        return rootView;
        }



    }


    Handler handler_title1=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            title1.setText(string);
            super.handleMessage(msg);
        }
    };
    Handler handler_source1=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            source1.setText(string);
            super.handleMessage(msg);
        }
    };
    Handler handler_date1=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            date1.setText(string);
            super.handleMessage(msg);
        }
    };
    Handler handler_photo1=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bitmap string=(Bitmap) msg.obj;
            photo1.setImageBitmap(string);
            super.handleMessage(msg);
        }
    };

    Handler handler_title2=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            title2.setText(string);
            super.handleMessage(msg);
        }
    };
    Handler handler_source2=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            source2.setText(string);
            super.handleMessage(msg);
        }
    };
    Handler handler_date2=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            date2.setText(string);
            super.handleMessage(msg);
        }
    };

    Handler handler_title3=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            title3.setText(string);
            super.handleMessage(msg);
        }
    };
    Handler handler_source3=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            source3.setText(string);
            super.handleMessage(msg);
        }
    };
    Handler handler_date3=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            date3.setText(string);
            super.handleMessage(msg);
        }
    };

    Handler handler_title4=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            title4.setText(string);
            super.handleMessage(msg);
        }
    };
    Handler handler_source4=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            source4.setText(string);
            super.handleMessage(msg);
        }
    };
    Handler handler_date4=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            date4.setText(string);
            super.handleMessage(msg);
        }
    };

    Handler handler_title5=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            title5.setText(string);
            super.handleMessage(msg);
        }
    };
    Handler handler_source5=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            source5.setText(string);
            super.handleMessage(msg);
        }
    };

    Handler handler_title6=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            title6.setText(string);
            super.handleMessage(msg);
        }
    };
    Handler handler_source6=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            source6.setText(string);
            super.handleMessage(msg);
        }
    };

    Handler handler_title7=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            title7.setText(string);
            super.handleMessage(msg);
        }
    };
    Handler handler_source7=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            source7.setText(string);
            super.handleMessage(msg);
        }
    };

    Handler handler_title8=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            title8.setText(string);
            super.handleMessage(msg);
        }
    };
    Handler handler_source8=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            source8.setText(string);
            super.handleMessage(msg);
        }
    };


    //通过URL获取图片
    public static Bitmap getBitmap(String path) throws IOException {
        path="http://60.205.182.144:8080/shiweitian3/images/2_1.jpg";
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if(conn.getResponseCode() == 200){
            InputStream inputStream = conn.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }
        return null;
    }

    //判断网络是否连接
    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }




}