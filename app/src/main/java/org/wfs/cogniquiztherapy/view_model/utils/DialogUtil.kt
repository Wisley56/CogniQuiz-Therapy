package org.wfs.cogniquiztherapy.view_model.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import org.wfs.cogniquiztherapy.view.WelcomeActivity

class DialogUtil  {
    companion object {
        fun simpleDialog(context: Context, title: String, msg: String) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
                .setMessage(msg)
                .setNeutralButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
            val dialog = builder.create()
            dialog.show()
        }

        fun congratulationsDialog(activity: Activity) {
            val builder = AlertDialog.Builder(activity)
            builder.setTitle("Parabéns")
                .setMessage("Você concluiu nosso quiz, esperamos que tenha conseguido refletir e alcançado um auto conhecimento melhor para lidar com as situações desafiadoras da vida.")
                .setNeutralButton("Até breve") {dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton("Ir tela de respostas") {dialog, _ ->
                    val intent = Intent(activity, WelcomeActivity::class.java)
                    FuncoesUtil().proximaTela(activity, intent)
                }
        }

    }
}