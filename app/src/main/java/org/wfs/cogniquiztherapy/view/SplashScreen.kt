package org.wfs.cogniquiztherapy.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.wfs.cogniquiztherapy.R
import org.wfs.cogniquiztherapy.view_model.utils.FuncoesUtil
import java.util.Timer
import java.util.TimerTask

class SplashScreen : AppCompatActivity() {
    private val timer = Timer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen_)
        timer.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    val intent = Intent(this@SplashScreen, InitialActivity::class.java)
                    FuncoesUtil().proximaTela(this@SplashScreen, intent)
                    finish()
                }
            }
        }, 3000)
    }
}