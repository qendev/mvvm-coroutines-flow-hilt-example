package com.example.mvvm_coroutines_flow_hilt_example.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


//Set the application as HiltAndroidApp.
//Also, don’t forget to add the application class and internet permission to the AndroidManifest.xml file.
@HiltAndroidApp
class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
    }
}