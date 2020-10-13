package com.example.roomdatabaseexample.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseexample.R

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

class MainFragment : Fragment() {

    private lateinit var rootView:View

    // RecyclerView:
    private lateinit var rv:RecyclerView
    private lateinit var adapter:VocListAdapter

    // MainViewModel:
    private lateinit var mainViewModel: MainViewModel

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
        mainViewModel = ViewModelProvider(requireActivity(),MainViewModelFactory(requireActivity().application)).get(MainViewModel::class.java)
        mainViewModel.getLiveVocList().observe(viewLifecycleOwner, Observer { items ->
            adapter.updateContent(ArrayList(items))
        })
    }

    private fun initRecyclerView()
    {
        rv = rootView.findViewById(R.id.main_rv)
        adapter = VocListAdapter(ArrayList())
        rv.adapter = adapter
    }
}