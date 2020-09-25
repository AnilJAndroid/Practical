package com.example.mypractical

import android.content.Context
import androidx.preference.PreferenceManager

class MyPrefrence(ctx: Context){

    private val sharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(ctx)
    }

    var data: String?
        get() = sharedPreferences.getString("data", null)
        set(value) {
            sharedPreferences.edit().putString("data", value).apply()
        }
}
