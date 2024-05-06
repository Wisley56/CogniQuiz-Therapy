package org.wfs.cogniquiztherapy.model

class Question(question: String, answer1: String, answer2: String,
               answer3: String, answer4: String?, answer5: String?, answer6: String?, answerCorrect: String, tip: String?) {
    companion object{
        fun createQuestion(question: String, answer1: String, answer2: String, answer3: String,
                           answer4: String?, answer5: String?, answer6: String?, answerCorrect: String, tip: String?): Question {
            return Question(question, answer1, answer2, answer3, answer4, answer5, answer6, answerCorrect, tip)
        }
    }
}