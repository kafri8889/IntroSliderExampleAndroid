package com.eunidev.belajarbuatintroslider

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private var sharedPreferences: SharedPreferences
    private var sharedPreferencesEditor: SharedPreferences.Editor
    private val FILE_NAME = "firstLaunch"
    private val BOOLEAN_KEY = "isFirstLaunch"

    init {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        sharedPreferencesEditor = sharedPreferences.edit()
    }

    fun setFirstLaunchApp(first: Boolean) {
        sharedPreferencesEditor.putBoolean(BOOLEAN_KEY, first)
        sharedPreferencesEditor.commit()
    }

    fun isFirstLaunchApp(): Boolean = sharedPreferences.getBoolean(BOOLEAN_KEY, true)

}