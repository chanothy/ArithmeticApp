package com.example.arithmeticapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import org.w3c.dom.Text
import kotlin.math.log
import kotlin.random.Random

class fragment_math : Fragment() {
    // TODO: Rename and change types of parameters
    private var difficulty = -1
    private var operation = -1
    private var numQuestions = -1
    private var questionsCorrect = 0
    private var originalQuestions = -1


//    override fun onViewStateRestored(savedInstanceState: Bundle?) {
//        super.onViewStateRestored(savedInstanceState)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            difficulty = it.getInt("DIFFICULTY")
            operation = it.getInt("OPERATION")
            numQuestions = it.getInt("NUMQUESTIONS")
            originalQuestions = it.getInt("NUMQUESTIONS")
            Log.i("fragment_math",numQuestions.toString())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_math, container, false)
        val answerBox = view.findViewById<EditText>(R.id.answerBox)
        val doneButton = view.findViewById<Button>(R.id.doneButton)
        var answer = mathGenerator(difficulty,operation,view)

        answerBox.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                answerBox.text.clear()
            }
        }

        doneButton.setOnClickListener {
            if (answerBox.text.toString() == answer.toString()) {
                questionsCorrect++
            }
            if (numQuestions <= 1) {
                val bundle = Bundle()
                bundle.putInt("QUESTIONSCORRECT", questionsCorrect)
                bundle.putInt("NUMQUESTIONS", originalQuestions)
                view.findNavController().navigate(R.id.action_fragment_math_to_fragment_result,bundle)
            }
            answerBox.text.clear()
            answer = mathGenerator(difficulty,operation,view)
            numQuestions--
        }



//        val startButton = view.findViewById<TextView>(R.id.test)
//        startButton.text = difficulty.toString()
        return view

    }

    fun mathGenerator(difficulty: Int, operation: Int, view: View): Int {
        var num1 = -1
        var num2 = -1
        var op = ""
        val firstOperand = view.findViewById<TextView>(R.id.firstOperand)
        val secondOperand = view.findViewById<TextView>(R.id.secondOperand)
        val operator = view.findViewById<TextView>(R.id.operator)

        if (difficulty == 0) {
            num1 = Random.nextInt(0,9)
            num2 = Random.nextInt(0,9)
        }
        else if (difficulty == 1) {
            num1 = Random.nextInt(0,24)
            num2 = Random.nextInt(0,24)
        }
        else if (difficulty == 2) {
            num1 = Random.nextInt(0,49)
            num2 = Random.nextInt(0,49)
        }
        firstOperand.text = num1.toString()
        secondOperand.text= num2.toString()

        if (operation == 0) {
            operator.text = "+"
            return num1 + num2
        }
        else if (operation == 1) {
            operator.text = "X"
            return num1 * num2
        }
        /*
        Rounds down. I can do decimals, but makes it more time consuming to test.
        Would use andra String.format for decimals and would change this func to return a string or float
         */
        else if (operation == 2) {
            operator.text = "/"
            return num1 / num2
        }
        else if (operation == 3) {
            operator.text = "-"
            return num1 - num2
        }

        return -1
    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment fragment_math.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            fragment_math().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}