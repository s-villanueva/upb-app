package com.example.myapplication.notas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.notas.GradesViewModel
import com.example.myapplication.R

class GradesFragment : Fragment() {

    private lateinit var viewModel: GradesViewModel
    private lateinit var adapter: GradesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_grades, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[GradesViewModel::class.java]
        adapter = GradesAdapter()

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerGrades)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        viewModel.grades.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        val codigo = requireContext().getSharedPreferences("UserData", AppCompatActivity.MODE_PRIVATE)
            .getString("codigo", "")?.toLongOrNull() ?: return
        viewModel.loadGrades(codigo)
    }
}