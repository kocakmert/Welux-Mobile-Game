package com.welux.myapplication;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
public class SessionManager {
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE=0;
    private static final String PREF_NAME="LOGIN";
    private static final String LOGIN="IS_LOGIN";
    public static final String LEVEL="LEVEL";
    public static final String ID="ID";
    private static final String COIN="COIN";
    private static final String GENERATE ="GENERATE";
    private static final String WELEVEL ="WELEVEL";
    private static final String SOUND ="SOUND";
    private static final String VIBRATE ="VIBRATE";
    private static final String LANGUAGE="LANGUAGE";
    private static final String TIME="TIME";
    private int deger =0;
    public SessionManager(Context context){
        this.context=context;
        sharedPreferences=context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor=sharedPreferences.edit();
    }
    public void createSession(String level,String count,String coin,String  generate,String welevel){
        deger=1;
        editor.putBoolean(LOGIN,true);
        editor.putString(LEVEL,level);
        editor.putString(ID,count);
        editor.putString(COIN,coin);
        editor.putString(GENERATE,generate);
        editor.putString(WELEVEL,welevel);
        editor.apply();
    }
    public void settingSession(int sound_state){
        editor.putInt(SOUND,sound_state);
        editor.apply();
    }
    public void settingVibrationSession(int vibrate_state){
        editor.putInt(VIBRATE,vibrate_state);
        editor.apply();
    }
    public void settingLanguageSession(int language_deger){
        editor.putInt(LANGUAGE,language_deger);
        editor.apply();
    }
    public void settingTimeSession(int time_deger){
        editor.putInt(TIME,time_deger);
        editor.apply();
    }
    public  boolean isLoggin(){
        return sharedPreferences.getBoolean(LOGIN,false);
    }
    public  int checkLogin(){
        if(this.isLoggin()){
            deger=1;
        }
        else{
            deger=0;
        }
        return  deger;
    }
    public HashMap<String,String> getUserDetail(){
        HashMap<String,String> user=new HashMap<>();
        user.put(LEVEL,sharedPreferences.getString(LEVEL,null));
        user.put(ID,sharedPreferences.getString(ID,null));
        return  user;
    }
    public  String getGenerateDetail(){
        String generate =sharedPreferences.getString(GENERATE,null);
        return generate;
    }
    public String getWelevel(){
        String welevel =sharedPreferences.getString(WELEVEL,null);
        return welevel;
    }
    public String getCoinDetail(){
        String coin =sharedPreferences.getString(COIN,null);
        return  coin;
    }
    public int getSoundDetail(){
        int sounds =sharedPreferences.getInt(SOUND,0);
        return sounds;
    }
    public int getVibrateDetail(){
        int vibrate =sharedPreferences.getInt(VIBRATE,0);
        return vibrate;
    }
    public int getLanguageDetail(){
        int language =sharedPreferences.getInt(LANGUAGE,0);
        return language;
    }
    public int getTimeDetail(){
        int time =sharedPreferences.getInt(TIME,1);
        return time;
    }
    public void setAlterSession(String str_level,String str_count,String str_coin,String str_generate,String str_welevel){
        editor.putString(LEVEL,str_level);
        editor.putString(ID,str_count);
        editor.putString(COIN,str_coin);
        editor.putString(GENERATE,str_generate);
        editor.putString(WELEVEL,str_welevel);
        editor.apply();
    }
}
