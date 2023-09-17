package com.example.arithmeticapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_math.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_math : Fragment() {
    // TODO: Rename and change types of parameters
    private var difficulty = -1
    private var operation = -1
    private var numQuestions = -1
    private var questionsCorrect = 0


//    override fun onViewStateRestored(savedInstanceState: Bundle?) {
//        super.onViewStateRestored(savedInstanceState)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            difficulty = it.getInt("DIFFICULTY")
            operation = it.getInt("OPERATION")
            numQuestions= it.getInt("NUMQUESTIONS")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_math, container, false)
        val answerBox = view.findViewById<TextView>(R.id.answerBox)
        mathGenerator(difficulty,operation,view)


        answerBox.setOnClickListener {
            answerBox.text = ""
        }
//        val startButton = view.findViewById<TextView>(R.id.test)
//        startButton.text = difficulty.toString()
        return view

    }

    fun mathGenerator(difficulty: Int, operation: Int, view: View): Int {
        var num1 = -1
        var num2 = -1
        val firstOperand = view.findViewById<TextView>(R.id.firstOperand)
        val secondOperand = view.findViewById<TextView>(R.id.secondOperand)

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
            return num1 + num2
        }
        else if (operation == 1) {
            return num1 * num2
        }
        else if (operation == 2) {
            return num1 / num2
        }
        else if (operation == 3) {
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