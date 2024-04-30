package org.wfs.cogniquiztherapy.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import org.wfs.cogniquiztherapy.R
import org.wfs.cogniquiztherapy.view_model.utils.FuncoesUtil

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val btnStart = findViewById<AppCompatButton>(R.id.btn_start)
        btnStart.setOnClickListener(View.OnClickListener {
            FuncoesUtil().proximaTela(this, MainActivity::class.java)
        })
    }

    override fun onResume() {
        super.onResume()
    }
}