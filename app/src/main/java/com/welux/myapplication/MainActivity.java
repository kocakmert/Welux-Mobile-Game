package com.welux.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.HashMap;
import java.util.Locale;
import java.util.Random;
public class MainActivity extends AppCompatActivity implements RewardedVideoAdListener {
    private ProgressBar progressBar;
    private Dialog epic_try,epic_info;
    private AdView mAdView;
    private Button left_button,right_button,btn_block,btn_absoulte,btn_leftarrow,btn_rightarrow,btn_retry,btn_main_menu,left_block_motion,right_block_motion;
    private Button btn_next,btn_home,btn_info_block,btn_info_absoulute,btn_ads;
    private TextView try_baslık,try_message,txt_rules,txt_coin,sum,left_adet,right_adet,game_level_count,coin_txtview3,txt_level,x_left,x_right,txt_info_title,txt_absolute_info2,txt_info_block_1,txt_info_center,txt_timer;
    private ImageView icon,img_pop_ex,img_left_cong,img_right_cong;
    private  Button img_View12,img_View10;
    private int number1,number2,int_sum,counter1,counter2,negatif_countleft,positive_countleft,negatif_countright,positive_countright,bitirme_left,bitirme_right,bitirme_finish,coin,int_secenek;
    private SessionManager sessionManager;
    private Random rnd =new Random();
    private String str_level,str_adet,str_coin,str_generate,str_welevel;
    private int adet,generate,int_welevel,sound_deger,vibrate_deger,level,language_deger,time_deger,time_minute,stopping,progres_value;
    private MotionLayout mt;
    private MediaPlayer card_flip,card_flip2,button_click,success,btn_click1;
    private Vibrator vibrator;
    final private int pulse =60;
    private Locale locale;
    private Configuration config;
    private CountDownTimer count1,count2,countDownTimer,count3,times,cointimes;
    private int millileft,kazanc;
    private RewardedVideoAd mRewardedVideoAd;
    private Typeface custom_font,custom_font_gameria,font_Rule;
    private static final String AD_UNIT_ID = "ca-app-pub-2217525225495117/9824933252";
    private static final String APP_ID = "ca-app-pub-2217525225495117~7645875944";
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // status bar hide...
        //   ....Game Control....
        //     ....Session....
        sessionManager=new SessionManager(this);
        int deger =sessionManager.checkLogin();
        sound_deger=sessionManager.getSoundDetail();
        vibrate_deger=sessionManager.getVibrateDetail();
        language_deger=sessionManager.getLanguageDetail();
        time_deger=sessionManager.getTimeDetail();
        mt = (MotionLayout) findViewById(R.id.mmLayout);
        game_level_count = findViewById(R.id.game_level_count);
        txt_level =findViewById(R.id.level);
        txt_rules=findViewById(R.id.rules);
        txt_coin = findViewById(R.id.coin);
        progressBar=findViewById(R.id.simpleProgressBar);
        // ...  BackgroundColor ...
        Random rnd1 =new Random();
        int color_number=rnd1.nextInt(9);
        if(color_number ==0){
            mt.setBackgroundColor(Color.parseColor("#160036"));
            progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#3452a5"), PorterDuff.Mode.SRC_IN);}
        else if(color_number == 1){
            mt.setBackgroundColor(Color.parseColor("#262f3e"));}
        else if(color_number == 2){
            mt.setBackgroundColor(Color.parseColor("#ab8282"));
            progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#3452a5"), PorterDuff.Mode.SRC_IN);}
        else if(color_number == 3){
            mt.setBackgroundColor(Color.parseColor("#7fa7ae")); //  light blue
            progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#3452a5"), PorterDuff.Mode.SRC_IN);}
        else if(color_number == 4){
            mt.setBackgroundColor(Color.parseColor("#4f1414"));
            progressBar.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN); }
        else if(color_number == 5){
            mt.setBackgroundColor(Color.parseColor("#51694a"));  // dark green
            progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#3452a5"), PorterDuff.Mode.SRC_IN);}
        else if(color_number == 6){
            mt.setBackgroundColor(Color.parseColor("#114444"));  // so dark green
            progressBar.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);}
        else if(color_number == 7){
            mt.setBackgroundColor(Color.parseColor("#0b0a0a"));
            progressBar.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);}
        else if(color_number == 8){
            mt.setBackgroundColor(Color.parseColor("#3f6087"));
            progressBar.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);}
        // ....Fonts....
        custom_font= Typeface.createFromAsset(getAssets(), "fonts/orange kid.ttf");
        custom_font_gameria= Typeface.createFromAsset(getAssets(), "fonts/GAMERIA.ttf");
        font_Rule= Typeface.createFromAsset(getAssets(), "fonts/font31.ttf");
        txt_level.setTypeface(custom_font_gameria);
        game_level_count.setTypeface(custom_font_gameria);
        txt_coin.setTypeface(custom_font_gameria);
        txt_rules.setTypeface(font_Rule);
        // ...    ....
        epic_info=new Dialog(this);
        if(sound_deger ==0){
            //  .... Music Sound--Effect ....
            card_flip =MediaPlayer.create(this,R.raw.card_flip);
            card_flip2=MediaPlayer.create(this,R.raw.card_flip2);
            button_click=MediaPlayer.create(this,R.raw.press);
            btn_click1=MediaPlayer.create(this,R.raw.button_click3);
            success = MediaPlayer.create(this,R.raw.success);
        }
        if(deger ==0){
            mt.setBackgroundColor(Color.parseColor("#262f3e"));
            txt_rules.setVisibility(View.VISIBLE);
            time_minute=30000;
            if(language_deger==1){
                locale = new Locale("tr");  //locale en yaptık. Artık değişkenler values-en paketinden alınacak
                Locale.setDefault(locale);
                config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                txt_rules.setText("0 ile 21 arasinda kalmaya çalisin");
            }
            else{
                locale = new Locale("en");  //locale en yaptık. Artık değişkenler values-en paketinden alınacak
                Locale.setDefault(locale);
                config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                txt_rules.setText("Try to stay between 0 and 21");
            }
            level =1;adet =1;
            coin=0;generate=9;
            int_welevel=21;
            txt_coin.setText(String.valueOf(coin));
            game_level_count.setText(String.valueOf(level));
            str_level = String.valueOf(level);
            str_adet =String.valueOf(adet);
            str_coin =String.valueOf(coin);
            str_generate=String.valueOf(generate);
            str_welevel=String.valueOf(int_welevel);
            sessionManager.createSession(str_level,str_adet,str_coin,str_generate,str_welevel);
            epic_info.setContentView(R.layout.pop_up_how_to_play);
            txt_info_title=epic_info.findViewById(R.id.warning_icon);
            txt_info_center=epic_info.findViewById(R.id.warning_titles);
            btn_info_block=epic_info.findViewById(R.id.img_block_info);
            txt_info_block_1=epic_info.findViewById(R.id.txt_block_info);
            btn_info_absoulute=epic_info.findViewById(R.id.img_block_info2);
            txt_absolute_info2=epic_info.findViewById(R.id.txt_absoulte_info2);
            img_pop_ex=epic_info.findViewById(R.id.pop_ex);
            btn_info_block.setTypeface(custom_font_gameria);
            btn_info_absoulute.setTypeface(custom_font_gameria);
            txt_absolute_info2.setTypeface(custom_font);
            txt_info_title.setTypeface(custom_font);
            txt_info_center.setTypeface(custom_font);
            txt_info_block_1.setTypeface(custom_font);
            img_pop_ex.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(sound_deger == 0){
                        btn_click1.start();
                    }
                    epic_info.dismiss();
                }
            });
            epic_info.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            epic_info.show();
        }
        else{
            HashMap<String,String> user=sessionManager.getUserDetail();
            str_coin =sessionManager.getCoinDetail();
            str_level=user.get(sessionManager.LEVEL);
            str_adet=user.get(sessionManager.ID);
            str_generate=sessionManager.getGenerateDetail();
            str_welevel=sessionManager.getWelevel();
            level = Integer.parseInt(str_level);
            adet = Integer.parseInt(str_adet);
            coin =Integer.parseInt(str_coin);
            generate=Integer.parseInt(str_generate);
            int_welevel=Integer.parseInt(str_welevel);
            txt_rules.setVisibility(View.VISIBLE);
            txt_coin.setText(String.valueOf(coin));
            game_level_count.setText(String.valueOf(level));int i =1;progres_value =0;int sum2=10;
            if(level % 15 == 0){
                progres_value =0;
            }
            else{
                while(i < level % 15){
                    progres_value =progres_value+ sum2;
                    i++;
                }
            }
            progressBar.setProgress(progres_value);
            if (level<=5){
                time_minute=20000;
            }
            else{
                time_minute=(level+1)*2500;
            }
            if(language_deger==1){
                locale = new Locale("tr");
                Locale.setDefault(locale);
                config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                txt_rules.setText("0 ile"+ " " +str_welevel+" "+"arasinda kalmaya çalisin");
            }
            else{
                locale = new Locale("en");
                Locale.setDefault(locale);
                config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                txt_rules.setText("Try to stay between 0 and"+" "+str_welevel);
            }
        }
        //      ...Ads...
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-2217525225495117/7312007485");
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Video Reklam kodları
        MobileAds.initialize(this, APP_ID);
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener((RewardedVideoAdListener) this);
        loadRewardedVideoAd();
        //       Ads finished...
        epic_try=new Dialog(this);
        left_button=findViewById(R.id.left_button);
        right_button=findViewById(R.id.right_button);
        sum = findViewById(R.id.sum);
        left_adet =findViewById(R.id.left_adet);
        right_adet = findViewById(R.id.right_adet);
        x_left=findViewById(R.id.x_left);
        x_right=findViewById(R.id.x);
        //  ....  Game Feature ....
        btn_block=findViewById(R.id.block);
        btn_absoulte=findViewById(R.id.absulute);
        btn_rightarrow=findViewById(R.id.arrow_right);
        btn_leftarrow=findViewById(R.id.arrow_left);
        btn_leftarrow.setVisibility(View.INVISIBLE);
        btn_rightarrow.setVisibility(View.INVISIBLE);
        btn_retry=findViewById(R.id.retry);
        btn_main_menu=findViewById(R.id.btn_home);
        vibrator=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE) ;
        btn_absoulte.getBackground().setAlpha(124);
        btn_block.getBackground().setAlpha(124);
        //  .... Motion -- Action ....
        img_View10 = findViewById(R.id.imageView10);//action_left...
        img_View12 = findViewById(R.id.imageView12); //action_right...
        left_block_motion=findViewById(R.id.left_block_motion); //block action left ...
        right_block_motion=findViewById(R.id.right_block_animation); // block action right ...
        img_View12.setVisibility(View.INVISIBLE);
        img_View10.setVisibility(View.INVISIBLE);
        left_block_motion.setVisibility(View.INVISIBLE);
        right_block_motion.setVisibility(View.INVISIBLE);
        txt_timer=findViewById(R.id.timer);
        coin_txtview3=findViewById(R.id.textView3);
        coin_txtview3.setVisibility(View.INVISIBLE);
        // ...Fonts Setting ...
        btn_block.setTypeface(custom_font_gameria);
        btn_absoulte.setTypeface(custom_font_gameria);
        txt_timer.setTypeface(custom_font_gameria);
        coin_txtview3.setTypeface(custom_font_gameria);
        left_button.setTypeface(custom_font_gameria);
        right_button.setTypeface(custom_font_gameria);
        sum.setTypeface(custom_font_gameria);
        left_adet.setTypeface(custom_font_gameria);
        right_adet.setTypeface(custom_font_gameria);
        x_left.setTypeface(custom_font_gameria);
        x_right.setTypeface(custom_font_gameria);
        left_block_motion.setTypeface(custom_font_gameria);
        right_block_motion.setTypeface(custom_font_gameria);
        img_View10.setTypeface(custom_font_gameria);
        img_View12.setTypeface(custom_font_gameria);
        //  .... end ....
        //  .... First operation ....
        counter1=adet;counter2 =adet;
        bitirme_left=0;bitirme_right=0;bitirme_finish=0;int_secenek=0;
        left_adet.setText(String.valueOf(counter1));
        right_adet.setText(String.valueOf(counter2));
        number1=rnd.nextInt(9-2) + 2;
        number2 = rnd.nextInt(9-2) + 2;
        //  ....  Time  ....
        //Question time is finished....
        if(time_deger==1){
            stopping=0;
            timerStart(time_minute);
        }
        while(number1 == number2 ){
            number1=rnd.nextInt(9-2) + 2;
            number2 = rnd.nextInt(9-2) + 2;
        }
        left_button.setBackgroundResource(R.drawable.positive_background);
        img_View10.setBackgroundResource(R.drawable.positive_background);
        right_button.setBackgroundResource(R.drawable.positive_background);
        img_View12.setBackgroundResource(R.drawable.positive_background);
        /*left_button.setTextColor(Color.parseColor("#157400"));
        right_button.setTextColor(Color.parseColor("#157400"));
        img_View10.setTextColor(Color.parseColor("#157400"));
        img_View12.setTextColor(Color.parseColor("#157400"));*/
        left_button.setText("+"+ number1);
        right_button.setText("+"+ number2);
        sum.setText("0");
        btn_retry.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_UP:{
                        Button view=(Button)v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_DOWN:{
                        Button view=(Button)v;
                        view.getBackground().setColorFilter(0xff4F76C5, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_CANCEL:{
                        Button view=(Button)v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
        btn_main_menu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_UP:{
                        Button view=(Button)v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_DOWN:{
                        Button view=(Button)v;
                        view.getBackground().setColorFilter(0xff4F76C5, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_CANCEL:{
                        Button view=(Button)v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
        btn_leftarrow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_UP:{
                        Button view=(Button)v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_DOWN:{
                        Button view=(Button)v;
                        view.getBackground().setColorFilter(0xff4F76C5, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_CANCEL:{
                        Button view=(Button)v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
        btn_rightarrow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_UP:{
                        Button view=(Button)v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_DOWN:{
                        Button view=(Button)v;
                        view.getBackground().setColorFilter(0xff4F76C5, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_CANCEL:{
                        Button view=(Button)v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
        btn_block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sound_deger == 0){
                    button_click.start();
                }
                if(coin>=40){
                    btn_block.setTextColor(Color.parseColor("#3452a5"));
                    btn_absoulte.setTextColor(Color.parseColor("#ffffff"));
                    int_secenek=2;
                    btn_leftarrow.setVisibility(View.VISIBLE);
                    btn_rightarrow.setVisibility(View.VISIBLE);
                    if(time_deger == 1){
                        stopping=1;
                        times.cancel();
                    }
                    countDownTimer= new CountDownTimer(8000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            count1= new CountDownTimer(500, 1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    btn_leftarrow.setBackgroundResource(R.drawable.ic_baseline_double_arrow_24);
                                    btn_rightarrow.setBackgroundResource(R.drawable.ic_arrow);
                                }
                                @Override
                                public void onFinish() {
                                    btn_leftarrow.setBackgroundResource(R.drawable.ic_arrow);
                                    btn_rightarrow.setBackgroundResource(R.drawable.ic_baseline_double_arrow_24);
                                }
                            }.start();
                            count2= new CountDownTimer(500, 1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    btn_leftarrow.setBackgroundResource(R.drawable.ic_baseline_double_arrow_24);
                                    btn_rightarrow.setBackgroundResource(R.drawable.ic_arrow);
                                }
                                @Override
                                public void onFinish() {
                                    btn_leftarrow.setBackgroundResource(R.drawable.ic_arrow);
                                    btn_rightarrow.setBackgroundResource(R.drawable.ic_baseline_double_arrow_24);
                                }
                            }.start();
                            count3= new CountDownTimer(500, 1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    btn_leftarrow.setBackgroundResource(R.drawable.ic_baseline_double_arrow_24);
                                    btn_rightarrow.setBackgroundResource(R.drawable.ic_arrow);
                                }
                                @Override
                                public void onFinish() {
                                    btn_leftarrow.setBackgroundResource(R.drawable.ic_arrow);
                                    btn_rightarrow.setBackgroundResource(R.drawable.ic_baseline_double_arrow_24);
                                }
                            }.start();
                        }
                        @Override
                        public void onFinish() {
                            btn_rightarrow.setBackgroundResource(R.drawable.ic_arrow);
                            btn_leftarrow.setBackgroundResource(R.drawable.ic_arrow);
                        }
                    }.start();
                }
            }
        });
        btn_absoulte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sound_deger == 0){
                    button_click.start();
                }
                if(coin>=20){
                    btn_absoulte.setTextColor(Color.parseColor("#3452a5"));
                    btn_block.setTextColor(Color.parseColor("#ffffff"));
                    int_secenek=1;
                    btn_leftarrow.setVisibility(View.VISIBLE);
                    btn_rightarrow.setVisibility(View.VISIBLE);
                    if(time_deger == 1){
                        stopping=1;
                        times.cancel();
                    }
                    countDownTimer= new CountDownTimer(8000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            count1= new CountDownTimer(500, 1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    btn_leftarrow.setBackgroundResource(R.drawable.ic_baseline_double_arrow_24);
                                    btn_rightarrow.setBackgroundResource(R.drawable.ic_arrow);
                                }
                                @Override
                                public void onFinish() {
                                    btn_leftarrow.setBackgroundResource(R.drawable.ic_arrow);
                                    btn_rightarrow.setBackgroundResource(R.drawable.ic_baseline_double_arrow_24);
                                }
                            }.start();
                            count2= new CountDownTimer(500, 1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    btn_leftarrow.setBackgroundResource(R.drawable.ic_baseline_double_arrow_24);
                                    btn_rightarrow.setBackgroundResource(R.drawable.ic_arrow);
                                }
                                @Override
                                public void onFinish() {
                                    btn_leftarrow.setBackgroundResource(R.drawable.ic_arrow);
                                    btn_rightarrow.setBackgroundResource(R.drawable.ic_baseline_double_arrow_24);
                                }
                            }.start();
                            count3= new CountDownTimer(500, 1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    btn_leftarrow.setBackgroundResource(R.drawable.ic_baseline_double_arrow_24);
                                    btn_rightarrow.setBackgroundResource(R.drawable.ic_arrow);
                                }
                                @Override
                                public void onFinish() {
                                    btn_leftarrow.setBackgroundResource(R.drawable.ic_arrow);
                                    btn_rightarrow.setBackgroundResource(R.drawable.ic_baseline_double_arrow_24);
                                }
                            }.start();
                        }
                        @Override
                        public void onFinish() {
                            btn_rightarrow.setBackgroundResource(R.drawable.ic_arrow);
                            btn_leftarrow.setBackgroundResource(R.drawable.ic_arrow);
                        }
                    }.start();
                }
            }
        });
        btn_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sound_deger == 0){
                    button_click.start();
                }
                str_level = String.valueOf(level);
                str_adet =String.valueOf(adet);
                str_coin =String.valueOf(coin);
                str_generate=String.valueOf(generate);
                str_welevel=String.valueOf(int_welevel);
                sessionManager.setAlterSession(str_level,str_adet,str_coin,str_generate,str_welevel);
                next_step();
            }
        });
        btn_main_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sound_deger == 0){
                    button_click.start();
                }
                if(time_deger == 1){
                    times.cancel(); //dismis timer...
                }
                str_level = String.valueOf(level);
                str_adet =String.valueOf(adet);
                str_coin =String.valueOf(coin);
                str_generate=String.valueOf(generate);
                str_welevel=String.valueOf(int_welevel);
                sessionManager.setAlterSession(str_level,str_adet,str_coin,str_generate,str_welevel);
                Intent intent=new Intent(MainActivity.this,Menu.class);
                startActivity(intent);
                finish();
            }
        });
        left_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_block.setTextColor(Color.parseColor("#ffffff"));
                btn_absoulte.setTextColor(Color.parseColor("#ffffff"));
                btn_leftarrow.setVisibility(View.INVISIBLE);
                btn_rightarrow.setVisibility(View.INVISIBLE);
                if(int_secenek ==0){
                    if(sound_deger == 0){
                        card_flip.start();
                    }
                    img_View10.setVisibility(View.VISIBLE);
                    left_button.setClickable(false);
                    right_button.setClickable(false);
                    if(number1 <0){
                        if(vibrate_deger ==0){
                            vibrator.vibrate(pulse);
                        }
                        img_View10.setBackgroundResource(R.drawable.negative_background);
                        img_View10.setText(String.valueOf(number1));
                    }
                    else{
                        img_View10.setBackgroundResource(R.drawable.positive_background);
                        img_View10.setText("+"+String.valueOf(number1));
                    }
                    mt.setTransition(R.id.start,R.id.end);
                    mt.transitionToEnd();
                    int_sum=int_sum+number1;
                    counter1--;
                    sum.setText(String.valueOf(int_sum));
                    //count.setText(String.valueOf(game_count));
                    left_adet.setText(String.valueOf(counter1));
                    if(counter1>0){
                        if(int_sum<int_welevel+1 && int_sum>-1){
                            generate_number_left();
                        }
                        else{
                            left_button.setText("-");
                            right_button.setText("-");
                            bitirme_finish=1;
                            bitirme_kontrol();
                        }
                    }
                    else{
                        if(int_sum<=-1 || int_sum>=int_welevel+1){
                            left_button.setText("-");
                            right_button.setText("-");
                            bitirme_finish=1;
                            bitirme_kontrol();
                        }
                        else{
                            left_button.setText("-");
                            bitirme_left = 1;
                            if(counter2>0){
                                right_button.setClickable(true);
                            }
                            bitirme_kontrol();
                        }
                    }
                }
                else if(int_secenek ==1){
                    coin=coin-20;
                    coin_txtview3.setText("-20" );
                    //  ... coin animation ...
                    coin_txtview3.setVisibility(View.VISIBLE);
                    mt.setTransition(R.id.coin_start,R.id.coin_finish);
                    mt.transitionToEnd();
                    cointimerControl();
                    //  ... coin animation end ...
                    number1=number1 * -1;
                    if(number1<0){
                        left_button.setBackgroundResource(R.drawable.negative_background);
                        left_button.setText(String.valueOf(number1));
                    }
                    else{
                        left_button.setBackgroundResource(R.drawable.positive_background);
                        left_button.setText("+"+String.valueOf(number1));
                    }
                    int_secenek=0;
                }
                else if(int_secenek ==2){
                    coin=coin-40;
                    if(sound_deger == 0){
                        card_flip2.start();
                    }
                    left_block_motion.setVisibility(View.VISIBLE);
                    right_button.setClickable(false);
                    left_button.setClickable(false);
                    mt.setTransition(R.id.block_start,R.id.block_finish);
                    mt.transitionToEnd();
                    coin_txtview3.setText("-40" );
                    blocktimerControl();
                    if(number1 <0){
                        left_block_motion.setBackgroundResource(R.drawable.negative_background);
                        btn_block.setBackgroundResource(R.drawable.negative_background);
                        btn_block.setText(String.valueOf(number1));
                        left_block_motion.setText(String.valueOf(number1));
                    }
                    else{
                        left_block_motion.setBackgroundResource(R.drawable.positive_background);
                        btn_block.setBackgroundResource(R.drawable.positive_background);
                        btn_block.setText("+"+String.valueOf(number1));
                        left_block_motion.setText("+"+String.valueOf(number1));
                    }
                    counter1--;
                    left_adet.setText(String.valueOf(counter1));
                    int_secenek =0;
                    if(counter1>0){
                        if(int_sum<int_welevel+1 && int_sum>-1){
                            generate_number_left();
                        }
                        else{
                            left_button.setText("-");
                            right_button.setText("-");
                            bitirme_finish=1;
                            bitirme_kontrol();
                        }
                    }
                    else{
                        if(int_sum>=int_welevel+1 || int_sum<=-1){
                            left_button.setText("-");
                            right_button.setText("-");
                            bitirme_finish=1;
                            bitirme_kontrol();
                        }
                        else{
                            left_button.setText("-");
                            bitirme_left = 1;
                            if(counter2>0){
                                right_button.setClickable(true);
                            }
                            bitirme_kontrol();
                        }
                    }
                }
                if(stopping ==1){
                    stopping=0;
                    timerResume();
                }
            }

        });
        right_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_block.setTextColor(Color.parseColor("#ffffff"));
                btn_absoulte.setTextColor(Color.parseColor("#ffffff"));
                btn_leftarrow.setVisibility(View.INVISIBLE);
                btn_rightarrow.setVisibility(View.INVISIBLE);
                if(int_secenek == 0){
                    if(sound_deger == 0){
                        card_flip.start();
                    }
                    left_button.setClickable(false);
                    right_button.setClickable(false);
                    img_View12.setVisibility(View.VISIBLE);
                    if(number2 <0){
                        if(vibrate_deger ==0){
                            vibrator.vibrate(pulse);
                        }
                        img_View12.setBackgroundResource(R.drawable.negative_background);
                        img_View12.setText(String.valueOf(number2));
                    }
                    else{
                        img_View12.setBackgroundResource(R.drawable.positive_background);
                        img_View12.setText("+"+ String.valueOf(number2));
                    }
                    mt.setTransition(R.id.step3,R.id.step4);
                    mt.transitionToEnd();
                    int_sum=int_sum+number2;
                    counter2--;
                    sum.setText(String.valueOf(int_sum));
                    right_adet.setText(String.valueOf(counter2));
                    if(counter2>0){
                        if(int_sum<int_welevel+1 && int_sum>-1){
                            generate_number_right();
                        }
                        else{
                            left_button.setText("-");
                            right_button.setText("-");
                            bitirme_finish=1;
                            bitirme_kontrol();
                        }
                    }
                    else{
                        if(int_sum>=int_welevel+1 || int_sum<=-1){
                            left_button.setText("-");
                            right_button.setText("-");
                            bitirme_finish=1;
                            bitirme_kontrol();
                        }
                        else{
                            right_button.setText("-");
                            bitirme_right = 1;
                            if(counter1>0){
                                left_button.setClickable(true);
                            }
                            bitirme_kontrol();
                        }
                    }
                }
                else if(int_secenek ==1){
                    coin=coin-20;
                    coin_txtview3.setText("-20" );
                    //  ... coin animation ...
                    coin_txtview3.setVisibility(View.VISIBLE);
                    mt.setTransition(R.id.coin_start,R.id.coin_finish);
                    mt.transitionToEnd();
                    cointimerControl();
                    //  ... coin animation end ...
                    number2=number2 * -1;
                    if(number2<0){
                        right_button.setBackgroundResource(R.drawable.negative_background);
                        right_button.setText(String.valueOf(number2));
                    }
                    else{
                        right_button.setBackgroundResource(R.drawable.positive_background);
                        right_button.setText("+"+String.valueOf(number2));
                    }
                    int_secenek=0;
                }
                else if(int_secenek == 2){
                    coin=coin-40;
                    if(sound_deger == 0){
                        card_flip2.start();
                    }
                    left_button.setClickable(false);
                    right_button.setClickable(false);
                    right_block_motion.setVisibility(View.VISIBLE);
                    mt.setTransition(R.id.block_start_right,R.id.block_finish_right);
                    mt.transitionToEnd();
                    coin_txtview3.setText("-40" );
                    blocktimerControl();
                    if(number2 <0){
                        right_block_motion.setBackgroundResource(R.drawable.negative_background);
                        btn_block.setBackgroundResource(R.drawable.negative_background);
                        btn_block.setText(String.valueOf(number2));
                        right_block_motion.setText(String.valueOf(number2));
                    }
                    else{
                        right_block_motion.setBackgroundResource(R.drawable.positive_background);
                        btn_block.setBackgroundResource(R.drawable.positive_background);
                        btn_block.setText("+"+String.valueOf(number2));
                        right_block_motion.setText("+"+String.valueOf(number2));
                    }
                    counter2--;
                    right_adet.setText(String.valueOf(counter2));
                    int_secenek=0;
                    if(counter2>0){
                        if(int_sum<int_welevel+1 && int_sum>-1){
                            generate_number_right();
                        }
                        else{
                            left_button.setText("-");
                            right_button.setText("-");
                            bitirme_finish=1;
                            bitirme_kontrol();
                        }
                    }
                    else{
                        if(int_sum>=int_welevel+1 || int_sum<=-1){
                            left_button.setText("-");
                            right_button.setText("-");
                            bitirme_finish=1;
                            bitirme_kontrol();
                        }
                        else{
                            right_button.setText("-");
                            bitirme_right = 1;
                            if(counter1>0){
                                left_button.setClickable(true);
                            }
                            bitirme_kontrol();
                        }
                    }
                }
                if(stopping ==1){
                    timerResume();
                    stopping=0;
                }
            }
        });
        btn_leftarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_leftarrow.setVisibility(View.INVISIBLE);
                btn_rightarrow.setVisibility(View.INVISIBLE);
                countDownTimer.cancel();
                btn_block.setTextColor(Color.parseColor("#ffffff"));
                btn_absoulte.setTextColor(Color.parseColor("#ffffff"));
                if(sound_deger == 0){
                    button_click.start();
                }
                if(int_secenek ==2){
                    coin=coin-40;
                    if(sound_deger == 0){
                        card_flip2.start();
                    }
                    left_block_motion.setVisibility(View.VISIBLE);
                    right_button.setClickable(false);
                    left_button.setClickable(false);
                    mt.setTransition(R.id.block_start,R.id.block_finish);
                    mt.transitionToEnd();
                    coin_txtview3.setText("-40" );
                    blocktimerControl();
                    if(number1 <0){
                        left_block_motion.setBackgroundResource(R.drawable.negative_background);
                        btn_block.setBackgroundResource(R.drawable.negative_background);
                        btn_block.setText(String.valueOf(number1));
                        left_block_motion.setText(String.valueOf(number1));
                    }
                    else{
                        left_block_motion.setBackgroundResource(R.drawable.positive_background);
                        btn_block.setBackgroundResource(R.drawable.positive_background);
                        btn_block.setText("+"+String.valueOf(number1));
                        left_block_motion.setText("+"+String.valueOf(number1));
                    }
                    counter1--;
                    left_adet.setText(String.valueOf(counter1));
                    int_secenek=0;
                    if(counter1>0){
                        if(int_sum<int_welevel+1 && int_sum>-1){
                            generate_number_left();
                        }
                        else{
                            left_button.setText("-");
                            right_button.setText("-");
                            bitirme_finish=1;
                            bitirme_kontrol();
                        }
                    }
                    else{
                        if(int_sum>=int_welevel+1 || int_sum<=-1){
                            left_button.setText("-");
                            right_button.setText("-");
                            bitirme_finish=1;
                            bitirme_kontrol();
                        }
                        else{
                            left_button.setText("-");
                            bitirme_left = 1;
                            if(counter2>0){
                                right_button.setClickable(true);
                            }
                            bitirme_kontrol();
                        }
                    }
                }
                else if( int_secenek == 1){
                    coin=coin-20;
                    coin_txtview3.setText("-20" );
                    coin_txtview3.setVisibility(View.VISIBLE);
                    mt.setTransition(R.id.coin_start,R.id.coin_finish);
                    mt.transitionToEnd();
                    number1=number1 * -1;
                    if(number1<0){
                        left_button.setBackgroundResource(R.drawable.negative_background);
                        left_button.setText(String.valueOf(number1));
                    }
                    else{
                        left_button.setBackgroundResource(R.drawable.positive_background);
                        left_button.setText("+"+String.valueOf(number1));
                    }
                    int_secenek=0;
                }
                if(stopping ==1){
                    timerResume();
                    stopping=0;
                }
            }
        });
        btn_rightarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_leftarrow.setVisibility(View.INVISIBLE);
                btn_rightarrow.setVisibility(View.INVISIBLE);
                countDownTimer.cancel();
                btn_block.setTextColor(Color.parseColor("#ffffff"));
                btn_absoulte.setTextColor(Color.parseColor("#ffffff"));
                if(sound_deger == 0){
                    button_click.start();
                }
                if(int_secenek ==2){
                    coin=coin-40;
                    if(sound_deger == 0){
                        card_flip2.start();
                    }
                    left_button.setClickable(false);
                    right_button.setClickable(false);
                    right_block_motion.setVisibility(View.VISIBLE);
                    mt.setTransition(R.id.block_start_right,R.id.block_finish_right);
                    mt.transitionToEnd();
                    coin_txtview3.setText("-40" );
                    blocktimerControl();
                    if(number2 <0){
                        right_block_motion.setBackgroundResource(R.drawable.negative_background);
                        btn_block.setBackgroundResource(R.drawable.negative_background);
                        btn_block.setText(String.valueOf(number2));
                        right_block_motion.setText(String.valueOf(number2));
                    }
                    else{
                        right_block_motion.setBackgroundResource(R.drawable.positive_background);
                        btn_block.setBackgroundResource(R.drawable.positive_background);
                        btn_block.setText("+"+String.valueOf(number2));
                        right_block_motion.setText("+"+String.valueOf(number2));
                    }
                    counter2--;
                    right_adet.setText(String.valueOf(counter2));
                    int_secenek=0;
                    if(counter2>0){
                        if(int_sum<int_welevel+1 && int_sum>-1){
                            generate_number_right();
                        }
                        else{
                            left_button.setText("-");
                            right_button.setText("-");
                            bitirme_finish=1;
                            bitirme_kontrol();
                        }
                    }
                    else{
                        if(int_sum>=int_welevel+1 || int_sum<=-1){
                            left_button.setText("-");
                            right_button.setText("-");
                            bitirme_finish=1;
                            bitirme_kontrol();
                        }
                        else{
                            right_button.setText("-");
                            bitirme_right = 1;
                            if(counter1>0){
                                left_button.setClickable(true);
                            }
                            bitirme_kontrol();
                        }
                    }
                }
                else if(int_secenek == 1){
                    coin=coin-20;
                    coin_txtview3.setText("-20" );
                    coin_txtview3.setVisibility(View.VISIBLE);
                    //  ... coin animation ...
                    mt.setTransition(R.id.coin_start,R.id.coin_finish);
                    mt.transitionToEnd();
                    cointimerControl();
                    //  ... coin animation end ...
                    number2=number2 * -1;
                    if(number2<0){
                        right_button.setBackgroundResource(R.drawable.negative_background);
                        right_button.setText(String.valueOf(number2));
                    }
                    else{
                        right_button.setBackgroundResource(R.drawable.positive_background);
                        right_button.setText("+"+String.valueOf(number2));
                    }
                    int_secenek=0;
                }
                if(stopping ==1){
                    timerResume();
                    stopping=0;
                }
            }
        });
    }
    @SuppressLint("ClickableViewAccessibility")
    private void bitirme_kontrol(){
        btn_leftarrow.setVisibility(View.INVISIBLE);
        btn_rightarrow.setVisibility(View.INVISIBLE);
        coin_txtview3.setVisibility(View.INVISIBLE);
        img_View10.setVisibility(View.INVISIBLE);
        img_View12.setVisibility(View.INVISIBLE);
        coin_txtview3.setVisibility(View.INVISIBLE);
        left_block_motion.setVisibility(View.INVISIBLE);
        right_block_motion.setVisibility(View.INVISIBLE);
        int_secenek=0;
        if((bitirme_right == 1) && (bitirme_left == 1) && (int_sum < (int_welevel + 1)) && (int_sum > -1)){
            int prize1=40;int prize2 =0; int prize3=0;int sum_prize=0;
            coin=coin+prize1;
            level++; adet++;
            if(level % 5 == 0){
                prize2=100;
                generate=generate+3;
                coin=coin+prize2;
            }
            if (level % 15 == 0) {
                prize3=100;
                int_welevel=int_welevel+20;
                coin=coin+prize3;
            }
            if(time_deger == 1){
                stopping=0;
                times.cancel(); //dismis timer...
            }
            sum_prize=prize1+prize2+prize3;  //prize calculate ....
            coin_txtview3.setText("+"+String.valueOf(sum_prize));
            coin_txtview3.setVisibility(View.VISIBLE);
            mt.setTransition(R.id.coin_start,R.id.coin_finish);
            mt.transitionToEnd();
            cointimerControl();
            str_level = String.valueOf(level);
            str_adet =String.valueOf(adet);
            str_coin =String.valueOf(coin);
            str_generate=String.valueOf(generate);
            str_welevel=String.valueOf(int_welevel);
            sessionManager.setAlterSession(str_level,str_adet,str_coin,str_generate,str_welevel);
            epic_try.setContentView(R.layout.pop_up_finish);
            icon= epic_try.findViewById(R.id.warning_icon);
            btn_next= epic_try.findViewById(R.id.button_tmm);
            btn_home= epic_try.findViewById(R.id.btn_home);
            btn_ads=epic_try.findViewById(R.id.reklam);
            try_baslık= epic_try.findViewById(R.id.warning_titles);
            try_message= epic_try.findViewById(R.id.message_textxt);
            try_baslık.setTypeface(custom_font_gameria);
            img_left_cong=epic_try.findViewById(R.id.left_congratulation);
            img_right_cong=epic_try.findViewById(R.id.right_congratulation);
            img_left_cong.setVisibility(View.VISIBLE);img_right_cong.setVisibility(View.VISIBLE);
            try_baslık.setText("LEVEL"+" "+String.valueOf(level-1));
            if(language_deger ==1){
                try_message.setText("BAŞARILI");
            }
            else{
                try_message.setText("DONE");
            }
            icon.setBackgroundResource(R.drawable.trophy);
            btn_home.setBackgroundResource(R.drawable.ic_home_white);
            btn_next.setBackgroundResource(R.drawable.ic_next);
            epic_try.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            epic_try.show();
            epic_try.setCancelable(false);
            if(sound_deger == 0){
                success.start();
            }
            btn_next.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_UP:{
                            Button view=(Button)v;
                            view.getBackground().clearColorFilter();
                            view.invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_DOWN:{
                            Button view=(Button)v;
                            view.getBackground().setColorFilter(0xff374fc5, PorterDuff.Mode.SRC_ATOP);
                            view.invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_CANCEL:{
                            Button view=(Button)v;
                            view.getBackground().clearColorFilter();
                            view.invalidate();
                            break;
                        }
                    }
                    return false;
                }
            });
            btn_home.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_UP:{
                            Button view=(Button)v;
                            view.getBackground().clearColorFilter();
                            view.invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_DOWN:{
                            Button view=(Button)v;
                            view.getBackground().setColorFilter(0xff374fc5, PorterDuff.Mode.SRC_ATOP);
                            view.invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_CANCEL:{
                            Button view=(Button)v;
                            view.getBackground().clearColorFilter();
                            view.invalidate();
                            break;
                        }
                    }
                    return false;
                }
            });
            btn_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(sound_deger == 0){
                        button_click.start();
                    }
                    next_step();
                    epic_try.dismiss();
                }
            });
            btn_home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(sound_deger == 0){
                        button_click.start();
                    }
                    Intent intent=new Intent(MainActivity.this,Menu.class);
                    startActivity(intent);
                    finish();
                }
            });
            btn_ads.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mRewardedVideoAd.isLoaded()) {
                        mRewardedVideoAd.show();
                    }
                }
            });
        }
        if(bitirme_finish == 1) {
            if(vibrate_deger ==0){
                vibrator.vibrate(pulse);
            }
            epic_try.setContentView(R.layout.pop_up_finish);
            btn_next= epic_try.findViewById(R.id.button_tmm);
            btn_home= epic_try.findViewById(R.id.btn_home);
            icon= epic_try.findViewById(R.id.warning_icon);
            try_baslık= epic_try.findViewById(R.id.warning_titles);
            try_message= epic_try.findViewById(R.id.message_textxt);
            try_baslık.setTypeface(custom_font_gameria);
            btn_ads=epic_try.findViewById(R.id.reklam);
            try_baslık.setText("LEVEL"+" "+String.valueOf(level));
            if(language_deger ==1){
                try_message.setText("BAŞARISIZ");
            }
            else{
                try_message.setText("FAILED");
            }
            if(time_deger == 1){
                stopping=0;
                times.cancel(); //dismis timer...
            }
            icon.setBackgroundResource(R.drawable.game_over);
            btn_home.setBackgroundResource(R.drawable.ic_home_white);
            btn_next.setBackgroundResource(R.drawable.ic_retry_white);
            epic_try.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            epic_try.show();
            str_level = String.valueOf(level);
            str_adet =String.valueOf(adet);
            str_coin =String.valueOf(coin);
            str_generate=String.valueOf(generate);
            str_welevel=String.valueOf(int_welevel);
            sessionManager.setAlterSession(str_level,str_adet,str_coin,str_generate,str_welevel);
            epic_try.setCancelable(false);  //
            btn_next.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_UP:{
                            Button view=(Button)v;
                            view.getBackground().clearColorFilter();
                            view.invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_DOWN:{
                            Button view=(Button)v;
                            view.getBackground().setColorFilter(0xff374fc5, PorterDuff.Mode.SRC_ATOP);
                            view.invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_CANCEL:{
                            Button view=(Button)v;
                            view.getBackground().clearColorFilter();
                            view.invalidate();
                            break;
                        }
                    }
                    return false;
                }
            });
            btn_home.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_UP:{
                            Button view=(Button)v;
                            view.getBackground().clearColorFilter();
                            view.invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_DOWN:{
                            Button view=(Button)v;
                            view.getBackground().setColorFilter(0xff374fc5, PorterDuff.Mode.SRC_ATOP);
                            view.invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_CANCEL:{
                            Button view=(Button)v;
                            view.getBackground().clearColorFilter();
                            view.invalidate();
                            break;
                        }
                    }
                    return false;
                }
            });
            btn_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(sound_deger == 0){
                        button_click.start();
                    }
                    next_step();
                    epic_try.dismiss();
                }
            });
            btn_home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(sound_deger == 0){
                        button_click.start();
                    }
                    Intent intent=new Intent(MainActivity.this,Menu.class);
                    startActivity(intent);
                    finish();
                }
            });
            btn_ads.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mRewardedVideoAd.isLoaded()) {
                        mRewardedVideoAd.show();
                    }
                }
            });
        }
    }
    private void next_step(){
        btn_absoulte.getBackground().setAlpha(124);
        btn_block.getBackground().setAlpha(124);
        Random rnd1 =new Random();
        int color_number=rnd1.nextInt(9);
        if(color_number ==0){
            mt.setBackgroundColor(Color.parseColor("#160036"));
            progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#3452a5"), PorterDuff.Mode.SRC_IN);}
        else if(color_number == 1){
            mt.setBackgroundColor(Color.parseColor("#262f3e"));}
        else if(color_number == 2){
            mt.setBackgroundColor(Color.parseColor("#ab8282"));
            progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#3452a5"), PorterDuff.Mode.SRC_IN);}
        else if(color_number == 3){
            mt.setBackgroundColor(Color.parseColor("#7fa7ae"));
            progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#3452a5"), PorterDuff.Mode.SRC_IN);}
        else if(color_number == 4){
            mt.setBackgroundColor(Color.parseColor("#4f1414"));
            progressBar.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN); }
        else if(color_number == 5){
            mt.setBackgroundColor(Color.parseColor("#5d7a55"));
            progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#3452a5"), PorterDuff.Mode.SRC_IN);}
        else if(color_number == 6){
            mt.setBackgroundColor(Color.parseColor("#114444"));
            progressBar.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);}
        else if(color_number == 7){
            mt.setBackgroundColor(Color.parseColor("#0b0a0a"));
            progressBar.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);}
        else if(color_number == 8){
            mt.setBackgroundColor(Color.parseColor("#3f6087"));
            progressBar.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);}
        btn_leftarrow.setVisibility(View.INVISIBLE);
        btn_rightarrow.setVisibility(View.INVISIBLE);
        btn_block.setTextColor(Color.parseColor("#ffffff"));
        btn_block.setBackgroundResource(R.drawable.game_joker_background);
        btn_absoulte.setTextColor(Color.parseColor("#ffffff"));
        coin_txtview3.setVisibility(View.INVISIBLE);
        img_View10.setVisibility(View.INVISIBLE);
        img_View12.setVisibility(View.INVISIBLE);
        coin_txtview3.setVisibility(View.INVISIBLE);
        left_block_motion.setVisibility(View.INVISIBLE);
        right_block_motion.setVisibility(View.INVISIBLE);
        HashMap<String,String> user=sessionManager.getUserDetail();
        str_coin =sessionManager.getCoinDetail();
        str_generate=sessionManager.getGenerateDetail();
        str_welevel=sessionManager.getWelevel();
        str_level=user.get(sessionManager.LEVEL);
        str_adet=user.get(sessionManager.ID);
        time_deger=sessionManager.getTimeDetail();
        level = Integer.parseInt(str_level);
        adet = Integer.parseInt(str_adet);
        coin =Integer.parseInt(str_coin);
        generate=Integer.parseInt(str_generate);
        int_welevel=Integer.parseInt(str_welevel);
        txt_coin.setText(String.valueOf(coin));
        game_level_count.setText(String.valueOf(level));
        counter1=adet;counter2 =adet;
        left_adet.setText(String.valueOf(counter1));
        right_adet.setText(String.valueOf(counter2));
        bitirme_left=0;bitirme_right=0;
        bitirme_finish=0;int_secenek=0;
        sum.setText("0");int i =1;
        progres_value=0;int sum2 = 10;
        if(level % 15 == 0){
            progres_value = 0;
        }
        else{
            while(i < level % 15 ){
                progres_value =progres_value+ sum2;
                i++;
            }
        }
        progressBar.setProgress(progres_value);
        txt_rules.setVisibility(View.VISIBLE);
        if(language_deger ==1){
            txt_rules.setText("0 ile"+ " " +str_welevel+" "+"arasinda kalmaya çalisin");
            btn_block.setText("PAS");
        }
        else{
            txt_rules.setText("Try to stay between 0 and"+" "+str_welevel);
            btn_block.setText("PASS");
        }
        int_sum=0;
        right_button.setClickable(true);
        left_button.setClickable(true);
        number1=rnd.nextInt(9-2) + 2;
        number2 = rnd.nextInt(9-2) + 2;
        if(time_deger == 1){
            times.cancel();
            stopping=0;
            if (level<=5){
                time_minute=20000;
            }
            else{
                time_minute=(level+1)*2500;
            }
           timerStart(time_minute);
        }
        while(number1 == number2 ){
            number1=rnd.nextInt(9-2) + 2;
            number2 = rnd.nextInt(9-2) + 2;
        }
        left_button.setText("+"+String.valueOf(number1));
        right_button.setText("+"+String.valueOf(number2));
        left_button.setBackgroundResource(R.drawable.positive_background);
        right_button.setBackgroundResource(R.drawable.positive_background);
    }
    private void  generate_number_left(){
        left_block_motion.setVisibility(View.INVISIBLE);
        img_View10.setVisibility(View.INVISIBLE);
        img_View12.setVisibility(View.INVISIBLE);
        left_button.setClickable(true);
        if(counter2>0){
            right_button.setClickable(true);
        }
        int number_=number1;
        number1=rnd.nextInt(generate+generate) - generate;
        if(negatif_countleft == 4){
            negatif_countleft=0;
            number1=rnd.nextInt(generate-1) + 1;
        }
        if(positive_countleft == 4){
            positive_countleft=0;
            number1=rnd.nextInt(-1+generate) - generate;
        }
        while(number1 == number_ || number1 == 0 || number2 == number1){
            number1=rnd.nextInt(generate+generate) - generate;
        }
        if(number1<0){
            left_button.setBackgroundResource(R.drawable.negative_background);
            //left_button.setTextColor(Color.parseColor("#bd0000"));
            left_button.setText(String.valueOf(number1));
            negatif_countleft++;
        }
        else{
            left_button.setBackgroundResource(R.drawable.positive_background);
            //left_button.setTextColor(Color.parseColor("#157400"));
            left_button.setText("+"+String.valueOf(number1));
            positive_countleft++;
        }
    }
    private void generate_number_right(){
        img_View10.setVisibility(View.INVISIBLE);
        img_View12.setVisibility(View.INVISIBLE);
        right_button.setClickable(true);
        if(counter1>0){
            left_button.setClickable(true);
        }
        int number_=number2;
        number2 = rnd.nextInt(generate+generate) - generate;
        if(negatif_countright ==4){
            negatif_countright=0;
            number2=rnd.nextInt(generate-1) + 1;
        }
        if(positive_countright ==4){
            positive_countright=0;
            number2=rnd.nextInt(-1+generate) - generate;
        }
        while(number_  ==number2 || number2== 0 || number2 ==number1){
            number2 = rnd.nextInt(generate+generate) - generate;
        }
        if(number2<0){
            right_button.setBackgroundResource(R.drawable.negative_background);
            //right_button.setTextColor(Color.parseColor("#bd0000"));
            right_button.setText(String.valueOf(number2));
            negatif_countright++;
        }
        else{
            right_button.setBackgroundResource(R.drawable.positive_background);
            //right_button.setTextColor(Color.parseColor("#157400"));
            right_button.setText("+"+String.valueOf(number2));
            positive_countright++;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode ==KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        }
        else if(keyCode == KeyEvent.KEYCODE_HOME){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);

        }
        return super.onKeyDown(keyCode, event);
    }
    public void onStop() {
        super.onStop();
        str_level = String.valueOf(level);
        str_adet =String.valueOf(adet);
        str_coin =String.valueOf(coin);
        str_generate=String.valueOf(generate);
        str_welevel=String.valueOf(int_welevel);
        sessionManager.setAlterSession(str_level,str_adet,str_coin,str_generate,str_welevel);
    }
    public void timerStart(int timeLengthMilli) {
        times= new CountDownTimer(timeLengthMilli, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                millileft=(int)millisUntilFinished;
                txt_timer.setText(String.valueOf(millisUntilFinished/1000));
            }
            @Override
            public void onFinish() {
                bitirme_finish=1;
                bitirme_kontrol();
            }
        }.start();
    }
    private void cointimerControl(){
        txt_coin.setText(String.valueOf(coin));
        cointimes= new CountDownTimer(1300, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                coin_txtview3.setVisibility(View.INVISIBLE);
                coin_txtview3.setText("");
            }
        }.start();
    }
    private void blocktimerControl(){
        cointimes= new CountDownTimer(900, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                //  ... coin animation ...
                coin_txtview3.setVisibility(View.VISIBLE);
                mt.setTransition(R.id.coin_start,R.id.coin_finish);
                mt.transitionToEnd();
                cointimerControl();
                //  ... coin animation end ...
            }
        }.start();
    }
    private void timerResume() {
        timerStart(millileft);
    }
    //Video Reklam yükle
    private void loadRewardedVideoAd() {
        if (!mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.loadAd(AD_UNIT_ID, new AdRequest.Builder().build());
        }
    }
    @Override
    public void onRewardedVideoAdLoaded() {
    }
    @Override
    public void onRewardedVideoAdOpened() {
    }
    @Override
    public void onRewardedVideoStarted() {
    }
    @Override
    public void onRewardedVideoAdClosed() {
        if(kazanc==100){
            if(sound_deger == 0){
                success.start();
            }
            coin_txtview3.setText("+100");
            mt.setTransition(R.id.coin_start,R.id.coin_finish);
            mt.transitionToEnd();
            cointimerControl();

        }
        /*Toast.makeText(this, String.valueOf(kazanc),
                Toast.LENGTH_SHORT).show();*/
    }
    @Override
    public void onRewarded(RewardItem rewardItem) {
        kazanc=rewardItem.getAmount();
        coin=coin+kazanc;
        str_coin =String.valueOf(coin);
        sessionManager.setAlterSession(str_level,str_adet,str_coin,str_generate,str_welevel);
    }
    @Override
    public void onRewardedVideoAdLeftApplication() {
    }
    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
    }
    @Override
    public void onRewardedVideoCompleted() {
    }
}