package com.example.hvaquest.ui

import androidx.lifecycle.ViewModel
import com.example.hvaquest.QuestionRepository
import com.example.hvaquest.model.Question

class QuestionViewModel : ViewModel() {

    private val questionRepository = QuestionRepository()
    private lateinit var questions: List<Question>
    private var currentQuestionIndex = 0
    private var questionAnswered = false

    fun nextQuestion() {
        currentQuestionIndex++
    }

    fun previousQuestion() {
        currentQuestionIndex--
    }

    fun getCurrentQuestion(): Question {
        return questions[currentQuestionIndex]
    }

    fun isQuestFinished(): Boolean {
        return currentQuestionIndex == questions.size - 1
    }

    fun getProgressCount(): String {
        return "${currentQuestionIndex+1}/${questions.size}"
    }

    fun startQuest() {
        currentQuestionIndex = 0
        questions = questionRepository.getHvaQuest()
    }
}