package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlin.apply
import kotlin.let

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnHomeButtonClickListener? = null

    interface OnHomeButtonClickListener {
        fun onButtonClicked(id: Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnHomeButtonClickListener) {
            listener = context
        } else {
            throw kotlin.RuntimeException("$context must implement OnHomeButtonClickListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Referencia a botones desde el layout
        val btnReserve = view.findViewById<Button>(R.id.btnReserve)
        val btnSchedule = view.findViewById<Button>(R.id.btnSchedule)
        val btnBuses = view.findViewById<Button>(R.id.btnBuses)
        val btnGrades = view.findViewById<Button>(R.id.btnGrades)
        val btnClassroom = view.findViewById<Button>(R.id.btnClassroom)
        val btnAccount = view.findViewById<Button>(R.id.btnAccount)

        // Setear listeners que llamen a MainMenu
        btnReserve.setOnClickListener { listener?.onButtonClicked(it.id) }
        btnSchedule.setOnClickListener { listener?.onButtonClicked(it.id) }
        btnBuses.setOnClickListener { listener?.onButtonClicked(it.id) }
        btnGrades.setOnClickListener { listener?.onButtonClicked(it.id) }
        btnClassroom.setOnClickListener { listener?.onButtonClicked(it.id) }
        btnAccount.setOnClickListener { listener?.onButtonClicked(it.id) }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
