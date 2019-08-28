package ru.vsu.alexey.surfandroidschool.SharedPreferencesUtil;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil extends Activity {

    public static final String TOKEN = "Token";
    public static final String ID = "Id";
    public static final String USERNAME = "Username";
    public static final String FIRSTNAME= "Firstname";
    public static final String LASTNAME= "Lastname";
    public static final String USER_DESCRIPTION = "UserDescription";

    private static SharedPreferences profileSettings;
    private SharedPreferencesUtil(){

    }
    public static void init(Context context) {
        if(profileSettings == null)
            profileSettings = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
    }

    public static void putString(String key, String value){
        SharedPreferences.Editor editor = profileSettings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(String key){
        String value = "";
        if(profileSettings.contains(key)) {
            value = profileSettings.getString(key, "");
            //nicknameText.setText(mSettings.getString(APP_PREFERENCES_NAME, ""));
        }

        return value;
    }

    public static  void putInt(String key, int value){
        SharedPreferences.Editor editor = profileSettings.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getInt(String key){
        int value = 0;
        if(profileSettings.contains(key)) {
            value = profileSettings.getInt(key,0);

        }
        return value;

    }



}
