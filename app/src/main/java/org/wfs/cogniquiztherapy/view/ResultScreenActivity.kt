package org.wfs.cogniquiztherapy.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import org.wfs.cogniquiztherapy.R
import org.wfs.cogniquiztherapy.model.User
import org.wfs.cogniquiztherapy.view_model.utils.FuncoesUtil

class ResultScreenActivity : AppCompatActivity() {
    private lateinit var imageUser: AppCompatImageView
    private lateinit var nameUser: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_screen)

        val user = intent.getParcelableExtra<User>("user")
        val stars = intent.getFloatExtra("stars", 2.0f)
        val tvMsg = findViewById<TextView>(R.id.msg_congratulations)

        imageUser = findViewById(R.id.image_user)
        nameUser = findViewById(R.id.tv_username)

        if (user != null) {
            if(user.image != null) {
                Glide.with(this)
                    .load(user.image)
                    .centerCrop()
                    .transform(CircleCrop())
                    .into(imageUser)
            }
            nameUser.text = user.name
            Log.d("User", user.name + " | " + user.image)
        }

        if(stars <= 2.0) {
            tvMsg.text = getString(R.string.msg_2_stars)
        }else if(stars <= 3.5) {
            tvMsg.text = getString(R.string.msg_3_5_stars)
        }else {
            tvMsg.text = getString(R.string.msg_5_stars)
        }

        val btnPlay = findViewById<AppCompatButton>(R.id.btn_play)
        val btnQuit = findViewById<AppCompatButton>(R.id.btn_quit)

        btnPlay.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, InitialActivity::class.java)
            FuncoesUtil().proximaTela(this, intent)
        })

        btnQuit.setOnClickListener(View.OnClickListener {
            FuncoesUtil().sairDoApp(this)
        })
    }
}