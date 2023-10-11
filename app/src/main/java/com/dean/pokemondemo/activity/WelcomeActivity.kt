package com.dean.pokemondemo.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Copyright (C)
 *
 * @author dean
 * @email dean0105@126.com
 * Date 2023/10/11
 * Description
 */
class WelcomeActivity : AppCompatActivity() {

    private var keep = AtomicBoolean(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splash = installSplashScreen()
        //keep or not
        splash.setKeepOnScreenCondition {
            keep.get()
        }
        splash.setOnExitAnimationListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        //2s exit
        Handler(Looper.getMainLooper()).postDelayed({
            keep.compareAndSet(true, false)
        }, 2000)
    }


}