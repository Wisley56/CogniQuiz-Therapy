package org.wfs.cogniquiztherapy.view_model.utils

import android.content.Context
import org.json.JSONObject

class QuizManager(private val context: Context, var nameJson: String) {
    private val questions: List<Question>

    init {
        val jsonString = context.assets.open("$nameJson.json").bufferedReader().use {
            it.readText()
        }

        // Extrair as perguntas do JSON e armazená-las em uma lista de objetos Question
        val jsonObject = JSONObject(jsonString)
        val jsonArray = jsonObject.getJSONArray(nameJson)
        val questionList = mutableListOf<Question>()
        for (i in 0 until jsonArray.length()) {
            val questionObject = jsonArray.getJSONObject(i)
            val answerList = mutableListOf<String>()

            // Adiciona respostas obrigatórias
            answerList.add(questionObject.getString("answer1"))
            answerList.add(questionObject.getString("answer2"))
            answerList.add(questionObject.getString("answer3"))

            // Adiciona respostas opcionais, se existirem
            for (j in 4..6) {
                val answerKey = "answer$j"
                if (questionObject.has(answerKey)) {
                    answerList.add(questionObject.getString(answerKey))
                }
            }

            val question = Question(
                questionObject.getString("question"),
                answerList,
                questionObject.getString("answerCorrect"),
                questionObject.optString("tip", null)
            )
            questionList.add(question)
        }

        questions = questionList
    }

    // Método para selecionar uma pergunta aleatória
    fun getRandomQuestion(): Question? {
        return questions.shuffled().firstOrNull()
    }
}

data class Question(val question: String, val answers: List<String>, val correctAnswer: String, var tip: String?)
