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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.bumptech.glide.Glide
import org.wfs.cogniquiztherapy.R
import org.wfs.cogniquiztherapy.model.User
import org.wfs.cogniquiztherapy.view_model.utils.FuncoesUtil
import java.io.IOException
import java.io.InputStream

class InitialActivity : AppCompatActivity() {
    private val PHOTO_PICKER_CODE = 1
    private lateinit var pictureUser: AppCompatImageView
    private var selectedImgUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)

        pictureUser = findViewById(R.id.image_user)
        var etName = findViewById<EditText>(R.id.et_username)

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
            val nameUser = etName.text.toString()
            if(nameUser.isNotEmpty()) {
                var user = User.createEmptyUser()

                if(selectedImgUri != null) {
                    user = User.createUser(nameUser, selectedImgUri.toString(), emptyList(), emptyList())
                }
                else {
                    user = User.createUser(nameUser, "", emptyList(), emptyList())
                }
                val intent = Intent(this, WelcomeActivity::class.java)
                intent.putExtra("user", user)

                FuncoesUtil().proximaTela(this, intent)
            }else {
                Toast.makeText(this, "Por favor insira seu nome para continuar..", Toast.LENGTH_SHORT).show()
            }
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
                    selectedImgUri = dados.data
                    renderizarImg(dados)
                }
            }
        }
    }

    private fun renderizarImg(dados: Intent?) {
        dados?.data?.let { selectedImageUri ->
            Glide.with(this)
                .asBitmap()
                .load(selectedImageUri)
                .centerCrop()
                .into(pictureUser)

            val bitmap: Bitmap? = getBitmapFromUri(selectedImageUri)

            manterImgComoCirculo(bitmap)
        }
    }

    private fun manterImgComoCirculo(bitmap: Bitmap?) {
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