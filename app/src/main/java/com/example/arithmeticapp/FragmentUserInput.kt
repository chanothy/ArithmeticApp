package com.example.arithmeticapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs


class FragmentUserInput : Fragment() {
    /**
     * Arithmetic app fragment User Input.
     * Serves as userInput page.
     *
     * Fragment that serves as the userInput section of ArithmeticApp.
     * Private variables store the information that is passed to nex fragment.
     *
     * @author Timothy Chan
     */
    private var difficulty = 0 // 0:easy 1:med 2:hard
    private var operation = 0 // 0:add 1:mult 2:div 3:subtract
    private var numQuestions = 1
    private var difficultyChecked = false
    private var operationChecked = false
    private var questionsCorrect = -1
    private var questionsTotal = -1
    private var lastOperation = -1
    private val args: FragmentUserInputArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        /**
         * Assigns values from previous fragment to variables in this fragment
         *
         * @property savedInstanceState - takes bundle if passed from previous fragment that contains values
         */
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (args.prevQuestionsCorrect != -1) {
                questionsCorrect = args.prevQuestionsCorrect
            }
            if (args.prevNumQuestions != -1) {
                questionsTotal = args.prevNumQuestions
            }
            if (args.prevOperation != -1) {
                lastOperation = args.prevOperation
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        /**
         * Implements onClickListeners and onChangeListeners for all buttons and radioButtons on UI.
         * onClickListeners update their respective private variable (difficulty, operation, numQuestions, etc).
         * onChangeListener checks for both radioGroups to be checked and updates the startButton when both options have been chosen
         * Implements safe args to pass data to next fragment.
         *
         * @property inflater - converts fragment xml to Kotlin code for UI
         * @property savedInstanceState - checks for a bundle which can contain information from a previous fragment
         *
         */
        // Inflate the layout for this fragment

        /**
         * Assigns all views and buttons in FragmentUserInput.kt using their @ids
         */
        val view = inflater.inflate(R.layout.fragment_user_input, container, false)

        val startButton = view.findViewById<Button>(R.id.startButton)
        val easyButton = view.findViewById<RadioButton>(R.id.easyButton)
        val mediumButton = view.findViewById<RadioButton>(R.id.mediumButton)
        val hardButton = view.findViewById<RadioButton>(R.id.hardButton)
        val additionButton = view.findViewById<RadioButton>(R.id.additionButton)
        val multiplicationButton = view.findViewById<RadioButton>(R.id.multiplicationButton)
        val divisionButton = view.findViewById<RadioButton>(R.id.divisionButton)
        val subtractionButton = view.findViewById<RadioButton>(R.id.subtractionButton)
        val minusSign = view.findViewById<TextView>(R.id.minusSign)
        val plusSign = view.findViewById<TextView>(R.id.plusSign)
        val questionNumber = view.findViewById<TextView>(R.id.questionNumber)
        val difficultyGroup = view.findViewById<RadioGroup>(R.id.difficultyGroup)
        val operationGroup = view.findViewById<RadioGroup>(R.id.operationGroup)
        val correctResultsView = view.findViewById<TextView>(R.id.correctResultsView)

        if (correctResultsView.text == "") {
            correctResultsView.visibility = View.GONE
        }

        val onCheckedChangeListener = RadioGroup.OnCheckedChangeListener { _, _ ->
            difficultyChecked = difficultyGroup.checkedRadioButtonId != -1
            operationChecked = operationGroup.checkedRadioButtonId != -1
            startButton.isEnabled = difficultyChecked && operationChecked
        }

        // 0:add 1:mult 2:div 3:subtract
        var printOp = ""
        when (lastOperation) {
            0 -> printOp = "addition"
            1 -> printOp = "multiplication"
            2 -> printOp = "division"
            3 -> printOp = "subtraction"
        }

        if (questionsTotal != -1) {
            correctResultsView.visibility = View.VISIBLE
            if ((questionsCorrect.toDouble())/(questionsTotal.toDouble()) < .8) {
                correctResultsView.text = "You got $questionsCorrect out of $questionsTotal in $printOp. You need practice."
            }
            else {
                correctResultsView.text = "You got $questionsCorrect out of $questionsTotal correct in $printOp. Good work!"
            }
        }

        difficultyGroup.setOnCheckedChangeListener(onCheckedChangeListener)
        operationGroup.setOnCheckedChangeListener(onCheckedChangeListener)

        startButton.setOnClickListener {
            val action = FragmentUserInputDirections.actionFragmentUserInputToFragmentMath(difficulty,operation,numQuestions)
            view.findNavController().navigate(action)
        }
        easyButton.setOnClickListener {
            onRadioButtonClicked(easyButton)
        }
        mediumButton.setOnClickListener {
            onRadioButtonClicked(mediumButton)
        }
        hardButton.setOnClickListener {
            onRadioButtonClicked(hardButton)
        }
        additionButton.setOnClickListener {
            onRadioButtonClicked(additionButton)
        }
        multiplicationButton.setOnClickListener {
            onRadioButtonClicked(multiplicationButton)
        }
        divisionButton.setOnClickListener {
            onRadioButtonClicked(divisionButton)
        }
        subtractionButton.setOnClickListener {
            onRadioButtonClicked(subtractionButton )
        }

        /**
         *  increments the numQuestions onClick but only if it doesn't go beneath 1
         *  because you cannot have the app do zero questions
         */
        minusSign.setOnClickListener {
            if (numQuestions > 1) {
                numQuestions--
                questionNumber.text = numQuestions.toString()
                Log.i("FragmentUserInput", numQuestions.toString())
            }
        }
        plusSign.setOnClickListener {
            numQuestions++
            questionNumber.text = numQuestions.toString()
            Log.i("FragmentUserInput", numQuestions.toString())
        }

        return view
    }

    fun onRadioButtonClicked(view: View) {
        /**
         * Looks at button and assigns difficulty and operation depending on the type of button.
         * Only works on RadioButtons
         *
         * @property view - is the view that is taken in to check. This should be a RadioButton view.
         * @property difficulty - difficulty is changed depending on which RadioButton is pressed
         * @property operation - operation is changed depending on RadioButton
         */
        if (view is RadioButton) {
            val checked = view.isChecked

            // Check which radio button
            when (view.getId()) {
                R.id.easyButton ->
                    if (checked) {
                        difficulty = 0
                        Log.i("FragmentUserInput", "difficulty easy")
                    }
                R.id.mediumButton ->
                    if (checked) {
                        difficulty = 1
                        Log.i("FragmentUserInput", "difficulty med")
                    }
                R.id.hardButton ->
                    if (checked) {
                        difficulty = 2
                        Log.i("FragmentUserInput", "difficulty hard")
                    }
                R.id.additionButton ->
                    if (checked) {
                        operation = 0
                        Log.i("FragmentUserInput", "op addition")
                    }
                R.id.multiplicationButton ->
                    if (checked) {
                        operation = 1
                        Log.i("FragmentUserInput", "op mult")
                    }
                R.id.divisionButton ->
                    if (checked) {
                        operation = 2
                        Log.i("FragmentUserInput", "op div")
                    }
                R.id.subtractionButton ->
                    if (checked) {
                        operation = 3
                        Log.i("FragmentUserInput", "op subtraction")
                    }
            }
        }
    }
}