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
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import kotlin.random.Random

class FragmentMath : Fragment() {
    /**
     * Arithmetic app fragment math solving portion.
     * Serves as math solving page.
     *
     * Fragment that serves as the math section of ArithmeticApp.
     * The private variables recieve information from userInput and are then used in this fragment
     *
     * @property difficulty - difficulty from userInput
     * @property operation - operation from userInput
     * @property numQuestions - number of questions left to solve
     * @property questionsCorrect - questions answered correctly
     * @property originalQuestions - original amount of questions in userInput
     *
     * @author Timothy Chan
     */
    private var difficulty = -1
    private var operation = -1
    private var numQuestions = -1
    private var questionsCorrect = 0
    private var originalQuestions = -1

    val args: FragmentMathArgs by navArgs()

//    override fun onViewStateRestored(savedInstanceState: Bundle?) {
//        super.onViewStateRestored(savedInstanceState)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        /**
         * Assigns values from previous fragment to variables in this fragment for use.
         *
         * @property savedInstanceState - takes a bundle passed from previous fragment that contains values
         *      needed in this fragment
         */
        super.onCreate(savedInstanceState)
        arguments?.let {
            difficulty = args.difficulty
            operation = args.operation
            numQuestions = args.numQuestions
            originalQuestions = args.numQuestions
            Log.i("FragmentMath",numQuestions.toString())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        /**
         * Implements onClickListeners for Done button to take in the answer and check with correct solution.
         * Increments if the solution is correct and moves to next fragment if enough questions have been answered.
         *
         * Implements onFocusChangeListener to clear the text in the textBox when it has the focus.
         *
         * @property container - container for fragment
         * @property savedInstanceState - possible bundle that contains information for creating the view,
         *      or saving information when orientation is changed
         * @property inflater - converts fragment xml to Kotlin code for UI
         */
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
            if (answerBox.text.toString() == "." || answerBox.text.isEmpty() || answerBox.text.toString() == "-") {
                Log.i("FragmentMath","bad input")
            }
            else if (operation == 2 && Math.abs(answerBox.text.toString().toDouble() - answer) <= .01) {
                questionsCorrect++
            }
            else if (operation != 2 && answerBox.text.toString().toDouble() == answer) {
                questionsCorrect++
            }
            if (numQuestions <= 1) {
                val action = FragmentMathDirections.actionFragmentMathToFragmentResult(questionsCorrect,originalQuestions)
                view.findNavController().navigate(action)
//                val bundle = Bundle()
//                bundle.putInt("QUESTIONSCORRECT", questionsCorrect)
//                bundle.putInt("NUMQUESTIONS", originalQuestions)
//                view.findNavController().navigate(R.id.action_fragment_math_to_fragment_result,bundle)
            }
            answerBox.text.clear()
            answer = mathGenerator(difficulty,operation,view)
            numQuestions--
        }

        return view
    }

    fun mathGenerator(difficulty: Int, operation: Int, view: View): Double {
        /**
         * Takes difficulty, operation, view to calculate the solution number.
         *
         * @property difficulty - takes difficulty
         * @property operation - takes operation for math
         * @property view - view of fragment for TextView operations
         *
         * @return int solution for the current math operation
         */
        var num1 = -1
        var num2 = -1
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
            return (num1 + num2).toDouble()
        }
        else if (operation == 1) {
            operator.text = "X"
            return (num1 * num2).toDouble()
        }
        /*
        Rounds down. I can do decimals, but makes it more time consuming to test.
        Would use a String.format for decimals and would change this func to return a string or float
         */
        else if (operation == 2) {
            operator.text = "/"
            if (num2 == 0) {
                num2 = 1
                secondOperand.text = num2.toString()
                return num1.toDouble() / num2.toDouble()
            }
            return num1.toDouble() / num2.toDouble()
        }
        else if (operation == 3) {
            operator.text = "-"
            return (num1 - num2).toDouble()
        }


        return -1.0
    }
}