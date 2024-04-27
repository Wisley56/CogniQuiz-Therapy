package org.wfs.cogniquiztherapy.view_model.utils

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK

class FuncoesUtil {

    fun proximaTela(context: Context, telaDestino: Class<*>) {
        val intent = Intent(context, telaDestino)
        intent.flags = FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }
}
