package org.wfs.cogniquiztherapy.view

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isInvisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import org.wfs.cogniquiztherapy.R
import org.wfs.cogniquiztherapy.model.User
import org.wfs.cogniquiztherapy.view_model.utils.FuncoesUtil
import org.wfs.cogniquiztherapy.view_model.utils.QuizManager

class MainActivity : AppCompatActivity() {
    private lateinit var easyQuizManager: QuizManager
    private lateinit var middleQuizManager: QuizManager

    private lateinit var imageUser: AppCompatImageView
    private lateinit var nameUser: TextView

    private var contQuestionsEasy = 1
    private var contQuestionsMiddle = 1
    private var hitsSequence = 0
    private lateinit var questionTv: TextView
    private lateinit var actualQuestion: String
    private lateinit var correctAnswer: String
    private var tipActualQuestion = ""
    private lateinit var ratingBar: RatingBar

    private lateinit var btnSad: AppCompatButton
    private lateinit var btnAngry: AppCompatButton
    private lateinit var btnNeutral: AppCompatButton
    private lateinit var btnNormal: AppCompatButton
    private lateinit var btnHappy: AppCompatButton
    private lateinit var btnQuestion: AppCompatButton
    private lateinit var btnA: AppCompatButton
    private lateinit var btnB: AppCompatButton
    private lateinit var btnC: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageUser = findViewById(R.id.image_user)
        nameUser = findViewById(R.id.tv_name)

        questionTv = findViewById(R.id.tv_question)
        ratingBar = findViewById(R.id.stars)

        btnSad = findViewById(R.id.btn_sad)
        btnNeutral = findViewById(R.id.btn_neutral)
        btnNormal = findViewById(R.id.btn_normal)
        btnAngry = findViewById(R.id.btn_angry)
        btnHappy = findViewById(R.id.btn_happy)
        btnQuestion = findViewById(R.id.btn_question)
        btnA = findViewById(R.id.btn_a)
        btnB = findViewById(R.id.btn_b)
        btnC = findViewById(R.id.btn_c)

