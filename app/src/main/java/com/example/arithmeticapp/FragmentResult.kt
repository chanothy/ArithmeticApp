package com.example.arithmeticapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs

class FragmentResult : Fragment() {
    /**
     * Arithmetic app result page.
     * Serves result page that shows how many questions answered correctly
     *
     * @property questionsCorrect - how many questions correct
     * @property numQuestions - how many questions total
     * @property args - safe args from previous fragment
     *
     * @author Timothy Chan
     */
    private var questionsCorrect = 0
    private var numQuestions = 0
    private val args: FragmentResultArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        /**
         * Assigns values from previous fragment to variables in this fragment
         *
         * @property savedInstanceState - takes bundle if passed from previous fragment that contains values
         */
        super.onCreate(savedInstanceState)
        arguments?.let {
            questionsCorrect = args.questionsCorrect
            numQuestions = args.numQuestions
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /**
         * Creates textView with information about questions answered correctly
         * Implements onClickListener that navigates back to userInput (starting) page
         */
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_result, container, false)
        val resultView = view.findViewById<TextView>(R.id.resultView)
        val restartButton = view.findViewById<Button>(R.id.restartButton)

        restartButton.setOnClickListener {
//            view.findNavController().navigate(R.id.action_fragment_result_to_fragment_userInput)
        }

        resultView.text = "Your result: " + questionsCorrect.toString() + " / " + numQuestions.toString()
        return view
    }

}