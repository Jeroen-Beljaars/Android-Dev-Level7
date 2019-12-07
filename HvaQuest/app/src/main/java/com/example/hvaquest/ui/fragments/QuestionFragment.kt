package com.example.hvaquest.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.hvaquest.ui.QuestionViewModel
import com.example.hvaquest.R
import kotlinx.android.synthetic.main.fragment_question.*

/**
 * A simple [Fragment] subclass.
 */
class QuestionFragment : Fragment() {
    private lateinit var viewModel: QuestionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize the Shared Activity ViewModel
        viewModel = ViewModelProviders.of(activity as AppCompatActivity).get(QuestionViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the current question
        val currentQuestion = viewModel.getCurrentQuestion()

        // Set the progress
        tvCounter.text = viewModel.getProgressCount()

        // Set the question text
        tvQuestion.text = currentQuestion.question

        // Create and set the options
        val radioGroup = rgOptions
        var correctOptionId = 0
        var idCounter = 0
        for (option in currentQuestion.choices){
            val radioButton = RadioButton(context)
            radioButton.text = option
            radioButton.id = idCounter
            radioGroup.addView(radioButton)

            if (option == currentQuestion.correctAnswer) {
                correctOptionId = idCounter
            }
            idCounter++
        }

        btnConfirm.setOnClickListener {
            val selectedButtonId = radioGroup.checkedRadioButtonId
            if(selectedButtonId == correctOptionId) {
                Toast.makeText(context, getText(R.string.correct), Toast.LENGTH_LONG).show()
                viewModel.nextQuestion()
                val action = QuestionFragmentDirections
                    .actionQuestionFragmentToLocationClueFragment(
                        currentQuestion.clue,
                        viewModel.isQuestFinished()
                    )
                findNavController().navigate(action)
            } else {
                Toast.makeText(context, getText(R.string.incorrect), Toast.LENGTH_LONG).show()
            }
        }
    }

}