        val user = intent.getParcelableExtra<User>("user")
        if (user != null) {
            easyQuizManager = QuizManager(this, "easyquestions")
            middleQuizManager = QuizManager(this, "middlequestions")
            if(user.image != null) {
                Glide.with(this)
                    .load(user.image)
                    .centerCrop()
                    .transform(CircleCrop())
                    .into(imageUser)
            }
            else if(user.image == "") {
                val drawable = getDrawable(R.drawable.img_user_default)
                imageUser.setImageDrawable(drawable)
            }
            nameUser.text = user.name

            user.questions = mutableListOf()
            user.answers = mutableListOf()

            displayRandomQuestion(easyQuizManager)

            dicasTCCDialog(this, getString(R.string.fase1_title), getString(R.string.fase1txt))

            btnSad.setOnClickListener(View.OnClickListener {
               configuraAcaoBotao()
                FuncoesUtil().updateRatingBar(ratingBar, hitsSequence)
            })

            btnNormal.setOnClickListener(View.OnClickListener {
                configuraAcaoBotao()
                FuncoesUtil().updateRatingBar(ratingBar, hitsSequence)
            })

            btnAngry.setOnClickListener(View.OnClickListener {
                configuraAcaoBotao()
                FuncoesUtil().updateRatingBar(ratingBar, hitsSequence)
            })

            btnNeutral.setOnClickListener(View.OnClickListener {
                configuraAcaoBotao()
                FuncoesUtil().updateRatingBar(ratingBar, hitsSequence)
            })

            btnHappy.setOnClickListener(View.OnClickListener {
                configuraAcaoBotao()
                FuncoesUtil().updateRatingBar(ratingBar, hitsSequence)
            })

            btnQuestion.setOnClickListener(View.OnClickListener {
                configuraAcaoBotao()
                FuncoesUtil().updateRatingBar(ratingBar, hitsSequence)
            })

            btnA.setOnClickListener(View.OnClickListener {
                if(contQuestionsMiddle == 4 || ratingBar.rating == 5.0f) {
                    FuncoesUtil().updateRatingBar(ratingBar, hitsSequence)
                    congratulationsDialog(this)
                }else if(btnA.text == correctAnswer) {
                    btnA.setBackgroundResource(R.drawable.container_button_answer_correct)

                    Handler().postDelayed({
                        btnA.setBackgroundResource(R.drawable.container_button)
                    }, 500)

                    hitsSequence++
                    FuncoesUtil().updateRatingBar(ratingBar, hitsSequence)
                }else {
                    dicasTCCDialog(this, "Dicas", tipActualQuestion)
                    hitsSequence = 0
                }
                contQuestionsMiddle++
                displayRandomQuestion(middleQuizManager)
            })

            btnB.setOnClickListener(View.OnClickListener {
                if(contQuestionsMiddle == 4 || ratingBar.rating == 5.0f) {
                    FuncoesUtil().updateRatingBar(ratingBar, hitsSequence)
                    congratulationsDialog(this)
                }else if (btnB.text == correctAnswer) {
                    btnB.setBackgroundResource(R.drawable.container_button_answer_correct)

                    Handler().postDelayed({
                        btnB.setBackgroundResource(R.drawable.container_button)
                    }, 500)

                    hitsSequence++
                    FuncoesUtil().updateRatingBar(ratingBar, hitsSequence)
                } else {
                    dicasTCCDialog(this, "Dicas", tipActualQuestion)
                    hitsSequence = 0
                }
                contQuestionsMiddle++
                displayRandomQuestion(middleQuizManager)
            })

            btnC.setOnClickListener(View.OnClickListener {
                if(contQuestionsMiddle == 4 || ratingBar.rating == 5.0f) {
                    FuncoesUtil().updateRatingBar(ratingBar, hitsSequence)
                    congratulationsDialog(this)
                } else if (btnC.text == correctAnswer) {
                    btnC.setBackgroundResource(R.drawable.container_button_answer_correct)

                    Handler().postDelayed({
                        btnC.setBackgroundResource(R.drawable.container_button)
                    }, 500)

                    hitsSequence++
                    FuncoesUtil().updateRatingBar(ratingBar, hitsSequence)
                } else {
                    dicasTCCDialog(this, "Dicas", tipActualQuestion)
                    hitsSequence = 0
                }
                contQuestionsMiddle++
                displayRandomQuestion(middleQuizManager)

            })
        }
    }

    private fun configuraAcaoBotao() {
        if (contQuestionsEasy < 4) {
            contQuestionsEasy++
            displayRandomQuestion(easyQuizManager)
        } else if (contQuestionsMiddle <= 3) {
            if (contQuestionsMiddle == 1) {
                dicasTCCDialog(
                    this,
                    getString(R.string.fase2_title),
                    getString(R.string.fase2txt)
                )
            }
            displayRandomQuestion(middleQuizManager)
            trocarLayoutBotoes()
        }
    }

    private fun trocarLayoutBotoes() {
        btnSad.isEnabled = false
        btnNeutral.isEnabled = false
        btnNormal.isEnabled = false
        btnAngry.isEnabled = false
        btnHappy.isEnabled = false
        btnQuestion.isEnabled = false

        btnSad.isInvisible = true
        btnNeutral.isInvisible = true
        btnNormal.isInvisible = true
        btnAngry.isInvisible = true
        btnHappy.isInvisible = true
        btnQuestion.isInvisible = true

        btnA.isEnabled = true
        btnB.isEnabled = true
        btnC.isEnabled = true

        btnA.isInvisible = false
        btnB.isInvisible = false
        btnC.isInvisible = false
    }

    private fun displayRandomQuestion(quizManager: QuizManager) {
        val question = quizManager.getRandomQuestion()
        question?.let {
            questionTv.text = it.question
            correctAnswer = it.correctAnswer
            val answers = it.answers
            var tip = it.tip
            if (tip != null) {
                tipActualQuestion = tip
            }
            if(quizManager.nameJson.contains("middlequestions") && answers.size >= 3) {
                btnA.text = answers[0]
                btnB.text = answers[1]
                btnC.text = answers[2]
            }
        }

        actualQuestion = questionTv.text.toString()
    }

    private fun dicasTCCDialog(context: Context, title: String, msg: String) {
        val dialogBinding = layoutInflater.inflate(R.layout.dicas_tcc_dialog, null)
        val tvTitlte = dialogBinding.findViewById<TextView>(R.id.title)
        val tvMsg = dialogBinding.findViewById<TextView>(R.id.dica_msg)
        tvTitlte.text = title
        tvMsg.text = msg

        val myDialog = Dialog(context)
        myDialog.setContentView(dialogBinding)

        myDialog.setCancelable(true)
        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myDialog.show()

        val btnOk = dialogBinding.findViewById<AppCompatButton>(R.id.btn_ok)
        btnOk.setOnClickListener(View.OnClickListener { myDialog.dismiss() })
    }

    private fun congratulationsDialog(context: Context) {
        val dialogBinding = layoutInflater.inflate(R.layout.congratulations_dialog, null)

        val myDialog = Dialog(context)
        myDialog.setContentView(dialogBinding)

        myDialog.setCancelable(true)
        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myDialog.show()

        val btnBye = dialogBinding.findViewById<AppCompatButton>(R.id.btn_bye)
        btnBye.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, InitialActivity::class.java)
            FuncoesUtil().proximaTela(this, intent)
            myDialog.dismiss()
            finish()
        })

        val btnGoResult = dialogBinding.findViewById<AppCompatButton>(R.id.btn_go_results)
        btnGoResult.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            FuncoesUtil().proximaTela(this, intent)
            myDialog.dismiss()
            finish()
        })
    }
}