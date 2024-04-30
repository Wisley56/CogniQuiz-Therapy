package org.wfs.cogniquiztherapy.view_model.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Process
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

class FuncoesUtil {

    fun proximaTela(context: Context, telaDestino: Class<*>) {
        val intent = Intent(context, telaDestino)
        intent.flags = FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    fun fechaTela(activity: Activity) {
        activity.finish()
    }

    fun sairDoApp(activity: Activity) {
        activity.finishAffinity() // Fecha todas as atividades da pilha de atividades
        Process.killProcess(Process.myPid()) // Finaliza o processo do aplicativo
    }

    fun setupHideKeyboardOnEnterPress(editText: EditText) {
        editText.setOnEditorActionListener { _, KeyCode, event ->
            if (event.action == EditorInfo.IME_ACTION_DONE || KeyCode == EditorInfo.IME_ACTION_NEXT) {
                val inputMethodManager = editText.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(editText.windowToken, 0)
                true // Indica que o evento foi consumido
            } else {
                false // Indica que o evento não foi consumido
            }
        }
    }


}
