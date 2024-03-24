package com.rui.aoppro

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

open class BaseActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }


    override fun onResume() {
        super.onResume()
    }


    override fun onPause() {
        super.onPause()
    }

}