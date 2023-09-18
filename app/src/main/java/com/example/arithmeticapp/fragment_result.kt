package com.example.arithmeticapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_result.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_result : Fragment() {
    // TODO: Rename and change types of parameters
    private var questionsCorrect = 0
    private var numQuestions = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            questionsCorrect = it.getInt("QUESTIONSCORRECT")
            numQuestions = it.getInt("NUMQUESTIONS")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_result, container, false)
        val resultView = view.findViewById<TextView>(R.id.resultView)

        resultView.text = "Your result: " + questionsCorrect.toString() + " /" + numQuestions.toString()
        return view
    }

}