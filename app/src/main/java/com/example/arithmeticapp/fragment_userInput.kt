package com.example.arithmeticapp

import android.annotation.SuppressLint
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
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_userInput.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_userInput : Fragment() {

    private var difficulty = 0 // 0:easy 1:med 2:hard
    private var operation = 0 // 0:add 1:mult 2:div 3:subtract
    private var numQuestions = 1
    private var difficultyChecked = false
    private var operationChecked = false

    override fun onSaveInstanceState(outState: Bundle) {
        /**
         * Saves the data of [difficulty], [numQuestions] and [operation] at the time or orientation change,
         * to be re-assigned in onRestoreInstanceState()
         *
         * @property outState: Bundle that holds the data [DIFFICULTY], [OPERATION], AND [NUMQUESTIONS]
         */
        super.onSaveInstanceState(outState)
        outState.putInt("DIFFICULTY",difficulty)
        outState.putInt("OPERATION", operation)
        outState.putInt("NUMQUESTIONS", numQuestions)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
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

        val onCheckedChangeListener = RadioGroup.OnCheckedChangeListener { _, _ ->
            difficultyChecked = difficultyGroup.checkedRadioButtonId != -1
            operationChecked = operationGroup.checkedRadioButtonId != -1
            startButton.isEnabled = difficultyChecked && operationChecked
        }

        difficultyGroup.setOnCheckedChangeListener(onCheckedChangeListener)
        operationGroup.setOnCheckedChangeListener(onCheckedChangeListener)

        startButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("DIFFICULTY", difficulty)
            bundle.putInt("OPERATION", operation)
            bundle.putInt("NUMQUESTIONS",numQuestions)
            view.findNavController().navigate(R.id.action_fragment_userInput_to_fragment_math,bundle)
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
        minusSign.setOnClickListener {
            if (numQuestions > 1) {
                numQuestions--
                questionNumber.text = numQuestions.toString()
                Log.i("fragment_userInput", numQuestions.toString())
            }
        }
        plusSign.setOnClickListener {
            numQuestions++
            questionNumber.text = numQuestions.toString()
            Log.i("fragment_userInput", numQuestions.toString())
        }




        return view
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked


            // Check which radio button was clicked
            when (view.getId()) {
                R.id.easyButton ->
                    if (checked) {
                        difficulty = 0
                        Log.i("fragment_userInput", "difficulty easy")
                    }
                R.id.mediumButton ->
                    if (checked) {
                        difficulty = 1
                        Log.i("fragment_userInput", "difficulty med")
                    }
                R.id.hardButton ->
                    if (checked) {
                        difficulty = 2
                        Log.i("fragment_userInput", "difficulty hard")
                    }
                R.id.additionButton ->
                    if (checked) {
                        operation = 0
                        Log.i("fragment_userInput", "op addition")
                    }
                R.id.multiplicationButton ->
                    if (checked) {
                        operation = 1
                        Log.i("fragment_userInput", "op mult")
                    }
                R.id.divisionButton ->
                    if (checked) {
                        operation = 2
                        Log.i("fragment_userInput", "op div")
                    }
                R.id.subtractionButton ->
                    if (checked) {
                        operation = 3
                        Log.i("fragment_userInput", "op subtraction")
                    }
            }
        }
    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment fragment_userInput.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            fragment_userInput().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}