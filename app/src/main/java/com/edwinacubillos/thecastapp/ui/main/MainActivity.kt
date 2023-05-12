package com.edwinacubillos.thecastapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.edwinacubillos.thecastapp.R
import com.edwinacubillos.thecastapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)
        setTheme(R.style.Theme_TheCastApp)

        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
    }
}
