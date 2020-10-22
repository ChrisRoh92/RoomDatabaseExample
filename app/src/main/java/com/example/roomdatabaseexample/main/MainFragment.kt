package com.example.roomdatabaseexample.main

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.repository.database.Voc

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

        // Implement the Interfaces:
        adapter.setOnItemClickListener(object:VocListAdapter.OnItemClickListener{
            override fun setOnItemClickListener(pos: Int) {
                val dialog = DialogInput(adapter.content[pos])
                dialog.show(parentFragmentManager,"update Voc")
            }

        })

        adapter.setOnItemLongClickListener(object:VocListAdapter.OnItemLongClickListener{
            override fun setOnItemLongClickListener(pos: Int) {
                startAlarmDialog(adapter.content[pos])
            }
        })
    }


    private fun startAlarmDialog(voc: Voc)
    {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.apply {
            setMessage("Warning - Entry will be deleted")
            setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, id ->
                Toast.makeText(requireContext(),"${voc.foreignWord} deleted",Toast.LENGTH_SHORT).show()
                mainViewModel.delete(voc)
            })
            setNegativeButton("Abort", DialogInterface.OnClickListener { dialog, id ->
                dialog.dismiss()
            })
        }
        val dialog = builder.create()
        dialog.show()
    }
}