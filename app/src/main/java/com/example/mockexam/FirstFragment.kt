package com.example.mockexam

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch


class FirstFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        recyles = view.findViewById(R.id.recyclerView)
        val FaveItems = view.findViewById<Button>(R.id.faveList)
        val ClearItems = view.findViewById<Button>(R.id.clearList)
        FaveItems.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_firstFragment_to_thirdFragment) }
        ClearItems.setOnClickListener { clearData() }
        val lagosAdapter = LagosAdapters()
        lifecycleScope.launch {
            val listGotten = LagosApiss.lagosApiServicess.getDatas().items
            Log.d("FirstFragment", "${listGotten[2]}")
            lagosAdapter.submitList(listGotten)
            recyles.adapter = lagosAdapter
        }
        return view
    }
    fun clearData() {
        lifecycleScope.launch {
            viewModel.deleteData()
        }

        fun changeToFave() {

        }
    }
}
