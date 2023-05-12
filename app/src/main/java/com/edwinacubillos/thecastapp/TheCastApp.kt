package com.edwinacubillos.thecastapp

import android.app.Application
import com.edwinacubillos.thecastapp.di.initDI

class TheCastApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

}
