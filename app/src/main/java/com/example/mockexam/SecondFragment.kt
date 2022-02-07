package com.example.mockexam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import kotlinx.coroutines.launch


class SecondFragment : Fragment() {

    val args: SecondFragmentArgs by navArgs()
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
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        val textName = view.findViewById<TextView>(R.id.textName)
        val textUrls = view.findViewById<TextView>(R.id.UrlName)
        val OwnerImages = view.findViewById<ImageView>(R.id.ownerImages)
        val addButton = view.findViewById<Button>(R.id.addList)
        val removeListen = view.findViewById<Button>(R.id.removeList)
        val logs = args.names
        val urls = args.theUrl
        val avats = args.avatar
        val ids = args.id
        textName.text = "Username: ${logs}"
        textUrls.text = "Profile Url: ${urls}"
        OwnerImages.load(avats) {
            placeholder(R.drawable.ic_placeholder)
            error(R.drawable.ic_broken)
        }
        addButton.setOnClickListener {
            lifecycleScope.launch {
                val input = LagosSecondData(id = ids, login = logs, avatar_url = avats, url = urls)
                viewModel.addLagosData(input)
            }
        }
        removeListen.setOnClickListener { lifecycleScope.launch {
            viewModel.deleteItems(ids)
        } }

        return view
    }

}