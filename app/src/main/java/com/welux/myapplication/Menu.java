package com.welux.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
public class Menu extends AppCompatActivity {
    private Button btn_play,btn_setting;
    private Dialog epic_setting;
    private TextView game1,game2,sound_environment,game_mode,language,other_games;
    private MediaPlayer btn_click,background;
    private ImageView btn_close_pop_up;
    private SessionManager sessionManager;
    private MotionLayout mt;
    private CheckBox chx_sound,chx_vibrate,chx_turkish,chx_english,chx_time;
    private int sound_deger,language_deger,lenght;
    private Locale locale;
    private Configuration config;
    private Vibrator vibrator;
    private Typeface custom_font;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locale= Locale.getDefault();
        Locale.setDefault(locale);
        config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        setContentView(R.layout.activity_menu);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        vibrator=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE) ;
        sessionManager=new SessionManager(this);
        sound_deger=sessionManager.getSoundDetail();
        language_deger=sessionManager.getLanguageDetail();
        mt = (MotionLayout) findViewById(R.id.mmLayout);
        mt.transitionToStart();
        btn_play=findViewById(R.id.btn_play);
        btn_setting=(Button)findViewById(R.id.setting);
        epic_setting=new Dialog(this);
        custom_font = Typeface.createFromAsset(getAssets(), "fonts/GoodUnicornRegular-Rxev.ttf");
        if(String.valueOf(Locale.getDefault()).equals("tr_TR")){
            if(language_deger==2){
                sessionManager.settingLanguageSession(2);
                Locale locale = new Locale("en");  //locale en yaptık. Artık değişkenler values-en paketinden alınacak
                Locale.setDefault(locale);
                config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
            }
            else{
                language_deger=1;
                sessionManager.settingLanguageSession(1);
            }
        }
        else{
            language_deger=2;
            sessionManager.settingLanguageSession(2);
            Locale locale = new Locale("");
            Locale.setDefault(locale);
            config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        }
        //Toast.makeText(getApplicationContext(), String.valueOf(language_deger) , Toast.LENGTH_SHORT).show();
        if (sound_deger == 0) {
            btn_click =MediaPlayer.create(this,R.raw.button_click3);
            background = MediaPlayer.create(this,R.raw.background);
            background.start();
            background.setLooping(true);
        }
        btn_play.setOnTouchListener(new View.OnTouchListener() {
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
        btn_setting.setOnTouchListener(new View.OnTouchListener() {
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
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sound_deger == 0) {
                    btn_click.start();
                    background.stop();

                }
                Intent intent=new Intent(Menu.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sound_deger == 0) {
                    btn_click.start();
                }
                epic_setting.setContentView(R.layout.settings);
                chx_sound=epic_setting.findViewById(R.id.sound);
                chx_vibrate=epic_setting.findViewById(R.id.vibration);
                btn_close_pop_up=epic_setting.findViewById(R.id.pop_ex);
                chx_turkish=epic_setting.findViewById(R.id.turkish);
                chx_english=epic_setting.findViewById(R.id.english);
                chx_time=epic_setting.findViewById(R.id.time);
                game1=epic_setting.findViewById(R.id.flags_and_capitals);
                game2=epic_setting.findViewById(R.id.choose_and_conquer);
                sound_environment=epic_setting.findViewById(R.id.sound_environment);
                game_mode=epic_setting.findViewById(R.id.game_mode);
                language=epic_setting.findViewById(R.id.language);
                other_games=epic_setting.findViewById(R.id.other_games);
                chx_sound.setTypeface(custom_font);
                chx_vibrate.setTypeface(custom_font);chx_turkish.setTypeface(custom_font);
                chx_english.setTypeface(custom_font);chx_time.setTypeface(custom_font);
                game1.setTypeface(custom_font);game2.setTypeface(custom_font);
                sound_environment.setTypeface(custom_font);game_mode.setTypeface(custom_font);
                language.setTypeface(custom_font);other_games.setTypeface(custom_font);
                int vibrate_deger=sessionManager.getVibrateDetail();
                int time_deger=sessionManager.getTimeDetail();
                if(sound_deger ==0){
                    chx_sound.setChecked(true);
                }
                else {
                    chx_sound.setChecked(false);
                }
                if(vibrate_deger == 0){
                    chx_vibrate.setChecked(true);
                }
                else{
                    chx_vibrate.setChecked(false);
                }
                if(language_deger==1){
                    chx_turkish.setChecked(true);
                }
                else{
                    chx_english.setChecked(true);
                }
                if(time_deger == 1){
                    chx_time.setChecked(true);
                }
                else{
                    chx_time.setChecked(false);
                }
                chx_sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(chx_sound.isChecked())
                        { sessionManager.settingSession(0);
                            sound_deger=sessionManager.getSoundDetail();
                            sound_load();}
                        else{background.stop();sessionManager.settingSession(1);
                            sound_deger=sessionManager.getSoundDetail();}
                    }
                });
                chx_vibrate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(chx_vibrate.isChecked())
                        {
                            if (sound_deger == 0) {
                                btn_click.start();
                            }
                            sessionManager.settingVibrationSession(0);vibrator.vibrate(100);}
                        else{
                            if (sound_deger == 0) {
                                btn_click.start();
                            }
                            sessionManager.settingVibrationSession(1);
                        }
                    }
                });
                chx_time.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(chx_time.isChecked())
                        {
                            if (sound_deger == 0) {
                                btn_click.start();
                            }
                            sessionManager.settingTimeSession(1);}
                        else{
                            if (sound_deger == 0) {
                                btn_click.start();
                            }
                            sessionManager.settingTimeSession(0);
                        }
                    }
                });
                chx_turkish.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(chx_turkish.isChecked()){
                            if (sound_deger == 0) {
                                btn_click.start();
                            }
                            sessionManager.settingLanguageSession(1);
                            language_deger=1;
                            chx_english.setChecked(false);
                            Locale locale = new Locale("tr");  //locale en yaptık. Artık değişkenler values-en paketinden alınacak
                            Locale.setDefault(locale);
                            config = new Configuration();
                            config.locale = locale;
                            getBaseContext().getResources().updateConfiguration(config,
                                    getBaseContext().getResources().getDisplayMetrics());
                            epic_setting.dismiss();
                        }
                        else{
                            if (sound_deger == 0) {
                                btn_click.start();
                            }
                            sessionManager.settingLanguageSession(2);
                            chx_turkish.setChecked(false);
                            language_deger=2;
                            Locale locale = new Locale("en");  //locale en yaptık. Artık değişkenler values-en paketinden alınacak
                            Locale.setDefault(locale);
                            config = new Configuration();
                            config.locale = locale;
                            getBaseContext().getResources().updateConfiguration(config,
                                    getBaseContext().getResources().getDisplayMetrics());
                            epic_setting.dismiss();
                        }
                    }
                });
                chx_english.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(chx_english.isChecked()){
                            if (sound_deger == 0) {
                                btn_click.start();
                            }
                            sessionManager.settingLanguageSession(2);
                            chx_turkish.setChecked(false);
                            language_deger=2;
                            Locale locale = new Locale("en");  //locale en yaptık. Artık değişkenler values-en paketinden alınacak
                            Locale.setDefault(locale);
                            config = new Configuration();
                            config.locale = locale;
                            getBaseContext().getResources().updateConfiguration(config,
                                    getBaseContext().getResources().getDisplayMetrics());
                            epic_setting.dismiss();
                        }
                        else{
                            if (sound_deger == 0) {
                                btn_click.start();
                            }
                            sessionManager.settingLanguageSession(1);
                            language_deger=1;
                            chx_english.setChecked(false);
                            Locale locale = new Locale("tr");  //locale en yaptık. Artık değişkenler values-en paketinden alınacak
                            Locale.setDefault(locale);
                            config = new Configuration();
                            config.locale = locale;
                            getBaseContext().getResources().updateConfiguration(config,
                                    getBaseContext().getResources().getDisplayMetrics());
                            epic_setting.dismiss();
                        }
                    }
                });
                btn_close_pop_up.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (sound_deger == 0) {
                            btn_click.start();
                        }
                        epic_setting.dismiss();
                    }
                });
                game1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (sound_deger == 0) {
                            btn_click.start();
                        }
                        final Uri LINK= Uri.parse("https://play.google.com/store/apps/details?id=com.gameflag.gameflag");
                        Intent intent =new Intent(Intent.ACTION_VIEW,LINK);
                        startActivity(intent);
                    }
                });
                game2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (sound_deger == 0) {
                            btn_click.start();
                        }
                        final Uri LINK= Uri.parse("https://play.google.com/store/apps/details?id=com.wars.myapplication");
                        Intent intent =new Intent(Intent.ACTION_VIEW,LINK);
                        startActivity(intent);
                    }
                });
                epic_setting.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                epic_setting.show();
                epic_setting.setCancelable(false);
            }
        });

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode ==KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);  finish();
            startActivity(intent);
            System.exit(0);
        }
        else if(keyCode == KeyEvent.KEYCODE_HOME){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);finish();
            startActivity(intent);
            System.exit(0);
        }
        return super.onKeyDown(keyCode, event);
    }
    private void sound_load(){
        btn_click =MediaPlayer.create(this,R.raw.button_click3);
        background =MediaPlayer.create(this,R.raw.background);
        background.start();
    }
    @Override
    protected void onPause() {
        super.onPause();
        if(sound_deger==0){
            background.pause();
            lenght=background.getCurrentPosition();
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        if(sound_deger==0){
            background.seekTo(lenght);
            background.start();
        }
    }
}