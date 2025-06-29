package com.example.myapplication.buses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class ListBusesFragment : Fragment() {

    private val viewModel: BusViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_list_buses, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewRoutes)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Carga inicial de datos
        viewModel.loadBuses()

        // Observa los datos del ViewModel
        viewModel.busList.observe(viewLifecycleOwner) { buses ->
            recyclerView.adapter = BusAdapter(buses)
        }
    }
}