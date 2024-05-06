package org.wfs.cogniquiztherapy.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import org.wfs.cogniquiztherapy.R
import org.wfs.cogniquiztherapy.model.User
import org.wfs.cogniquiztherapy.view_model.utils.FuncoesUtil

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val btnStart = findViewById<AppCompatButton>(R.id.btn_start)
        btnStart.setOnClickListener(View.OnClickListener {
            val user = intent.getParcelableExtra<User>("user")
            intent = Intent(this, MainActivity::class.java)

            if(user != null) {
                intent.putExtra("user", user)
            }
            FuncoesUtil().proximaTela(this, intent)
        })
    }

    override fun onResume() {
        super.onResume()
    }
}