package org.wfs.cogniquiztherapy.view

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore.Images.Media
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.bumptech.glide.Glide
import org.wfs.cogniquiztherapy.R
import org.wfs.cogniquiztherapy.view_model.utils.FuncoesUtil
import java.io.IOException
import java.io.InputStream

class InitialActivity : AppCompatActivity() {
    private val PHOTO_PICKER_CODE = 1
    private lateinit var pictureUser: AppCompatImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)

        pictureUser = findViewById(R.id.image_user)
        var etName = findViewById<EditText>(R.id.et_username)

        //todo: tentar corrigir funcao para clicar enter e fechar teclado
        FuncoesUtil().setupHideKeyboardOnEnterPress(etName)
        val btnEditPicture = findViewById<AppCompatButton>(R.id.btn_picture_profile)
        btnEditPicture.setOnClickListener(View.OnClickListener {
            var intent = Intent(Intent.ACTION_PICK, Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(
                Intent.createChooser(intent, "Escolha sua imagem"),
                PHOTO_PICKER_CODE
            )
        })


        val btnPlay = findViewById<AppCompatButton>(R.id.btn_play)
        btnPlay.setOnClickListener(View.OnClickListener {
            FuncoesUtil().proximaTela(this, WelcomeActivity::class.java)
        })

        val btnQuit = findViewById<AppCompatButton>(R.id.btn_quit)
        btnQuit.setOnClickListener(View.OnClickListener {
            FuncoesUtil().sairDoApp(this)
        })
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, dados: Intent?) {
        super.onActivityResult(requestCode, resultCode, dados)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PHOTO_PICKER_CODE) {
                if (dados != null) {
                    dados?.data?.let { selectedImageUri ->
                        Glide.with(this)
                            .asBitmap()
                            .load(selectedImageUri)
                            .centerCrop()
                            .into(pictureUser)

                        val bitmap: Bitmap? = getBitmapFromUri(selectedImageUri)

                        // Verificar se o bitmap não é nulo
                        bitmap?.let {
                            // Exibir a imagem na ImageView
                            pictureUser.setImageBitmap(bitmap)

                            // Aplicar clip de máscara para manter o formato original como um círculo
                            val roundedBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(resources, bitmap)
                            roundedBitmapDrawable.isCircular = true
                            pictureUser.setImageDrawable(roundedBitmapDrawable)

                        }
                    }
                }
            }
        }
    }

    private fun getBitmapFromUri(uri: Uri): Bitmap? {
        return try {
            val inputStream: InputStream? = contentResolver.openInputStream(uri)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}