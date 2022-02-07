package com.example.mockexam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch


class ThirdFragment : Fragment() {
    lateinit var recyles: RecyclerView
    private val viewModel: LagosModel by activityViewModels {
        LagosModelFactory(
            (activity?.application as AppContext).database.lagosDao()
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_third, container, false)
        recyles = view.findViewById(R.id.recyclerView)
        val lagosAdapter = LagosAdapters()

        lifecycleScope.launch {
            val listGotten = viewModel.getLagosData()
            lagosAdapter.submitList(listGotten)
            recyles.adapter = lagosAdapter }

        return view
    }

    }
