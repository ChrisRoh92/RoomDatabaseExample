package com.example.roomdatabaseexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseexample.main.VocListAdapter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment() {

    private lateinit var rootView:View

    // RecyclerView:
    private lateinit var rv:RecyclerView
    private lateinit var adapter:VocListAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rootView = view

        initRecyclerView()
    }

    private fun initRecyclerView()
    {
        rv = rootView.findViewById(R.id.main_rv)
        val content = ArrayList<String>(List(25) {""})
        adapter = VocListAdapter(content)
        rv.adapter = adapter
    }
}