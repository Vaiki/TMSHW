package com.example.tmshw

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmshw.databinding.FragmentRecyclerViewBinding


class RecyclerViewFragment : Fragment() {
    private var binding: FragmentRecyclerViewBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_recycler_view, container, false)
        val taskList = mutableListOf<Task>(Task(1), Task(2), Task(3), Task(4), Task(5),
            Task(6), Task(7),Task(8))

        val adapter = MyRecyclerViewAdapter(taskList)
        binding?.taskRecycler?.adapter = adapter
        binding?.taskRecycler?.layoutManager = LinearLayoutManager(context)

        return binding?.root


}}