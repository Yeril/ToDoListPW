package com.example.todolist;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    SharedPreferences sharedPref;

    public SharedPref(Context context) {
        sharedPref = context.getSharedPreferences("filename", Context.MODE_PRIVATE);
    }

    public void setNightModeState(Boolean state){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("NightMode", state);
        editor.commit();
    }

    public Boolean loadNightModeState (){
        Boolean state = sharedPref.getBoolean("NightMode", false);
        return state;
    }

}
